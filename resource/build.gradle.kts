plugins {
    KotlinMultiplatform
    NexusPublish
    JaCoCo
    Dokka
    LoggerVersioning
}

val dokkaJar by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
    dependsOn(tasks.dokkaHtml)
    dependsOn(tasks.dokkaJavadoc)
}

kotlin {
    explicitApi()

    jvm {
        mavenPublication {
            artifact(dokkaJar)
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

        defaultLanguageSettings
    }
}
