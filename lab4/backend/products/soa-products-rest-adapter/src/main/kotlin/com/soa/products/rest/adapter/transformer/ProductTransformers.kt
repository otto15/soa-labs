package com.soa.products.rest.adapter.transformer

import com.soa.products.rest.adapter.wsdl.FilterOperatorTo as SoapFilterOperatorTo
import com.soa.products.rest.adapter.wsdl.CriteriaTo as SoapCriteriaTo
import com.soa.products.rest.adapter.wsdl.GetProducts
import com.soa.products.rest.adapter.wsdl.SortTypeTo as SoapSortTypeTo
import com.soa.products.rest.adapter.wsdl.CoordinatesTo as SoapCoordinatesTo
import com.soa.products.rest.adapter.wsdl.UnitOfMeasureTo as SoapUnitOfMeasureTo
import com.soa.products.rest.adapter.wsdl.CreateUpdateProductRequestTo as SoapCreateUpdateProductRequestTo
import generated.soa.products.rest.adapter.dto.ColorTo
import generated.soa.products.rest.adapter.dto.FilterOperatorTo as RestFilterOperatorTo
import generated.soa.products.rest.adapter.dto.CriteriaTo as RestCriteriaTo
import generated.soa.products.rest.adapter.dto.SortTypeTo as RestSortTypeTo
import generated.soa.products.rest.adapter.dto.CreateUpdateProductRequestTo as RestCreateUpdateProductRequestTo
import com.soa.products.rest.adapter.wsdl.PersonTo as SoapPersonTo
import generated.soa.products.rest.adapter.dto.CoordinatesTo as RestCoordinatesTo
import generated.soa.products.rest.adapter.dto.PersonTo as RestPersonTo
import generated.soa.products.rest.adapter.dto.UnitOfMeasureTo as RestUnitOfMeasureTo
import java.time.LocalDate
import generated.soa.products.rest.adapter.dto.ProductTo as RestProductTo
import com.soa.products.rest.adapter.wsdl.ProductTo as SoapProductTo

fun SoapProductTo.toRestProductTo(): RestProductTo = RestProductTo(
    this.id,
    this.name,
    RestCoordinatesTo(this.coordinates.x, this.coordinates.y),
    LocalDate.parse(this.createdDate),
    this.price,
    this.manufacturerCost,
    RestUnitOfMeasureTo.forValue(this.unitOfMeasure.value()),
    this.partNumber,
    this.owner.toRestPersonTo()
)

fun SoapPersonTo.toRestPersonTo(): RestPersonTo = RestPersonTo(
    this.name,
    this.passportID,
    this.eyeColor?.let { ColorTo.forValue(this.eyeColor.value()) },
    this.hairColor?.let { ColorTo.forValue(this.hairColor.value()) }
)

fun RestCreateUpdateProductRequestTo.toSoapCreateUpdateProductRequestTo(): SoapCreateUpdateProductRequestTo {
    val soapRequest = SoapCreateUpdateProductRequestTo()
    soapRequest.name = this.name
    soapRequest.price = this.price
    soapRequest.ownerPassportId = this.ownerPassportId
    soapRequest.partNumber = this.partNumber
    soapRequest.unitOfMeasure = SoapUnitOfMeasureTo.fromValue(this.unitOfMeasure.value)
    soapRequest.manufacturerCost = this.manufacturerCost

    val coordinates = SoapCoordinatesTo()
    coordinates.x = this.coordinates.x
    coordinates.y = this.coordinates.y
    soapRequest.coordinates = coordinates

    return soapRequest
}

fun transform(
    page: Int,
    size: Int,
    sortTypeList: List<RestSortTypeTo>?,
    sortCriteriaList: List<RestCriteriaTo>?,
    filterCriteriaList: List<RestCriteriaTo>?,
    filterOperatorList: List<RestFilterOperatorTo>?,
    filterValueList: List<String>?,
): GetProducts {
    val request = GetProducts()
    request.page = page
    request.size = size

    sortTypeList?.let {
        request.sortTypeList.addAll(sortTypeList.map { SoapSortTypeTo.fromValue(it.value) })
    }

    sortCriteriaList?.let {
        request.sortCriteriaList.addAll(sortCriteriaList.map { SoapCriteriaTo.fromValue(it.value) })
    }

    filterCriteriaList?.let {
        request.filterCriteriaList.addAll(filterCriteriaList.map { SoapCriteriaTo.fromValue(it.value) })
    }

    filterOperatorList?.let {
        request.filterOperators.addAll(filterOperatorList.map { SoapFilterOperatorTo.fromValue(it.value) })
    }

    filterValueList?.let {
        request.filterValues.addAll(filterValueList)
    }

    return request
}
