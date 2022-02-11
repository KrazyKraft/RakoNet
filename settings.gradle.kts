pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        jcenter()
    }

    plugins {
        kotlin("multiplatform") version "1.5.0-M2"
    }
}

include(":shared")
