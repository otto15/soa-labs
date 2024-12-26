package com.soa.ebay.logging.dao

import com.soa.ebay.logging.model.Log
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.sql.Types

@Repository
abstract class AccessLogDao(
    protected val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {
    fun insert(log: Log) = namedParameterJdbcTemplate.update(
        INSERT_LOG,
        MapSqlParameterSource()
            .addValue("timestamp", Timestamp.from(log.timestamp), Types.TIMESTAMP)
            .addValue("request_method", log.requestMethod)
            .addValue("request_url", log.requestUrl)
            .addValue("status", log.status)
    )

    abstract fun findLatestTen(): List<Log>

    companion object {
        private val INSERT_LOG = """
            INSERT INTO access_log (timestamp, request_method, request_url, status)
            VALUES (:timestamp, :request_method, :request_url, :status);
         """.trimIndent()
    }
}
