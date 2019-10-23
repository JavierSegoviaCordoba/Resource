import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.Kotlin.jvm)
}

group = Build.groupId
version = Build.version

repositories {
    jcenter()
}

dependencies {
    implementation(project(Modules.resource))

    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.KotlinX.Coroutines.core)
    implementation(Dependencies.Koin.core)
    Dependencies.Retrofit2.apply {
        implementation(retrofit)
        implementation(retrofitMock)
        implementation(converterGson)
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