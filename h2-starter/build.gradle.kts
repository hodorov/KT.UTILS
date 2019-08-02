val h2Version = project.extra.get("h2Version")
val springBootVersion = project.extra.get("springBootVersion")

dependencies {
    compile(project(":base"))
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    // If use runtimeOnly - need to add dep in project, that use this starter
    compile("com.h2database:h2:$h2Version")
}