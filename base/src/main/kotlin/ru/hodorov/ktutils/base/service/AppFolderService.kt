package ru.hodorov.ktutils.base.service

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import javax.annotation.PostConstruct

@Service
class AppFolderService {
    private val log = KotlinLogging.logger { }

    @Value("\${spring.application.name:#{null}}")
    private var springApplicationName: String? = null

    private lateinit var appFolder: File
    // Super getter
    fun getAppFolder(): File = appFolder

    @PostConstruct
    fun postConstruct() {
        val appFolderName = springApplicationName ?: throw IllegalStateException("Please, specify 'spring.application.name' property")

        val os = System.getProperty("os.name").toUpperCase()
        log.debug { "OS: $os" }
        val appFolderPath = when {
            os.contains("WIN") -> {
                log.debug { "Found windows" }
                "${System.getenv("APPDATA")}\\$appFolderName"
            }
            os.contains("MAC") -> {
                log.debug { "Found mac" }
                "${System.getProperty("user.home")}/Library/Application Support/$appFolderName"
            }
            os.contains("NUX") -> {
                log.debug { "Found linux" }
                "${System.getProperty("user.dir")}/.$appFolderName"
            }
            else -> throw IllegalStateException("Unknown OS: $os")
        }

        appFolder = File(appFolderPath)

        if (!appFolder.exists()) {
            log.debug { "App folder not fount. Creating" }
            appFolder.mkdirs()
        }
    }

    fun getFile(filename: String, autoCreate: Boolean = true): File {
        val file = File("${appFolder.canonicalPath}/$filename")
        if(autoCreate && !file.exists()) {
            file.createNewFile()
        }
        return file
    }
}