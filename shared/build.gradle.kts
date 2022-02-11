plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm {
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("io.ktor:ktor-io:1.6.7")
            }
        }
    }
}
