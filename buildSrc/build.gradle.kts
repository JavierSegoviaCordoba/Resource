plugins {
    `kotlin-dsl`
}

val kotlin = "1.4-M2"
val dependencyUpdates = "0.28.0"
val detekt = "1.9.1"
val nexus = "0.21.2"

repositories {
    jcenter()
    mavenCentral()
    google()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlin")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$detekt")
    implementation("com.github.ben-manes:gradle-versions-plugin:$dependencyUpdates")
    implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:$nexus")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
