package ru.hodorov.ktutils.fx

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.Appender
import ch.qos.logback.core.OutputStreamAppender
import javafx.scene.control.TextArea
import org.slf4j.LoggerFactory

public object FXLogger {
    public fun init(textArea: TextArea): Appender<ILoggingEvent> {
        val rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
        val os = TextAreaOutputStream(textArea)

        val ple = PatternLayoutEncoder()
        ple.pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %5p [%15.15t] %-40.40logger{39} : %m%n%wEx"
        ple.context = loggerContext
        ple.start()

        val appender = OutputStreamAppender<ILoggingEvent>()
        appender.outputStream = os
        appender.encoder = ple
        appender.context = loggerContext
        appender.start()

        rootLogger.addAppender(appender)
        os.write("Logger started${System.lineSeparator()}".toByteArray())

        return appender
    }
}