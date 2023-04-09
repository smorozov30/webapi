package ru.morozovsu.webapi.repository

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.morozovsu.webapi.model.BrandModel
import ru.morozovsu.webapi.model.CarModel
import ru.morozovsu.webapi.model.CarSimpleModel
import ru.morozovsu.webapi.model.ModelModel
import ru.morozovsu.webapi.exception.DataNotFoundByIdException
import ru.morozovsu.webapi.jooq.Tables.CAR
import ru.morozovsu.webapi.jooq.Tables.BRAND
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
     * Функция получения описания автомобиля по переданному пользователем ID.
     *
     * @return DTO описания автомобиля или исключение если по ID не было найдено данных.
     */
    fun getCarSimpleInfoById(id: Int): CarSimpleModel {
        return dsl.select(
                CAR.ID, BRAND.NAME, MODEL.NAME
        )
                .from(CAR)
                .leftJoin(BRAND).on(BRAND.ID.eq(CAR.BRAND_ID))
                .leftJoin(MODEL).on(MODEL.ID.eq(CAR.MODEL_ID))
                .where(CAR.ID.eq(id))
                .fetchOne()
                ?.map {row ->
                    CarSimpleModel(
                            row.get(CAR.ID),
                            row.get(BRAND.NAME),
                            row.get(MODEL.NAME)
                    )
                // todo вынесети текст сообщения для исключения в ресурсы и использовать MessageSource.
                } ?: throw DataNotFoundByIdException("Car description was not found by id: $id")
    }

    /**
     * Функция получения автомобиля со всеми данными по переданному пользователем ID.
     *
     * @return DTO автомобиля или исключение если по ID не было найдено данных.
     */
    fun getCarById(id: Int): CarModel {
        return dsl.select(
                    CAR.ID, BRAND.ID, BRAND.NAME, MODEL.ID, MODEL.NAME)
                .from(CAR)
                .leftJoin(BRAND).on(BRAND.ID.eq(CAR.BRAND_ID))
                .leftJoin(MODEL).on(MODEL.ID.eq(CAR.MODEL_ID))
                .where(CAR.ID.eq(id))
                .fetchOne()
                ?.map {row ->
                    CarModel(
                            row.get(CAR.ID),
                            BrandModel(row.get(BRAND.ID), row.get(BRAND.NAME)),
                            ModelModel(row.get(MODEL.ID), row.get(MODEL.NAME))
                    )
        } ?: throw DataNotFoundByIdException("Car was not found by id: $id")
    }
}