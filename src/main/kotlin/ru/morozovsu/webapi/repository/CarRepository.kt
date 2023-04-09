package ru.morozovsu.webapi.repository

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.morozovsu.webapi.dto.CarDto
import ru.morozovsu.webapi.exception.DataNotFoundByIdException
import ru.morozovsu.webapi.jooq.Tables.CAR
import ru.morozovsu.webapi.jooq.Tables.MAKE
import ru.morozovsu.webapi.jooq.Tables.MODEL

/**
 * Репозиторий для работы с сущностью Автомобиля.
 *
 * @property dsl объект создающий запросы для последующего выполнения.
 * @constructor создает объект репозитория по переданному параметру dsl.
 */
@Repository
class CarRepository(private val dsl: DSLContext) {

    /**
     * Функция получения автомобиля по переданному пользователем ID.
     *
     * @return DTO автомобиля или исключение если по ID не было найдено данных.
     */
    fun getCarById(id: Int): CarDto {
        return dsl.select(
                CAR.ID, MAKE.NAME, MODEL.NAME
        )
                .from(CAR)
                .leftJoin(MAKE).on(MAKE.ID.eq(CAR.MAKE_ID))
                .leftJoin(MODEL).on(MODEL.ID.eq(CAR.MODEL_ID))
                .where(CAR.ID.eq(id))
                .fetchOne()
                ?.map {row ->
                    CarDto(
                            row.get(CAR.ID),
                            row.get(MAKE.NAME),
                            row.get(MODEL.NAME)
                    )
                // todo вынесети текст сообщения для исключения в ресурсы и использовать MessageSource.
                } ?: throw DataNotFoundByIdException("Car was not found by id: $id")
    }
}