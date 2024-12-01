plugins {
    kotlin("jvm") version "2.0.0"
}

var projectName = "Codex"
group = "net.runemc"
version = "v0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.netty:netty-all:4.1.96.Final")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "net.runemc.MainKt"
    }
    //destinationDirectory.set(file("X:/JARs"))
    archiveFileName.set("$projectName-$version.jar")
}
kotlin {
    jvmToolchain(21)
}