package ru.morozovsu.webapi.exception

/**
 * Описание сообщения для клиента в случае не найденных данных.
 *
 * @property status http-статус ответа
 * @property message сообщение ответа
 */
data class DataNotFoundMessage(var status: Int?,
                               var message: String?
)