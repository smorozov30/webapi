package ru.morozovsu.webapi.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig {

    @Bean
    fun topic() = NewTopic("car_topic", 1, 1)
}