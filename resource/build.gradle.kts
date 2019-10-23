import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*

plugins {
    id(Plugins.Kotlin.jvm)
    id(Plugins.bintray) version Versions.bintray
    id(Plugins.mavenPublish)
}

buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }
}

repositories {
    jcenter()
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.KotlinX.Coroutines.core)
    implementation(Dependencies.Retrofit2.retrofit)
}

tasks {
    withType<KotlinCompile> { kotlinOptions.jvmTarget = Versions.jvmTarget }
    withType<Delete> { delete("out") }
}

val sourcesJar: Jar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val localProperties =
    Properties().apply { load(rootProject.file("local.properties").inputStream()) }


bintray {
    user = localProperties.getProperty(Bintray.user)
    key = localProperties.getProperty(Bintray.key)
    publish = true
    pkg.apply {
        repo = Bintray.repo
        name = Bintray.name
        userOrg = Bintray.userOrg
        description = Bintray.description
        websiteUrl = Bintray.websiteUrl
        setLicenses(Bintray.licenses)
        issueTrackerUrl = Bintray.issueTrackerUrl
        vcsUrl = Bintray.vscUrl
        version.apply { name = Bintray.version }
        setLabels(Bintray.label1, Bintray.label2, Bintray.label3, Bintray.label4)
    }
    setPublications(Bintray.name)
}

publishing {
    publications {
        create<MavenPublication>(Bintray.name) {
            groupId = Bintray.groupId
            artifactId = Bintray.artifactId
            version = Bintray.version
            artifact(sourcesJar)
            artifact(Bintray.artifactDir)
        }
    }
}