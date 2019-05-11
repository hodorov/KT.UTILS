plugins {
    val kotlinVersion = "1.3.31"

    maven
    id("io.spring.dependency-management") version "1.0.6.RELEASE" apply false
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
}

allprojects {
    group = "ru.hodorov.ktutils"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }

    apply(plugin = "idea")
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")

    tasks.compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}

tasks {
    artifacts {
        archives(jar)
    }
}