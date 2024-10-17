package com.soa.products.dao

import com.soa.products.service.command.CreateProductCommand
import com.soa.products.service.command.UpdateProductCommand
import com.soa.products.domain.Color
import com.soa.products.domain.search.ComparisonOperator
import com.soa.products.domain.Coordinates
import com.soa.products.domain.Person
import com.soa.products.domain.Product
import com.soa.products.domain.ProductSearchParams
import com.soa.products.domain.UnitOfMeasure
import com.soa.products.domain.search.SearchField
import com.soa.products.exception.ProductDuplicationException
import org.springframework.dao.DuplicateKeyException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.math.BigDecimal

@Repository
class ProductDao(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {
    fun insert(createProductCommand: CreateProductCommand): Long = try {
        namedParameterJdbcTemplate.queryForObject(
            INSERT_PRODUCT,
            MapSqlParameterSource()
                .addValue("name", createProductCommand.name)
                .addValue("x", createProductCommand.coordinates.x)
                .addValue("y", createProductCommand.coordinates.y)
                .addValue("creationDate", java.sql.Date.valueOf(java.time.LocalDate.now()))
                .addValue("price", createProductCommand.price)
                .addValue("manufacturerCost", createProductCommand.manufacturerCost)
                .addValue("unitOfMeasure", createProductCommand.unitOfMeasure.name)
                .addValue("partNumber", createProductCommand.partNumber)
                .addValue("ownerPassportId", createProductCommand.ownerPassportId),
            Long::class.java
        ) ?: throw IllegalStateException("Failed to retrieve the inserted product ID")
    } catch (e: DuplicateKeyException) {
        createProductCommand.partNumber?.let {
            throw ProductDuplicationException(it)
        }

        throw e
    }

    fun findById(id: Long): Product? = try {
        namedParameterJdbcTemplate.queryForObject(
            SELECT_PRODUCT_BY_ID,
            MapSqlParameterSource().addValue("id", id),
            ROW_MAPPER
        )
    } catch (e: EmptyResultDataAccessException) {
        null
    }

    fun deleteById(id: Long): Boolean = namedParameterJdbcTemplate.update(
        "DELETE FROM Product WHERE id = :id",
        MapSqlParameterSource().addValue("id", id)
    ) > 0

    fun update(updateProductCommand: UpdateProductCommand): Boolean {
        val params = MapSqlParameterSource()
            .addValue("id", updateProductCommand.id)
            .addValue("name", updateProductCommand.name)
            .addValue("x", updateProductCommand.coordinates.x)
            .addValue("y", updateProductCommand.coordinates.y)
            .addValue("creationDate", java.sql.Date.valueOf(java.time.LocalDate.now()))
            .addValue("price", updateProductCommand.price)
            .addValue("manufacturerCost", updateProductCommand.manufacturerCost)
            .addValue("unitOfMeasure", updateProductCommand.unitOfMeasure.name)
            .addValue("partNumber", updateProductCommand.partNumber)
            .addValue("ownerPassportId", updateProductCommand.ownerPassportId)

        return try {
            namedParameterJdbcTemplate.update(UPDATE_PRODUCT, params) > 0
        } catch (e: DuplicateKeyException) {
            updateProductCommand.partNumber?.let {
                throw ProductDuplicationException(it)
            }

            throw e
        }
    }

    fun getTotalPrice(): Long = namedParameterJdbcTemplate.queryForObject(
        "SELECT COALESCE(SUM(price), 0) FROM Product",
        MapSqlParameterSource(), Long::class.java
    ) ?: 0L

    fun findProductWithMinPartNumber(): Product? = try {
        namedParameterJdbcTemplate.queryForObject(
            SELECT_WITH_MIN_PART_NUMBER,
            MapSqlParameterSource(),
            ROW_MAPPER
        )
    } catch (e: EmptyResultDataAccessException) {
        null
    }

    fun findProductsWithManufacturerCostLessThan(cost: Long, offset: Int, limit: Int): List<Product> =
        namedParameterJdbcTemplate.query(
            SELECT_WITH_MANUFACTURER_COST_LESS_THAN,
            MapSqlParameterSource()
                .addValue("cost", cost)
                .addValue("limit", limit)
                .addValue("offset", offset),
            ROW_MAPPER
        )

    fun find(params: ProductSearchParams): List<Product> {
        val parameterSource = MapSqlParameterSource()
        val sql: String = buildSelectQueryAndExtendParameterSource(parameterSource, params)

        return namedParameterJdbcTemplate.query(
            sql,
            parameterSource,
            ROW_MAPPER
        )
    }

    fun updateProductPrices(coef: BigDecimal) {
        namedParameterJdbcTemplate.update(
            UPDATE_PRODUCT_PRICE,
            MapSqlParameterSource().addValue("coef", coef)
        )
    }

    private fun buildSelectQueryAndExtendParameterSource(
        parameterSource: MapSqlParameterSource,
        searchParams: ProductSearchParams
    ): String {
        val conditions: List<String> = searchParams.filterParams.map {
            val fieldName = SEARCH_FIELD_AND_DB_FIELD_NAME_MAPPING[it.searchField]!!
            val paramPlaceholder: String = when (it.searchField) {
                SearchField.COORDINATES -> {
                    val coordinates = it.value as Coordinates

                    parameterSource.addValue(COORDINATES_X_PARAM_NAME, coordinates.x)
                    parameterSource.addValue(COORDINATES_Y_PARAM_NAME, coordinates.y)

                    "point(:$COORDINATES_X_PARAM_NAME, :$COORDINATES_Y_PARAM_NAME)"
                }

                else -> {
                    val paramName = SEARCH_FIELD_AND_PARAM_NAME_MAPPING[it.searchField]!!
                    parameterSource.addValue(paramName, it.value)
                    ":$paramName"
                }
            }

            buildCondition(fieldName, it.comparisonOperator, paramPlaceholder)
        }

        val wherePart: String = if (conditions.isEmpty()) {
            ""
        } else {
            conditions.joinToString(" AND ", "WHERE ", "")
        }

        val sortPart: String = if (searchParams.sortParams.isEmpty()) {
            ""
        } else {
            searchParams.sortParams.joinToString(", ", "ORDER BY ", "") {
                "${SEARCH_FIELD_AND_DB_FIELD_NAME_MAPPING[it.searchField]} ${it.sortOrder.name}"
            }
        }

        return """
            SELECT
                p.id as p_id,
                p.name as p_name,
                p.coordinates,
                p.creationDate,
                p.price,
                p.partNumber,
                p.manufacturerCost,
                p.unitOfMeasure,
                p.ownerPassportID,
                pe.passportID,
                pe.name as pe_name,
                pe.eyeColor,
                pe.hairColor
            FROM product p
            LEFT JOIN person pe ON p.ownerPassportID = pe.passportID
            $wherePart
            $sortPart
            LIMIT ${searchParams.limit}
            OFFSET ${searchParams.offset}
        """.trimIndent()
    }

    private fun buildCondition(fieldName: String, operator: ComparisonOperator, paramPlaceholder: String): String {
        val dbOperator: String = when (operator) {
            ComparisonOperator.EQUAL -> "="
            ComparisonOperator.GREATER -> ">"
            ComparisonOperator.GREATER_OR_EQUAL -> ">="
            ComparisonOperator.LESS -> "<"
            ComparisonOperator.LESS_OR_EQUAL -> "<="
            ComparisonOperator.SAME -> "~="
        }

        return "$fieldName $dbOperator $paramPlaceholder"
    }

    companion object {
        const val ID_FIELD = "p.id"
        const val NAME_FIELD = "p.name"
        const val COORDINATES_FIELD = "p.coordinates"
        const val CREATION_DATE_FIELD = "p.creationDate"
        const val PRICE_FIELD = "p.price"
        const val PART_NUMBER_FIELD = "p.partNumber"
        const val MANUFACTURER_COST_FIELD = "p.manufacturerCost"
        const val UNIT_OF_MEASURE_FIELD = "p.unitOfMeasure"
        const val OWNER_PASSPORT_ID_FIELD = "p.ownerPassportId"

        const val ID_PARAM_NAME = "id"
        const val NAME_PARAM_NAME = "name"
        const val COORDINATES_X_PARAM_NAME = "x"
        const val COORDINATES_Y_PARAM_NAME = "y"
        const val CREATION_DATE_PARAM_NAME = "creationDate"
        const val PRICE_PARAM_NAME = "price"
        const val PART_NUMBER_PARAM_NAME = "partNumber"
        const val MANUFACTURER_COST_PARAM_NAME = "manufacturerCost"
        const val UNIT_OF_MEASURE_PARAM_NAME = "unitOfMeasure"
        const val OWNER_PASSPORT_PARAM_NAME = "ownerPassportId"

        private val SEARCH_FIELD_AND_DB_FIELD_NAME_MAPPING: Map<SearchField, String> = mapOf(
            SearchField.ID to ID_FIELD,
            SearchField.NAME to NAME_FIELD,
            SearchField.COORDINATES to COORDINATES_FIELD,
            SearchField.CREATION_DATE to CREATION_DATE_FIELD,
            SearchField.PRICE to PRICE_FIELD,
            SearchField.PART_NUMBER to PART_NUMBER_FIELD,
            SearchField.MANUFACTURER_COST to MANUFACTURER_COST_FIELD,
            SearchField.UNIT_OF_MEASURE to UNIT_OF_MEASURE_FIELD,
            SearchField.OWNER_PASSPORT_ID to OWNER_PASSPORT_ID_FIELD,
        )

        private val SEARCH_FIELD_AND_PARAM_NAME_MAPPING: Map<SearchField, String> = mapOf(
            SearchField.ID to ID_PARAM_NAME,
            SearchField.NAME to NAME_PARAM_NAME,
            SearchField.CREATION_DATE to CREATION_DATE_PARAM_NAME,
            SearchField.PRICE to PRICE_PARAM_NAME,
            SearchField.PART_NUMBER to PART_NUMBER_PARAM_NAME,
            SearchField.MANUFACTURER_COST to MANUFACTURER_COST_PARAM_NAME,
            SearchField.UNIT_OF_MEASURE to UNIT_OF_MEASURE_PARAM_NAME,
            SearchField.OWNER_PASSPORT_ID to OWNER_PASSPORT_PARAM_NAME,
        )

        private val INSERT_PRODUCT = """
            INSERT INTO product (
                name, 
                coordinates, 
                creationDate, 
                price, 
                manufacturerCost, 
                unitOfMeasure, 
                partNumber, 
                ownerPassportID
            )
            VALUES (
                :name, 
                point(:x, :y), 
                :creationDate, 
                :price, 
                :manufacturerCost, 
                :unitOfMeasure::unit_of_measure, 
                :partNumber, 
                :ownerPassportId
            )
            RETURNING id;
        """.trimIndent()

        private val SELECT_PRODUCT_BY_ID = """
            SELECT 
                p.id as p_id, 
                p.name as p_name, 
                p.coordinates, 
                p.creationDate, 
                p.price, 
                p.partNumber, 
                p.manufacturerCost, 
                p.unitOfMeasure, 
                p.ownerPassportID, 
                pe.passportID, 
                pe.name as pe_name, 
                pe.eyeColor, 
                pe.hairColor
            FROM product p
            LEFT JOIN person pe ON p.ownerPassportID = pe.passportID
            WHERE p.id = :id
            """.trimIndent()

        private val UPDATE_PRODUCT = """
            UPDATE product
            SET
                name = :name,
                coordinates = point(:x, :y),
                price = :price,
                manufacturerCost = :manufacturerCost,
                unitOfMeasure = :unitOfMeasure::unit_of_measure,
                partNumber = :partNumber,
                ownerPassportId = :ownerPassportId
            WHERE id = :id
        """.trimIndent()

        private val SELECT_WITH_MANUFACTURER_COST_LESS_THAN = """
            SELECT 
                p.id as p_id, 
                p.name as p_name, 
                p.coordinates, 
                p.creationDate, 
                p.price, 
                p.partNumber, 
                p.manufacturerCost, 
                p.unitOfMeasure, 
                p.ownerPassportID, 
                pe.passportID, 
                pe.name as pe_name, 
                pe.eyeColor, 
                pe.hairColor
            FROM product p
            LEFT JOIN person pe ON p.ownerPassportID = pe.passportID
            WHERE p.manufacturerCost < :cost
            ORDER BY p.manufacturerCost
            LIMIT :limit OFFSET :offset
        """.trimIndent()

        private val SELECT_WITH_MIN_PART_NUMBER = """
            SELECT 
                p.id as p_id, 
                p.name as p_name, 
                p.coordinates, 
                p.creationDate, 
                p.price, 
                p.partNumber, 
                p.manufacturerCost, 
                p.unitOfMeasure, 
                p.ownerPassportID, 
                pe.passportID, 
                pe.name as pe_name, 
                pe.eyeColor, 
                pe.hairColor
            FROM product p
            LEFT JOIN person pe ON p.ownerPassportID = pe.passportID
            WHERE p.partNumber = (SELECT MIN(partNumber) FROM product)
        """.trimIndent()

        private val UPDATE_PRODUCT_PRICE = """
            UPDATE product
            SET price = price * :coef
        """.trimIndent()

        private val ROW_MAPPER = RowMapper { rs, _ ->
            val passportID = rs.getString("passportID")
            val owner: Person? = passportID?.let {
                Person(
                    passportID = passportID,
                    name = rs.getString("pe_name"),
                    eyeColor = rs.getString("eyeColor")?.let { Color.valueOf(it) },
                    hairColor = rs.getString("hairColor")?.let { Color.valueOf(it) }
                )
            }

            Product(
                id = rs.getLong("p_id"),
                name = rs.getString("p_name"),
                coordinates = rs.getString("coordinates").let {
                    val clean = it.removeSurrounding("(", ")")
                    val parts = clean.split(",").map { part -> part.toInt() }

                    Coordinates(parts[0], parts[1])
                },
                creationDate = rs.getDate("creationDate").toLocalDate(),
                price = rs.getLong("price"),
                partNumber = rs.getString("partNumber"),
                manufacturerCost = rs.getLong("manufacturerCost"),
                unitOfMeasure = UnitOfMeasure.valueOf(rs.getString("unitOfMeasure")),
                owner = owner
            )
        }
    }
}
