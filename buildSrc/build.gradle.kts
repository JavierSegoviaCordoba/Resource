plugins {
    `kotlin-dsl`
}

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
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-serialization:${versions.kotlin}")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${versions.detekt}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${versions.dependencyUpdates}")
    implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:${versions.nexus}")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
