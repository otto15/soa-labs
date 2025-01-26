package com.soa.products.configuration

import com.soa.products.ejb.service.PersonService
import com.soa.products.ejb.service.ProductService
import com.soa.products.exception.FaultResolver
import com.soa.products.service.HelloService
import jakarta.xml.ws.Endpoint
import org.apache.catalina.connector.Connector
import org.apache.cxf.Bus
import org.apache.cxf.bus.spring.SpringBus
import org.apache.cxf.interceptor.LoggingInInterceptor
import org.apache.cxf.interceptor.LoggingOutInterceptor
import org.apache.cxf.jaxws.EndpointImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.client.RestTemplate
import org.springframework.ws.config.annotation.WsConfigurerAdapter
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver
import java.util.Properties
import javax.naming.Context
import javax.naming.InitialContext

@Configuration
class ApplicationConfiguratio : WsConfigurerAdapter() {
    @Bean
    fun servletContainer(@Value("\${server.http.port}") httpPort: Int): ServletWebServerFactory {
        val connector = Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL)
        connector.port = httpPort

        val tomcat = TomcatServletWebServerFactory()
        tomcat.addAdditionalTomcatConnectors(connector)
        return tomcat
    }

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.build()
    }


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
    fun context(): Context {
        val jndiProps = Properties()
        jndiProps[Context.INITIAL_CONTEXT_FACTORY] = "org.wildfly.naming.client.WildFlyInitialContextFactory"
        jndiProps["jboss.naming.client.ejb.context"] = true
        jndiProps["remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED"] = false
        jndiProps["remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS"] = false
        jndiProps["remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT"] = false
        jndiProps[Context.SECURITY_PRINCIPAL] = "user"
        jndiProps[Context.SECURITY_CREDENTIALS] = "user"
        jndiProps[Context.PROVIDER_URL] = "http-remoting://products-ejb:8080"
        
        return InitialContext(jndiProps)
    }

    @Bean
    fun productService(context: Context): ProductService =
        context.lookup("ejb:/soa-products-ejb-0.0.1-SNAPSHOT/ProductServiceImpl!com.soa.products.ejb.service.ProductService")
            as ProductService

    @Bean
    fun personService(context: Context): PersonService =
        context.lookup("ejb:/soa-products-ejb-0.0.1-SNAPSHOT/PersonServiceImpl!com.soa.products.ejb.service.PersonService")
            as PersonService

    @Bean
    fun endpoint(
        eventEndpoint: HelloService,
        bus: Bus
    ): Endpoint {
        val endpoint: EndpointImpl = EndpointImpl(bus, eventEndpoint)
        endpoint.publish("/products")
        endpoint.getServer().getEndpoint().getInInterceptors().add(LoggingInInterceptor())
        endpoint.getServer().getEndpoint().getOutInterceptors().add(LoggingOutInterceptor())
        //endpoint.getServer().getEndpoint().getOutFaultInterceptors().add(ExceptionInterceptor())
        return endpoint
    }

    @Bean(name = [Bus.DEFAULT_BUS_ID])
    fun springBus(): SpringBus {
        return SpringBus()
    }

    @Bean
    fun exceptionResolver(): SoapFaultMappingExceptionResolver {
        val exceptionResolver: SoapFaultMappingExceptionResolver = FaultResolver()

        val faultDefinition = SoapFaultDefinition()
        faultDefinition.faultCode = SoapFaultDefinition.SERVER
        exceptionResolver.setDefaultFault(faultDefinition)

        val errorMappings = Properties()
        errorMappings.setProperty(Exception::class.java.name, SoapFaultDefinition.SERVER.toString())
        //errorMappings.setProperty(ErrorWithCode::class.java.getName(), SoapFaultDefinition.SERVER.toString())
        exceptionResolver.setExceptionMappings(errorMappings)
        exceptionResolver.order = 1
        return exceptionResolver
    }
}
