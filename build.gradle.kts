import tasks.baseKotlinOptions

plugins {
    id(Plugins.gradleVersions) version Versions.gradleVersions
    id(Plugins.detekt) version Versions.detekt
    jacoco
}

subprojects {
    tasks {
        withType<Test> {
            maxParallelForks = Runtime.getRuntime().availableProcessors()
            useJUnitPlatform()
            useTestNG()
        }
    }
}

allprojects {
    repositories {
        jcenter()
    }

    apply(plugin = Plugins.detekt)

    dependencies {
        detektPlugins(Dependencies.detektFormatting)
    }

    detekt {
        toolVersion = Versions.detekt
        ignoreFailures = true
        autoCorrect = true
    }

    tasks {
        withType<Test> { maxParallelForks = Runtime.getRuntime().availableProcessors() }
        baseKotlinOptions
    }
}
