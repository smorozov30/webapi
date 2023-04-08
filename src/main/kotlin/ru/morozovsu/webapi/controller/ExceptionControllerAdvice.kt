package ru.morozovsu.webapi.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.morozovsu.webapi.exception.DataNotFoundByIdException
import ru.morozovsu.webapi.exception.DataNotFoundMessage

/**
 * Глобальный обработчик исключений.
 */
@ControllerAdvice
class ExceptionControllerAdvice {

    /**
     * Перехватчик исключений типа DataNotFoundByIdException.
     *
     * @return http-ответ для пользователя со статусом 404, если данные по запросу не были найдены.
     */
    @ExceptionHandler
    fun handleIllegalStateException(ex: DataNotFoundByIdException): ResponseEntity<DataNotFoundMessage> {
        val errorMessage = DataNotFoundMessage(
                HttpStatus.NOT_FOUND.value(),
                ex.message
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}