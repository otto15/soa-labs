package com.soa.products.exception

import org.springframework.ws.soap.SoapFault
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver
import javax.xml.namespace.QName
import kotlin.Any
import kotlin.Exception

class FaultResolver : SoapFaultMappingExceptionResolver() {
    override fun customizeFault(endpoint: Any?, ex: Exception, fault: SoapFault) {
        // if (ex is ErrorWithCode) {
        //     val detail: SoapFaultDetail = fault.addFaultDetail()
        //     detail.addFaultDetailElement(CODE).addText(String.valueOf(ex.getCode()))
        //     detail.addFaultDetailElement(MESSAGE).addText(ex.getMessage())
        // }
    }

    companion object {
        private val CODE: QName = QName("code")
        private val MESSAGE: QName = QName("message")
    }
}