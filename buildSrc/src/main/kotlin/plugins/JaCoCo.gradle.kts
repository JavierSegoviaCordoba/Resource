
plugins {
    jacoco
    java
}

tasks.withType<JacocoReport> {
    dependsOn("jvmTest")
    val coverageSourceDirs = arrayOf(
        "commonMain/src",
        "jvmMain/src"
    )
    val classFiles = File("$buildDir/classes/kotlin/jvm/main")
        .walkBottomUp()
        .toSet()
    classDirectories.setFrom(classFiles)
    sourceDirectories.setFrom(files(coverageSourceDirs))
    additionalSourceDirs.setFrom(files(coverageSourceDirs))

    executionData.setFrom(files("$buildDir/jacoco/jvmTest.exec"))
    reports {
        xml.isEnabled = true
        xml.destination = file("$buildDir/reports/jacoco/report.xml")
        html.isEnabled = true
        html.destination = file("$buildDir/reports/jacoco/report.html")
        csv.isEnabled = false
    }
}
