package ru.morozovsu.webapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.morozovsu.webapi.dto.BrandModel
import ru.morozovsu.webapi.service.BrandService

/**
 * Контроллер для работы с сущностью марки автомобиля.
 *
 * @property brandService сервисный слой с бизнес-логикой для работы с маркой автомобиля.
 */
@RestController
@RequestMapping("api/v1/brands")
class BrandController(val brandService: BrandService) {

    /**
     * Метод получения марки автомобиля с моделями по переданному ID.
     *
     * @param id переданный клиентом ID для поиска марки.
     * @return DTO с данными марки автомобиля.
     */
    @GetMapping("/{id}")
    fun getCar(@PathVariable id: Int): BrandModel {
        return brandService.getBrandById(id)
    }
}