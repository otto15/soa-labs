package com.soa.ebay.logging

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.context.annotation.Configuration

@Configuration
class RequestLoggingCustomizer(
    private val ebayRequestLog: EbayRequestLog,
) : WebServerFactoryCustomizer<JettyServletWebServerFactory?> {
    override fun customize(factory: JettyServletWebServerFactory?) {
        factory?.addServerCustomizers({ it.requestLog = ebayRequestLog })
    }
}