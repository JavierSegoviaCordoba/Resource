import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.Spring.boot) version Versions.Spring.boot
    id(Plugins.Spring.dependencyManager) version Versions.Spring.dependencyManager
    id(Plugins.Kotlin.jvm)
    id(Plugins.Kotlin.spring) version Versions.kotlin
}

group = Build.groupId
version = Build.version

java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    jcenter()
}

dependencies {
    implementation(Dependencies.SpringFramework.boot)
    implementation(Dependencies.Jackson.kotlin)

    Dependencies.Kotlin.apply {
        implementation(stdlib)
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = Versions.jvmTarget
        }
    }
    withType<Delete> { delete("out") }
}