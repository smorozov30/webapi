package ru.morozovsu.webapi.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.morozovsu.webapi.model.CarModel
import ru.morozovsu.webapi.model.CarSimpleModel
import ru.morozovsu.webapi.help.KafkaConstants.Companion.CAR_SIMPLE_TOPIC
import ru.morozovsu.webapi.help.KafkaConstants.Companion.CAR_TOPIC
import ru.morozovsu.webapi.repository.CarRepository

/**
 * Сервис для работы с "Автомобилями".
 *
 * @property carRepository репозиторий для работы с сущностью автомобиля.
 */
@Service
@Transactional(readOnly = true)
class CarService(val carRepository: CarRepository,
                 val kafkaTemplate: KafkaTemplate<String, String>) {

    /**
     * Маппер можели автомобиля в json.
     */
    val mapper = jacksonObjectMapper()

    /**
     * Метод получения описания автомобиля по ID.
     *
     * @return DTO описания для работы с автомобилем.
     */
    fun getCarSimpleInfoById(id: Int): CarSimpleModel {
        val carSimpleModel = carRepository.getCarSimpleInfoById(id)
        val carModelJson = mapper.writeValueAsString(carSimpleModel)
        kafkaTemplate.send(CAR_SIMPLE_TOPIC, carModelJson)
        return carSimpleModel
    }

    /**
     * Метод получения автомобиля по ID.
     *
     * @return DTO для работы с автомобилем.
     */
    fun getCarById(id: Int): CarModel {
        val carModel = carRepository.getCarById(id)
        val carModelJson = mapper.writeValueAsString(carModel)
        kafkaTemplate.send(CAR_TOPIC, carModelJson)
        return carModel
    }
}