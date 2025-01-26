package com.soa.ebay.logging.dao

import com.soa.ebay.logging.model.Log
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class PsqlAccessLogDao(
    namedParameterJdbcTemplate: NamedParameterJdbcTemplate
): AccessLogDao(namedParameterJdbcTemplate) {
    override fun findLatestTen(): List<Log> {
        return namedParameterJdbcTemplate.query(
            "SELECT * FROM access_log ORDER BY timestamp DESC LIMIT 10"
        ) { rs, _ ->
            Log(
                rs.getTimestamp("timestamp").toInstant(),
                rs.getString("request_method"),
                rs.getString("request_url"),
                rs.getString("status")
            )
        }
    }
}