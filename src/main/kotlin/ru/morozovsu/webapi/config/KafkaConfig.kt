package ru.morozovsu.webapi.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Класс конфигурации Kafka.
 */
@Configuration
class KafkaConfig {

    @Bean
    fun topic1() = NewTopic("car_simple_topic", 1, 1)

    @Bean
    fun topic2() = NewTopic("car_topic", 1, 1)
}