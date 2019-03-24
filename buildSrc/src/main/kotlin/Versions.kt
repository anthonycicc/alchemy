import kotlin.String

/**
 * Find which updates are available by running
 *     `$ ./gradlew buildSrcVersions`
 * This will only update the comments.
 *
 * YOU are responsible for updating manually the dependency version. */
object Versions {
    const val com_github_johnrengelman_shadow_gradle_plugin: String = "5.0.0" 

    const val de_fayard_buildsrcversions_gradle_plugin: String = "0.3.2" 

    const val nebula_clojure_gradle_plugin: String = "8.1.1" 

    const val cljunit: String = "0.2.0" // available: "0.6.0"

    const val mathz: String = "0.1.0" // available: "0.3.0"

    const val mikera: String = "1.5.0" // available: "1.6.1"

    const val orculje: String = "0.0.2" // available: "0.0.3"

    const val swing_console: String = "0.1.2" 

    const val clojure: String = "1.5.1" // available: "1.10.0"

    const val math_combinatorics: String = "0.0.3" // available: "0.1.4"

    /**
     *
     *   To update Gradle, edit the wrapper file at path:
     *      ./gradle/wrapper/gradle-wrapper.properties
     */
    object Gradle {
        const val runningVersion: String = "5.2.1"

        const val currentVersion: String = "5.3"

        const val nightlyVersion: String = "5.4-20190324000026+0000"

        const val releaseCandidate: String = ""
    }
}
