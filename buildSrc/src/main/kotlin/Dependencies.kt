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

    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${versions.detekt}"
}

private fun kotlin(dependency: String) = "org.jetbrains.kotlin:kotlin-$dependency"
private fun kotlinx(dependency: String) = "org.jetbrains.kotlinx:kotlinx-$dependency"
