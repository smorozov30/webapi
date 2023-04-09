package ru.morozovsu.webapi.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

/**
 * Класс конфигурации Jackson.
 */
@Configuration
class JacksonConfiguration {

    @Bean
    fun objectMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper {
        val objectMapper = builder.build<ObjectMapper>()
        objectMapper.findAndRegisterModules()
        return objectMapper
    }
}