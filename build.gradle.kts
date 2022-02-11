buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0-M2")
    }
}

allprojects {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        jcenter()
    }
}
