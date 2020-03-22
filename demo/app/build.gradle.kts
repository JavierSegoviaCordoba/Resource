import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.Kotlin.jvm)
    id(Plugins.Kotlin.kotlinSerialization)
}

group = Build.groupId
version = Build.version

repositories {
    jcenter()
    //maven("https://dl.bintray.com/javiersegoviacordoba/Resources")
}

dependencies {
    implementation(projects.resource)
    //implementation("com.javiersc.resources:resource:0.9.8")

    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.KotlinX.Coroutines.core)
    implementation(Dependencies.Koin.core)
    Dependencies.Retrofit2.apply {
        implementation(retrofit)
        implementation(retrofitMock)
        implementation(converterSerialization)
    }
    implementation(Dependencies.OkHttp.loggingInterceptor)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = Versions.jvmTarget
}

tasks {
    withType<KotlinCompile> { kotlinOptions.jvmTarget = Versions.jvmTarget }
    withType<Delete> { delete("out") }
}