package com.soa.products.domain.search

import com.soa.products.utils.SortOrder

data class SortParam(
    val searchField: SearchField,
    val sortOrder: SortOrder
) {
    init {
        require(searchField != SearchField.COORDINATES) { "COORDINATES are not suitable for sorting" }
    }
}
