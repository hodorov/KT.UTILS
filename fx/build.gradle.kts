val logbackVersion = project.extra.get("logbackVersion")

dependencies {
    compile(project(":base"))
    compile("ch.qos.logback:logback-classic:$logbackVersion")
}