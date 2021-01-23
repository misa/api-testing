plugins {
    kotlin("jvm") version Version.kotlin
}

description = "API testing helpers"

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${Version.Test.junit5}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${Version.Test.junit5}")
}
