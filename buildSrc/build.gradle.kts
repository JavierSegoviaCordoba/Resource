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

    Dependencies.Plugins.apply {
        implementation(dependencyUpdates)
        implementation(detekt)
        implementation(dokka)
        implementation(kotlin)
        implementation(kotlinSerialization)
        implementation(nexusStaging)
        implementation(nexusPublish)
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
