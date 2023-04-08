package ru.morozovsu.webapi.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.morozovsu.webapi.dto.CarDto
import ru.morozovsu.webapi.repository.CarRepository

/**
 * Сервис для работы с "Автомобилями".
 *
 * @property carRepository репозиторий для работы с сущностью автомобиля.
 */
@Service
@Transactional(readOnly = true)
class CarService(val carRepository: CarRepository) {

    /**
     * Метод получения Автомобиля по ID.
     *
     * @return DTO для работы с автомобилем.
     */
    fun getCarById(id: Int): CarDto {
        return carRepository.getCarById(id)
    }
}