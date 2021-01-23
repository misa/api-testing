plugins {
    kotlin("jvm") version Version.kotlin
}

description = "API testing helpers"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}
