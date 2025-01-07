package com.soa.products.transformer

import com.soa.products.ejb.service.command.CreateProductCommand
import com.soa.products.ejb.service.command.UpdateProductCommand
import com.soa.products.ejb.domain.Coordinates
import com.soa.products.ejb.domain.Product
import com.soa.products.ejb.domain.ProductSearchParams
import com.soa.products.ejb.domain.UnitOfMeasure
import com.soa.products.ejb.domain.search.ComparisonOperator
import com.soa.products.ejb.domain.search.FilterParam
import com.soa.products.ejb.domain.search.SearchField
import com.soa.products.ejb.domain.search.SortParam
import com.soa.products.ejb.utils.SortOrder
import generated.soa.products.dto.CoordinatesTo
import generated.soa.products.dto.CreateUpdateProductRequestTo
import generated.soa.products.dto.CriteriaTo
import generated.soa.products.dto.FilterOperatorTo
import generated.soa.products.dto.ProductTo
import generated.soa.products.dto.SortTypeTo
import generated.soa.products.dto.UnitOfMeasureTo

fun CreateUpdateProductRequestTo.toCreateProductCommand() = CreateProductCommand(
    this.name,
    this.coordinates.toCoordinates(),
    this.price,
    this.manufacturerCost,
    UnitOfMeasure.valueOf(this.unitOfMeasure.value),
    this.partNumber,
    this.ownerPassportId
)

fun CreateUpdateProductRequestTo.toUpdateProductCommand(id: Long) = UpdateProductCommand(
    id,
    this.name,
    this.coordinates.toCoordinates(),
    this.price,
    this.manufacturerCost,
    UnitOfMeasure.valueOf(this.unitOfMeasure.value),
    this.partNumber,
    this.ownerPassportId
)

fun CoordinatesTo.toCoordinates() = Coordinates(this.x, this.y)

fun Coordinates.toCoordinatesTo() = CoordinatesTo(this.x, this.y)

fun Product.toProductTo() = ProductTo(
    this.id,
    this.name,
    this.coordinates.toCoordinatesTo(),
    this.creationDate,
    this.price,
    this.manufacturerCost,
    UnitOfMeasureTo.forValue(this.unitOfMeasure.name),
    this.partNumber,
    this.owner?.toPersonTo()
)

fun transform(
    page: Int,
    size: Int,
    sortCriteriaList: List<CriteriaTo>?,
    sortTypeList: List<SortTypeTo>?,
    filterCriteriaList: List<CriteriaTo>?,
    filterOperators: List<FilterOperatorTo>?,
    filterValues: List<String>?
): ProductSearchParams {
    require(
        (sortTypeList == null && sortCriteriaList == null) ||
            (sortTypeList?.size == sortCriteriaList?.size)
    ) { "Sort criteria and type list should be both nulls or be equal" }

    require(
        (filterCriteriaList == null && filterOperators == null && filterValues == null) ||
            ((filterCriteriaList?.size == filterOperators?.size) &&
                (filterOperators?.size == filterValues?.size))
    ) { "Filter criteria, operator, value list should be all nulls or be equal" }

    val builder = ProductSearchParams.Builder()

    builder.limit(size)
    builder.offset((page - 1) * size)

    if (sortCriteriaList != null && sortTypeList != null) {
        val searchFields: List<SearchField> = sortCriteriaList.toSearchFields()
        val sortOrders: List<SortOrder> = sortTypeList.toSortOrders()

        val sortParams = searchFields.zip(sortOrders) { field, order -> SortParam(field, order) }
        builder.sortParams(sortParams)
    }

    if (filterCriteriaList != null && filterOperators != null && filterValues != null) {
        val searchFields: List<SearchField> = filterCriteriaList.toSearchFields()
        val comparisonOperators: List<ComparisonOperator> = filterOperators.toComparisonOperator()

        val filterParams: List<FilterParam> = searchFields.zip(comparisonOperators)
            .zip(filterValues) { fieldOperatorPair, value ->
                FilterParam(
                    fieldOperatorPair.first,
                    fieldOperatorPair.second,
                    value
                )
            }

        builder.filterParams(filterParams)
    }

    return builder.build()
}

fun List<CriteriaTo>.toSearchFields(): List<SearchField> = this.map { SearchField.valueOf(it.name) }

fun List<SortTypeTo>.toSortOrders(): List<SortOrder> = this.map { SortOrder.valueOf(it.name) }

fun List<FilterOperatorTo>.toComparisonOperator(): List<ComparisonOperator> = this.map {
    ComparisonOperator.valueOf(it.name)
}
