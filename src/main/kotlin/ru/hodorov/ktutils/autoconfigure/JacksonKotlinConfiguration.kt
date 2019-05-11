package ru.hodorov.ktutils.autoconfigure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonKotlinConfiguration {

    @Bean
    fun objectMapper() : ObjectMapper {
        val om = objectMapper()
        om.registerModule(KotlinModule())
        return om
    }
}