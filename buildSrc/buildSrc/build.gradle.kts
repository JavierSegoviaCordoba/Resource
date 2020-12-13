plugins {
    `kotlin-dsl`
}

repositories.jcenter()

sourceSets.main {
    java {
        setSrcDirs(setOf(projectDir.parentFile.resolve("src/main/kotlin")))
        include("Dependencies.kt")
        include("Versions.kt")
    }
}
