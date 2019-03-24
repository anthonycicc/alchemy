plugins {
    java
    id("com.github.johnrengelman.shadow") version "5.0.0"
    application
    id("nebula.clojure") version "8.1.1"
}

repositories {
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
    compile("org.clojure:clojure:1.5.1")
    compile("net.mikera:orculje:0.0.2")
    compile("net.mikera:mikera:1.5.0")
    compile("net.mikera:swing-console:0.1.2")
    compile("net.mikera:mathz:0.1.0")
    compile("net.mikera:cljunit:0.2.0")
    compile("org.clojure:math.combinatorics:0.0.3")
}

group = "net.mikera"
version = "0.0.3-SNAPSHOT"

application {
    mainClassName = "mikera.alchemy.GameApp"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
