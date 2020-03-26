plugins {
    id(Plugins.Kotlin.jvm)
    id(Plugins.Kotlin.kotlinSerialization)
    BintraySetup
}

repositories {
    jcenter()
}

dependencies {
    //implementation("com.javiersc.resources:resource:0.9.8")
    implementation(projects.resource)
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.KotlinX.Coroutines.core)
    implementation(Dependencies.Retrofit2.retrofit)
    implementation(Dependencies.KotlinX.serialization)
}