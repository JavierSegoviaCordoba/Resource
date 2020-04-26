plugins {
    `kotlin-dsl`
}

val kotlinVersion = "1.4-M1"
val dependencyUpdatesVersion = "0.28.0"
val detektVersion = "1.8.0"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://plugins.gradle.org/m2")
    jcenter()
    mavenCentral()
    google()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detektVersion")
    implementation("com.github.ben-manes:gradle-versions-plugin:$dependencyUpdatesVersion")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
