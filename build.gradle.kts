plugins {
    val kotlinVersion = "1.3.31"

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
    apply(plugin = "maven")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")

    tasks.compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    tasks{
        artifacts {
            archives(jar)
        }
    }

    extra.apply {
        set("springVersion", "5.1.7.RELEASE")
        set("springBootVersion", "2.1.6.RELEASE")
        set("logbackVersion", "1.2.3")
        set("kotlinLoggingVersion", "1.6.24")
        set("jacksonVersion", "2.9.8")
        set("h2Version", "1.4.199")
    }
}