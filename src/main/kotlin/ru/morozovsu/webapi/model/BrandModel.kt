package ru.morozovsu.webapi.model

/**
 * DTO для работы с сущностью марки автомобиля.
 *
 * @property id идентификатор марки.
 * @property name наименование марки автомобиля.
 * @property models модели марки автомобиля.
 * @constructor создает DTO марки автомобиля.
 */
data class BrandModel(var id: Int,
                      var name: String,
                      var models: List<ModelModel> = ArrayList())
