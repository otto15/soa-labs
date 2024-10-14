package com.soa.products.dao

import com.soa.products.service.command.CreatePersonCommand
import com.soa.products.exception.PersonDuplicationException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class PersonDao(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {
    fun insert(createPersonCommand: CreatePersonCommand): String = try {
        namedParameterJdbcTemplate.queryForObject(
            INSERT_PERSON,
            MapSqlParameterSource()
                .addValue("name", createPersonCommand.name)
                .addValue("passportID", createPersonCommand.passportID)
                .addValue("eyeColor", createPersonCommand.eyeColor?.name)
                .addValue("hairColor", createPersonCommand.hairColor?.name),
            String::class.java
        ) ?: throw IllegalStateException("Failed to retrieve the inserted person passportID")
    } catch (e: DataIntegrityViolationException) {
        throw PersonDuplicationException(createPersonCommand.passportID)
    }

    fun existsById(passportID: String): Boolean = namedParameterJdbcTemplate.queryForObject(
        "SELECT EXISTS(SELECT 1 FROM person WHERE passportID = :passportID)",
        MapSqlParameterSource().addValue("passportID", passportID),
        Boolean::class.java
    ) ?: throw IllegalStateException("Failed to check existence of person by passportId")

    companion object {
        private val INSERT_PERSON = """
            INSERT INTO person (name, passportID, eyeColor, hairColor)
            VALUES (:name, :passportID, :eyeColor::color, :hairColor::color)
            RETURNING passportID;
         """.trimIndent()
    }
}