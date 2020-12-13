val commonDependencies get() = Dependencies.Common
val commonTestDependencies get() = Dependencies.CommonTest
val jvmTestDependencies get() = Dependencies.JvmTest

object Dependencies {

    object Common {
        val coroutinesCore = kotlinx("coroutines-core:${versions.coroutines}")
        val kotlinSerialization = kotlinx("serialization-core:${versions.serialization}")
    }

    object CommonTest {
        const val kotest = "io.kotest:kotest-assertions-core:${versions.kotest}"
        val kotlinTest = kotlin("test")
        val kotlinTestAnnotation = kotlin("test-annotations-common")
        const val turbine = "app.cash.turbine:turbine:${versions.turbine}"
    }

    object JvmTest {
        val kotlinTestJUnit = kotlin("test-junit")
    }

    object Plugins {
        const val dependencyUpdates = "com.github.ben-manes:gradle-versions-plugin:${versions.dependencyUpdates}"
        const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${versions.detekt}"
        const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${versions.dokka}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${versions.kotlin}"
        const val nexusStaging = "io.codearte.gradle.nexus:gradle-nexus-staging-plugin:${versions.nexusStaging}"
        const val nexusPublish = "de.marcphilipp.gradle:nexus-publish-plugin:${versions.nexusPublish}"
    }

    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${versions.detekt}"
}

private fun kotlin(dependency: String) = "org.jetbrains.kotlin:kotlin-$dependency"
private fun kotlinx(dependency: String) = "org.jetbrains.kotlinx:kotlinx-$dependency"
