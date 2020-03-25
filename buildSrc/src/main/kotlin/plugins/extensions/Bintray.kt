package plugins.extensions

import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.getByType

internal inline fun Project.bintray(block: BintrayExtension.() -> Unit) {
    val bintrayExtension = this.extensions.getByType<BintrayExtension>()
    return block(bintrayExtension)
}

internal inline fun Project.publishing(block: PublishingExtension.() -> Unit) {
    val publishingExtension = this.extensions.getByType<PublishingExtension>()
    return block(publishingExtension)
}