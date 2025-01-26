package com.soa.ebay.logging.dao

import com.soa.ebay.logging.model.Log
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class MssqlAccessLogDao(
    namedParameterJdbcTemplate: NamedParameterJdbcTemplate
): AccessLogDao(namedParameterJdbcTemplate) {
    override fun findLatestTen(): List<Log> {
        return namedParameterJdbcTemplate.query(
            "SELECT TOP 10 * FROM access_log ORDER BY timestamp DESC"
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