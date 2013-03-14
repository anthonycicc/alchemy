(ns mikera.alchemy.world
  (:require [mikera.alchemy.engine :as engine])
  (:require [mikera.alchemy.lib :as lib]) 
  (:require [mikera.cljutils.find :as find]) 
  (:require [mikera.alchemy.dungeon :as dungeon]) 
  (:use mikera.orculje.core))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ======================================================
;; WORLD SIMULATION
;; everything internal to the game goes in here

;; constants

(def BLANK_TILE (thing lib/BLANK_TILE_PROPS))


;; ======================================================
;; key external functions (called by main namespace)

(defn new-game []
  (as-> (empty-game) game
    ;; build game environment
    (lib/setup game)
    (dungeon/generate game)
    
    (assoc game :functions {:is-identified? engine/is-identified?})
    
    ;; add the hero
    (add-thing game (loc 0 0 0) (lib/create game "you")) 
    (merge game {:turn 0
                 :hero-id (:last-added-id game)})
    
    ;; testing state
    ;; (add-thing game (engine/hero game) (lib/create game "invincibility")) 

    ;; final prep
    (engine/message game (engine/hero game) "Welcome to the dungeon, Alchemist! Seek the Philosopher's Stone!")
    (engine/update-visibility game)))

(defn monster-turn [game aps-added]
  ;; (println (str "Monster turn: " (:turn game)))
  (loop [game game
         obs (seq (all-things game))]
    (if obs
      (let [o (get-thing game (first obs))]
        (if-let [mfn (:on-action o)]
          (recur
            (let [new-aps (+ (or (:aps o) 0) aps-added)
	                game (if (== aps-added 0) game (! game o :aps new-aps))
                  o (get-thing game o)]
	            ;; (println (str new-aps " aps action on " (into {} o)))
	            
	            (if (> new-aps 0)
	                (mfn game o)
	                game))
            (next obs))
          (recur game (next obs))))
      game)))

(defn end-turn 
  "Called to update the game after every player turn"
  ([game]
    (let [hero (engine/hero game)]
      (as-> game game
            (monster-turn game (- (:aps hero)))
            (monster-turn game 0)
            (monster-turn game 0)
            (engine/update-visibility game)
            (let [turn (:turn game)]
              ;; (println (str "Finished turn: " turn))
              (assoc game :turn (inc turn)))
            (! game hero :aps 0)
            ;;(do (println "Turn ended") game)
            ))))

(defn handle-move 
  "Handles a hero move"
  [game dir]
  (let [h (engine/hero game)]
    (as-> game game
      (engine/clear-messages game)
      (engine/try-move game h (loc-add (:location h) dir))
      (end-turn game))))

(defn handle-command
  "Handles a command, expressed as a complete command String"
  [game k]
  (as-> game game
    (engine/clear-messages game)
    (cond
      :else (do 
              (println (str "Turn " (:turn game) " unhandled command [" k "]")) 
              game))    
    (end-turn game)))