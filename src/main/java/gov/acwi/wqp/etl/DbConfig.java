package gov.acwi.wqp.etl;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Profile("default")
public class DbConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource-nwis")
    public DataSourceProperties dataSourcePropertiesNwis() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource dataSourceNwis() {
        return dataSourcePropertiesNwis().initializeDataSourceBuilder().build();
    }

    @Bean
    public JdbcTemplate jdbcTemplateNwis() {
        return new JdbcTemplate(dataSourceNwis());
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource-nwqdatachecks")
    public DataSourceProperties dataSourcePropertiesNwqDataChecks() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSourceNwqDataChecks() {
        return dataSourcePropertiesNwqDataChecks().initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties(prefix="spring.datasource-natdb")
    public DataSourceProperties dataSourcePropertiesNatdb() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSourceNatdb() {
        return dataSourcePropertiesNatdb().initializeDataSourceBuilder().build();
    }
}
