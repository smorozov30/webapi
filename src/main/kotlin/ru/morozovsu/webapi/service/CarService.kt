package ru.morozovsu.webapi.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.morozovsu.webapi.dto.CarDto
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


    val mapper = jacksonObjectMapper()

    /**
     * Метод получения Автомобиля по ID.
     *
     * @return DTO для работы с автомобилем.
     */
    fun getCarById(id: Int): CarDto {
        val carDto = carRepository.getCarById(id)
        val carDtoJson = mapper.writeValueAsString(carDto)
        kafkaTemplate.send(CAR_TOPIC, carDtoJson)
        return carDto
    }
}