import nebula.plugin.clojuresque.tasks.ClojureCompile

plugins {
    java
    application
    id("nebula.clojure") version Versions.nebula_clojure_gradle_plugin
    id("de.fayard.buildSrcVersions") version Versions.de_fayard_buildsrcversions_gradle_plugin
    id("com.github.johnrengelman.shadow") version Versions.com_github_johnrengelman_shadow_gradle_plugin
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://clojars.org/repo")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2")
    }

    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencies {
    compile(Libs.clojure)
    compile(Libs.orculje)
    compile(Libs.mikera)
    compile(Libs.swing_console)
    compile(Libs.mathz)
    compile(Libs.cljunit)
    compile(Libs.math_combinatorics)
}

group = "net.mikera"
version = "0.0.3-SNAPSHOT"

application {
    mainClassName = "mikera.alchemy.GameApp"
}

tasks.withType<JavaCompile> {
//    this.sourceCompatibility = "1.8"
    options.encoding = "UTF-8"
}

//tasks.register<ClojureCompile>("compileClojure")
tasks.findByPath("compileClojure")!!.mustRunAfter("compileJava")