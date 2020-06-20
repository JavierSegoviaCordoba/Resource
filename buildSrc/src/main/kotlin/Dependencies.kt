val commonDependencies get() = Dependencies.Common
val commonTestDependencies get() = Dependencies.CommonTest
val jvmDependencies get() = Dependencies.Jvm
val jvmTestDependencies get() = Dependencies.JvmTest

object Dependencies {

    object Common {
        val kotlinStdlib = kotlin("stdlib-common")
        val coroutinesCore = kotlinx("coroutines-core-common:${Versions.coroutines}")
        val kotlinSerialization = kotlinx("serialization-runtime:${Versions.serialization}")
    }

    object CommonTest {
        val kotlinTest = kotlin("test-common")
        val kotlinTestAnnotation = kotlin("test-annotations-common")
    }

    object Jvm {
        val kotlinStdlib = kotlin("stdlib-jdk8")
        val coroutinesCore = kotlinx("coroutines-core:${Versions.coroutines}")
        val kotlinSerialization = kotlinx("serialization-runtime:${Versions.serialization}")
    }

    object JvmTest {
        val kotlinTest = kotlin("test")
        val kotlinTestJUnit = kotlin("test-junit")
    }

    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
}

private fun kotlin(dependency: String) = "org.jetbrains.kotlin:kotlin-$dependency"
private fun kotlinx(dependency: String) = "org.jetbrains.kotlinx:kotlinx-$dependency"
