package ru.morozovsu.webapi.controller

import org.springframework.web.bind.annotation.*
import ru.morozovsu.webapi.dto.CarModel
import ru.morozovsu.webapi.service.CarService

/**
 * Контроллер для работы с сущностью автомобиля.
 *
 * @property carService сервисный слой с бизнес-логикой для работы с автомобилем.
 */
@RestController
@RequestMapping("api/v1/cars")
class CarController(val carService: CarService) {

    /**
     * Метод получения автомобиля по переданному ID.
     *
     * @param id переданный клиентом ID для поиска автомобиля.
     * @return DTO с данными автомобиля.
     */
    @GetMapping("/{id}")
    fun getCar(@PathVariable id: Int): CarModel {
        return carService.getCarById(id)
    }
}