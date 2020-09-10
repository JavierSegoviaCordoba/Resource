import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.github.ben-manes.versions")
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf { candidate.version.isNonStable }
}

internal val String.isNonStable: Boolean
    get() = listOf("eap", "dev", "alpha", "beta", "rc").any { it.contains(this, true) }
