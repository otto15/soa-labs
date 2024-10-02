package com.soa.products.dao

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
    fun findById(id: Int): Product? = try {
        namedParameterJdbcTemplate.queryForObject(
            SELECT_PRODUCT_BY_ID,
            MapSqlParameterSource().addValue("id", id),
            ROW_MAPPER
        )
    } catch (e: EmptyResultDataAccessException) {
        null
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
                p.manufactureCost, 
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
                    val parts = clean.split(", ").map { part -> part.toInt() }

                    Coordinates(parts[0], parts[1])
                },
                creationDate = rs.getDate("creationDate").toLocalDate(),
                price = rs.getLong("price"),
                partNumber = rs.getString("partNumber"),
                manufacturerCost = rs.getLong("manufactureCost"),
                unitOfMeasure = UnitOfMeasure.valueOf(rs.getString("unitOfMeasure")),
                owner = owner
            )
        }
    }
}