plugins {
    kotlin("jvm") version Version.kotlin
    kotlin("plugin.serialization") version Version.kotlin
}

description = "API testing example"

dependencies {
    implementation(project(":library"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.Dependency.kotlinSerialisation}")

    implementation("io.cucumber:cucumber-java:${Version.Test.cucumber}")
    implementation("io.cucumber:cucumber-java8:${Version.Test.cucumber}")
    implementation("io.cucumber:cucumber-junit:${Version.Test.cucumber}")
}
