import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import tasks.setup

plugins {
    id(Plugins.versions) version Versions.versions
    id(Plugins.detekt) version Versions.detekt
}

allprojects {
    apply(plugin = Plugins.detekt)

    detekt {
        toolVersion = Versions.detekt
        ignoreFailures = true
    }

    tasks {
        withType(KotlinCompile::setup)
    }
}
