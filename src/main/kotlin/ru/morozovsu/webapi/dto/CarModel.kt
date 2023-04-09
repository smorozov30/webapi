package ru.morozovsu.webapi.dto

/**
 * DTO для работы с сущностью автомобиля.
 *
 * @property id идентификатор автомобиля.
 * @property make марка автомобиля.
 * @property model модель автомобиля.
 * @constructor создает DTO автомобиля.
 */
data class CarModel(var id: Int,
                    var make: String,
                    var model: String)
