package ru.morozovsu.webapi.model

/**
 * DTO для работы с сущностью автомобиля.
 *
 * @property id идентификатор автомобиля.
 * @property brand наименование марки автомобиля.
 * @property model наименование модели автомобиля.
 * @constructor создает DTO автомобиля.
 */
data class CarSimpleModel(var id: Int,
                          var brand: String,
                          var model: String)
