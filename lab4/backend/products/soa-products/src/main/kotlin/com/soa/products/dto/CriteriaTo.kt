package com.soa.products.dto


enum class CriteriaTo(val value: String) {

    ID("ID"),
    NAME("NAME"),
    COORDINATES("COORDINATES"),
    CREATION_DATE("CREATION_DATE"),
    PRICE("PRICE"),
    PART_NUMBER("PART_NUMBER"),
    MANUFACTURER_COST("MANUFACTURER_COST"),
    UNIT_OF_MEASURE("UNIT_OF_MEASURE"),
    OWNER_PASSPORT_ID("OWNER_PASSPORT_ID");

    companion object {
        fun forValue(value: String): CriteriaTo {
            return values().firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("No enum constant with value $value")
        }
    }
}
