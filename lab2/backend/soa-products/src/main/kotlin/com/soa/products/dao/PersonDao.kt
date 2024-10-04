package com.soa.products.dao

import com.soa.products.command.CreatePersonCommand
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class PersonDao(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {
    fun insert(createPersonCommand: CreatePersonCommand): String {
        val sql = """
            INSERT INTO person 
            (name, passportID, eyeColor, hairColor)
            VALUES (:name, :passportID, :eyeColor::color, :hairColor::color)
            RETURNING passportID;
        """.trimIndent()

        val params = MapSqlParameterSource()
            .addValue("name", createPersonCommand.name)
            .addValue("passportID", createPersonCommand.passportID)
            .addValue("eyeColor", createPersonCommand.eyeColor?.name)
            .addValue("hairColor", createPersonCommand.hairColor?.name)

        return namedParameterJdbcTemplate.queryForObject(sql, params, String::class.java)
            ?: throw IllegalStateException("Failed to retrieve the inserted person passportID")
    }

    fun existsById(passportID: String): Boolean {
        val sql = """
            SELECT EXISTS(
                SELECT 1
                FROM person
                WHERE passportID = :passportID
            )
        """.trimIndent()

        val params = MapSqlParameterSource()
            .addValue("passportID", passportID)

        return namedParameterJdbcTemplate.queryForObject(sql, params, Boolean::class.java)
            ?: throw IllegalStateException("Failed to check existence of person by passportId")
    }
}