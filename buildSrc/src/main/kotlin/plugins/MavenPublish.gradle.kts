@file:Suppress("MaxLineLength")

plugins {
    id("maven-publish")
    `java-library`
    signing
}

java {
    withJavadocJar()
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

val isResourceReleaseEnv: Boolean? = System.getenv("isResourceReleaseEnv")?.toBoolean()
val isResourceRelease: String by project

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("Resource")
            description.set("Resource multiplatform utilities")
            url.set("http://github.com/JavierSegoviaCordoba/Resource")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set("JavierSegoviaCordoba")
                    name.set("Javier Segovia Cordoba")
                    email.set("javiersegoviacordoba@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/JavierSegoviaCordoba/Resource")
                connection.set("scm:git:https://github.com/JavierSegoviaCordoba/Resource.git")
                developerConnection.set("scm:git:git@github.com:JavierSegoviaCordoba/Resource.git")
            }
        }
        repositories {
            val releasesRepo = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
            val snapshotsRepo = "https://oss.sonatype.org/content/repositories/snapshots"

            val isRelease = isResourceReleaseEnv ?: isResourceRelease.toBoolean()

            maven(if (isRelease) releasesRepo else snapshotsRepo) {
                credentials {
                    username = System.getenv("ossUser")
                    password = System.getenv("ossToken")
                }
            }
        }
    }
}
