package ru.morozovsu.webapi.consumer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import ru.morozovsu.webapi.help.KafkaConstants

/**
 * Класс слушатель сообщений из очередей kafka.
 * todo удалить поле написания тестов
 */
@Component
class KafkaConsumer {

    private val logger: Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics = [KafkaConstants.CAR_SIMPLE_TOPIC], groupId = "car_group_id")
    fun consumeSimpleCarInfo(@Payload message: String,
                @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String
    ) {
        logger.info(String.format("#### -> SimpleCarInfo: Consumed message -> %s; topic: %s;", message, topic))
    }

    @KafkaListener(topics = [KafkaConstants.CAR_TOPIC], groupId = "car_group_id")
    fun consumeCarInfo(@Payload message: String,
                @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String
    ) {
        logger.info(String.format("#### -> CarInfo: Consumed message -> %s; topic: %s;", message, topic))
    }
}