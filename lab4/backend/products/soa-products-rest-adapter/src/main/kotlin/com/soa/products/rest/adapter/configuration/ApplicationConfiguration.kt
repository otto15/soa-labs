package com.soa.products.rest.adapter.configuration

import com.soa.products.rest.adapter.client.ProductsSoapClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.oxm.jaxb.Jaxb2Marshaller

@Configuration
class ApplicationConfiguration {
    @Bean
    fun marshaller(): Jaxb2Marshaller {
        val marshaller = Jaxb2Marshaller()
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.contextPath = "com.soa.products.rest.adapter.wsdl"
        return marshaller
    }

    @Bean
    fun productsSoapClient(
        @Value("\${products.soap.host:localhost}") host: String,
        marshaller: Jaxb2Marshaller
    ): ProductsSoapClient {
        val client = ProductsSoapClient(host)
        client.setDefaultUri("http://localhost:1616/services")
        client.setMarshaller(marshaller)
        client.setUnmarshaller(marshaller)
        return client
    }
}