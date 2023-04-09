package ru.morozovsu.webapi.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.morozovsu.webapi.model.BrandModel
import ru.morozovsu.webapi.repository.BrandRepository

/**
 * Сервис для работы с маркой автомобиля.
 *
 * @property brandRepository репозиторий для работы с сущностью марки автомобиля.
 */
@Service
@Transactional(readOnly = true)
class BrandService (val brandRepository: BrandRepository) {

    /**
     * Метод получения марки автомобиля по ID.
     *
     * @return DTO для работы с маркой автомобиля.
     */
    fun getBrandById(id: Int): BrandModel = brandRepository.getBrandById(id)
}