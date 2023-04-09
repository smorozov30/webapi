package ru.morozovsu.webapi.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.morozovsu.webapi.exception.DataNotFoundByIdException
import ru.morozovsu.webapi.exception.DataNotFoundMessage

/**
 * Глобальный обработчик исключений.
 */
@RestControllerAdvice
class ExceptionControllerAdvice {

    /**
     * Перехватчик исключений типа DataNotFoundByIdException.
     *
     * @return http-ответ для пользователя со статусом 404, если данные по запросу не были найдены.
     */
    @ExceptionHandler
    fun handleIllegalStateException(exception: DataNotFoundByIdException): ResponseEntity<DataNotFoundMessage> {
        val errorMessage = DataNotFoundMessage(
                HttpStatus.NOT_FOUND.value(),
                exception.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}