package com.soa.ebay.configuration

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.client5.http.io.HttpClientConnectionManager
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory
import org.apache.hc.core5.ssl.SSLContextBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.client.RestTemplate
import javax.net.ssl.SSLContext

@Configuration
class ApplicationConfiguration {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            authorizeRequests {
                authorize(anyRequest, permitAll)
            }
            cors { disable() }
            csrf { disable() }
        }

        return http.build()
    }

    @Bean
    fun restTemplate(
        @Value("\${trust.store}") trustStore: Resource,
        @Value("\${trust.store.password}") trustStorePassword: String
    ): RestTemplate? {
        val sslContext: SSLContext = SSLContextBuilder()
            .loadTrustMaterial(trustStore.url, trustStorePassword.toCharArray()).build()

        val sslConFactory = SSLConnectionSocketFactory(sslContext)

        val cm: HttpClientConnectionManager = PoolingHttpClientConnectionManagerBuilder.create()
            .setSSLSocketFactory(sslConFactory)
            .build()

        val httpClient: CloseableHttpClient = HttpClients.custom().setConnectionManager(cm).build()
        val requestFactory: ClientHttpRequestFactory = HttpComponentsClientHttpRequestFactory(httpClient)

        return RestTemplate(requestFactory)
    }
}
