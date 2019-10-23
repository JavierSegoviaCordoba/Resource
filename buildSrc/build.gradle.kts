plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
}

tasks {
    withType<Delete> { delete("out") }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}