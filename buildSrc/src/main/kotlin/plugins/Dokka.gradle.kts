plugins {
    id("org.jetbrains.dokka")
}

tasks.dokkaHtml {
    outputDirectory.set(buildDir.resolve("dokka"))
}
