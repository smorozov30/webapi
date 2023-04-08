package ru.morozovsu.webapi.exception

/**
 * Класс исключение для ситуации когда не было найдено данных по запросу клиента.
 *
 * @property message сообщение исключения
 */
class DataNotFoundByIdException(message: String) : RuntimeException(message) {
}