plugins {
    kotlin("jvm") version "2.1.20"
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }
}
