package ru.morozovsu.webapi.repository

import org.jooq.DSLContext
import org.jooq.impl.DSL.*
import org.springframework.stereotype.Repository
import ru.morozovsu.webapi.model.BrandModel
import ru.morozovsu.webapi.model.ModelModel
import ru.morozovsu.webapi.exception.DataNotFoundByIdException
import ru.morozovsu.webapi.jooq.Tables.*

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
                BRAND.ID,
                BRAND.NAME,
                multiset(
                        select(MODEL.ID, MODEL.NAME)
                                .from(MODEL)
                                .leftJoin(BRAND_MODEL)
                                .on(BRAND_MODEL.MODEL_ID.eq(MODEL.ID))
                                .where(BRAND_MODEL.BRAND_ID.eq(id))
                ).`as`("models").convertFrom(List::class.java) {
                    it?.map { row ->
                    ModelModel(
                            row.get(MODEL.ID),
                            row.get(MODEL.NAME))
                    }
                })
                .from(BRAND)
                .where(BRAND.ID.eq(id))
                .fetchOneInto(BrandModel::class.java)
                ?: throw DataNotFoundByIdException("Brand was not found by id: $id")
    }
}