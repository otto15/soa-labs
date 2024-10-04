package com.soa.products.dao

import com.soa.products.command.CreateProductCommand
import com.soa.products.domain.Color
import com.soa.products.domain.Coordinates
import com.soa.products.domain.Person
import com.soa.products.domain.Product
import com.soa.products.domain.UnitOfMeasure
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ProductDao(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {
    fun insert(createProductCommand: CreateProductCommand): Long {
        val sql = """
            INSERT INTO Product 
            (name, coordinates, creationDate, price, manufacturerCost, unitOfMeasure, partNumber, ownerPassportID)
            VALUES (:name, point(:x, :y), :creationDate, :price, :manufacturerCost, :unitOfMeasure::unit_of_measure, :partNumber, :ownerPassportId)
            RETURNING id;
        """.trimIndent()

        val params = MapSqlParameterSource()
            .addValue("name", createProductCommand.name)
            .addValue("x", createProductCommand.coordinates.x)
            .addValue("y", createProductCommand.coordinates.y)
            .addValue("creationDate", java.sql.Date.valueOf(java.time.LocalDate.now()))
            .addValue("price", createProductCommand.price)
            .addValue("manufacturerCost", createProductCommand.manufacturerCost)
            .addValue("unitOfMeasure", createProductCommand.unitOfMeasure.name)
            .addValue("partNumber", createProductCommand.partNumber)
            .addValue("ownerPassportId", createProductCommand.ownerPassportId)

        return namedParameterJdbcTemplate.queryForObject(sql, params, Long::class.java)
            ?: throw IllegalStateException("Failed to retrieve the inserted product ID")
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

    fun deleteById(id: Long): Boolean {
        val sql = """
            DELETE FROM Product WHERE id = :id
        """.trimIndent()

        val params = MapSqlParameterSource().addValue("id", id)

        return namedParameterJdbcTemplate.update(sql, params) > 0
    }

    fun getTotalPrice(): Long {
        val sql = """
            SELECT COALESCE(SUM(price), 0) FROM Product
        """.trimIndent()

        return namedParameterJdbcTemplate.queryForObject(sql, MapSqlParameterSource(), Long::class.java)
            ?: 0L
    }

    fun findProductWithMinPartNumber(): Product? {
        val sql = """
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

        return try {
            namedParameterJdbcTemplate.queryForObject(sql, MapSqlParameterSource(), ROW_MAPPER)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun findProductsWithManufacturerCostLessThan(cost: Long, offset: Long, limit: Long): List<Product> {
        val sql = """
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

        val params = MapSqlParameterSource()
            .addValue("cost", cost)
            .addValue("limit", limit)
            .addValue("offset", offset)

        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER)
    }


    companion object {
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