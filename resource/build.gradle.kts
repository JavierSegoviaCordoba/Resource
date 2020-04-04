plugins {
    id(Plugins.Kotlin.jvm)
    id(Plugins.Kotlin.kotlinSerialization)
    BintraySetup
}

repositories {
    jcenter()
}

tasks {
    test {
        useJUnitPlatform()
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
