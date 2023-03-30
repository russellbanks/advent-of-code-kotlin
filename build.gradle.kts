plugins {
    kotlin("jvm") version "1.8.20"
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
