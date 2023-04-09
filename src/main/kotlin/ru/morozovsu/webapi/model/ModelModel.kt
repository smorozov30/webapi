package ru.morozovsu.webapi.model

/**
 * DTO для работы с сущностью модели автомобиля.
 *
 * @property id идентификатор модели.
 * @property name наименование модели автомобиля.
 * @constructor создает DTO модели автомобиля.
 */
data class ModelModel(var id: Int,
                      var name: String)
