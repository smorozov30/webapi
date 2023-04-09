package ru.morozovsu.webapi.model

/**
 * DTO для работы с сущностью автомобиля.
 *
 * @property id идентификатор автомобиля.
 * @property brand марка автомобиля.
 * @property model модель автомобиля.
 * @constructor создает DTO автомобиля.
 */
data class CarModel(var id: Int,
                    var brand: BrandModel,
                    var model: ModelModel)
