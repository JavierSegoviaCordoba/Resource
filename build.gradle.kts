import tasks.baseKotlinOptions

plugins {
    DependencyUpdates
    Detekt
    Nexus
}

allprojects {
    tasks {
        withType<Delete> { delete(buildDir) }
        baseKotlinOptions
    }
}

tasks {
    withType<Test> {
        maxParallelForks = Runtime.getRuntime().availableProcessors()
        useJUnitPlatform()
        useTestNG()
    }
}
