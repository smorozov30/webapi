package ru.morozovsu.webapi.exception

/**
 * Описание сообщения для клиента в случае не найденных данных.
 *
 * @property status http-статус ответа
 * @property message сообщение ответа
 */
data class DataNotFoundMessage(
        private var status: Int? = null,
        private var message: String? = null
)