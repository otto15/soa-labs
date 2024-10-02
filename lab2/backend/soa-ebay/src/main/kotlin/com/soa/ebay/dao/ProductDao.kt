package com.soa.ebay.dao

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ProductDao(
    private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {
}