dependencyResolutionManagement {
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        mavenCentral()
        jcenter()
    }
}

rootProject.name = "Resource"

enableFeaturePreview("GRADLE_METADATA")

include(":resource")
include(":docs")
