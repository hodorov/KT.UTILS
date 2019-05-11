package ru.hodorov.ktutils.base.autoconfigure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonKotlinConfiguration {

    @Bean
    fun objectMapper() : ObjectMapper {
        val om = ObjectMapper()
        om.registerModule(KotlinModule())
        return om
    }
}