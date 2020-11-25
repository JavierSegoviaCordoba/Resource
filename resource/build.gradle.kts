plugins {
    id(Plugins.Kotlin.multiplatform)
    id(Plugins.Kotlin.kotlinSerialization)
    JaCoCo
    Detekt
    MavenPublish
    Nexus
}

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
    jcenter()
}

val resourceVersion: String by project
val isResourceReleaseEnv: Boolean? = System.getenv("isResourceReleaseEnv")?.toBoolean()
val isResourceRelease: String by project

val finalVersion = resourceVersion.generateVersion(isResourceReleaseEnv ?: isResourceRelease.toBoolean())

group = "com.javiersc.resources"
version = finalVersion

val javaDocs by tasks.creating(Jar::class) {
    dependsOn("javadocJar")
    archiveClassifier.set("javadoc")
}

kotlin {
    explicitApi()

    jvm {
        mavenPublication {
            artifact(javaDocs)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                commonDependencies.apply {
                    api(kotlinSerialization)
                    api(coroutinesCore)
                }
            }
        }

        commonTest {
            dependencies {
                commonTestDependencies.apply {
                    implementation(kotest)
                    implementation(kotlinTest)
                    implementation(kotlinTestAnnotation)
                    implementation(turbine)
                }
            }
        }

        named("jvmMain") {}

        named("jvmTest") {
            dependencies {
                jvmTestDependencies.apply {
                    implementation(kotlinTestJUnit)
                }
            }
        }

        all {
            languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
            languageSettings.useExperimentalAnnotation("kotlin.time.ExperimentalTime")
            languageSettings.useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
        }
    }
}
