plugins {
    id(Plugins.Kotlin.jvm)
    BintraySetup
}


repositories {
    jcenter()
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.KotlinX.Coroutines.core)
    implementation(Dependencies.Retrofit2.retrofit)
}