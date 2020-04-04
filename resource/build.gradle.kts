plugins {
    id(Plugins.Kotlin.jvm)
    id(Plugins.Kotlin.kotlinSerialization)
    BintraySetup
    jacoco
}

repositories {
    jcenter()
}

tasks {
    test {
        useJUnit()
        useJUnitPlatform()
        testLogging {
            setExceptionFormat("full")
            events("skipped", "failed")
        }
    }
    jacocoTestReport {
        executionData(
            fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec")
        )

        reports {
            xml.isEnabled = true
            xml.destination = file("$buildDir/reports/jacoco/report.xml")
            html.isEnabled = false
            csv.isEnabled = false
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.KotlinX.Coroutines.core)
    implementation(Dependencies.KotlinX.serialization)
    implementation(Dependencies.Kotlin.reflect)
    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.jUnitApi)
    testRuntimeOnly(Dependencies.jUnitEngine)
    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.guava)
}
