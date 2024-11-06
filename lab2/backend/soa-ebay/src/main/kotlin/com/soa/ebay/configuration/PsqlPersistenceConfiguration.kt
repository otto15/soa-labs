package com.soa.ebay.configuration

import com.soa.ebay.logging.dao.AccessLogDao
import com.soa.ebay.logging.dao.PsqlAccessLogDao
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
@Profile("psql")
class PsqlPersistenceConfiguration {
    @Bean
    @ConfigurationProperties("spring.psql.datasource")
    fun psqlDataSourceProperties() = DataSourceProperties()

    @Bean
    fun psqlDataSource(): DataSource = psqlDataSourceProperties()
        .initializeDataSourceBuilder()
        .build()

    @Bean
    fun psqlJdbcTemplate(@Qualifier("psqlDataSource") dataSource: DataSource) =
        NamedParameterJdbcTemplate(dataSource)
    
    @Bean
    fun psqlAccessLogDao(psqlJdbcTemplate: NamedParameterJdbcTemplate): AccessLogDao {
        return PsqlAccessLogDao(psqlJdbcTemplate)
    }
}