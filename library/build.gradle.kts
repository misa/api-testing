plugins {
    kotlin("jvm") version Version.kotlin
    kotlin("plugin.serialization") version Version.kotlin
}

description = "API testing helpers"

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.Dependency.kotlinSerialisation}")
    implementation("com.squareup.okhttp3:okhttp:${Version.Dependency.okhttp}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${Version.Test.junit5}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Version.Test.junit5}")
    testImplementation("com.squareup.okhttp3:mockwebserver:${Version.Dependency.okhttp}")
}
