package ru.morozovsu.webapi.repository

import org.jooq.DSLContext
import org.jooq.Record1
import org.jooq.impl.DSL.*
import org.springframework.stereotype.Repository
import ru.morozovsu.webapi.dto.BrandModel
import ru.morozovsu.webapi.dto.CarModel
import ru.morozovsu.webapi.dto.ModelModel
import ru.morozovsu.webapi.exception.DataNotFoundByIdException
import ru.morozovsu.webapi.jooq.Tables
import ru.morozovsu.webapi.jooq.Tables.BRAND_MODEL
import ru.morozovsu.webapi.jooq.Tables.MODEL


/**
 * Репозиторий для работы с сущностью марки втомобиля.
 *
 * @property dsl объект создающий запросы для последующего выполнения.
 * @constructor создает объект репозитория по переданному параметру dsl.
 */
@Repository
class BrandRepository(private val dsl: DSLContext) {

    /**
     * Функция получения марки автомобиля по переданному пользователем ID.
     *
     * @return DTO марки автомобиля или исключение если по ID не было найдено данных.
     */
    fun getBrandById(id: Int): BrandModel {
        return dsl.select(
                Tables.BRAND.ID, Tables.BRAND.NAME,
                multiset(
                        select(MODEL.ID, MODEL.NAME)
                                .from(MODEL)
                                .leftJoin(BRAND_MODEL)
                                .on(BRAND_MODEL.MODEL_ID.eq(MODEL.ID))
                                .where(BRAND_MODEL.BRAND_ID.eq(id))
                ).`as`("models").convertFrom(List::class.java) {
                    it?.map {row ->
                    ModelModel(
                            row.get(MODEL.ID),
                            row.get(MODEL.NAME)
                    ) }
                })
                .from(Tables.BRAND)
                .where(Tables.BRAND.ID.eq(id))
                .fetchOneInto(BrandModel::class.java)
                ?: throw DataNotFoundByIdException("Brand was not found by id: $id")
    }
}