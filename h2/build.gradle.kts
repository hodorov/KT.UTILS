val h2Version = project.extra.get("h2Version")

dependencies {
    compile(project(":base"))
    runtimeOnly("com.h2database:h2:$h2Version")
}