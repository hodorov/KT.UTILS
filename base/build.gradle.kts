val springVersion = project.extra.get("springVersion")
val springBootVersion = project.extra.get("springBootVersion")
val kotlinLoggingVersion = project.extra.get("kotlinLoggingVersion")
val jacksonVersion = project.extra.get("jacksonVersion")

dependencies {
    compile("org.jetbrains.kotlin:kotlin-reflect")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    compile("org.springframework:spring-beans:$springVersion")
    compile("org.springframework:spring-context:$springVersion")
    compile("org.springframework:spring-context:$springVersion")
    compile("org.springframework.boot:spring-boot-autoconfigure:$springBootVersion")
    compile("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
}