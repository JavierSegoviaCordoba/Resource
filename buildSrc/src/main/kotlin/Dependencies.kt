object Dependencies {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect"
    }

    object KotlinX {
        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        }

        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serialization}"
    }

    const val jUnit = "org.junit.jupiter:junit-jupiter-api:${Versions.jUnit}"
    const val jUnitApi = "org.junit.jupiter:junit-jupiter-api:${Versions.jUnit}"
    const val jUnitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jUnit}"

    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val guava = "com.google.guava:guava:${Versions.guava}"

    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
}
