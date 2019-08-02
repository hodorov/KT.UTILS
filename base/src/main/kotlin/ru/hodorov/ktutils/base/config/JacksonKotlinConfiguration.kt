package ru.hodorov.ktutils.base.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import javax.annotation.PostConstruct

@Configuration
class JacksonStubConfiguration {
    @Bean
    @ConditionalOnMissingBean
    fun objectMapper(): ObjectMapper = ObjectMapper()
}

@Configuration
@DependsOn("jacksonStubConfiguration")
class JacksonKotlinConfiguration(val objectMapper: ObjectMapper) {
    @PostConstruct
    private fun postConstruct() {
        objectMapper.registerModule(KotlinModule())
    }
}