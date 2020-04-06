package gov.acwi.wqp.etl.natdb;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;

import liquibase.integration.spring.SpringLiquibase;

@TestConfiguration
public class DBTestConfig {

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
	@Primary
	public JdbcTemplate jdbcTemplateNwis() {
		return new JdbcTemplate(dataSourceNwis());
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
	@ConfigurationProperties(prefix = "spring.datasource-natdb.liquibase")
	public LiquibaseProperties liquibasePropertiesNatdb() {
		return new LiquibaseProperties();
	}

	@Bean
	public SpringLiquibase liquibaseNatdbnwis() {
		return instantiateSpringLiquibase(dataSourceNatdb(), liquibasePropertiesNatdb());
	}

	private SpringLiquibase instantiateSpringLiquibase(DataSource dataSource, LiquibaseProperties liquibaseProperties) {
		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setDataSource(dataSource);
		springLiquibase.setChangeLog(liquibaseProperties.getChangeLog());
		springLiquibase.setChangeLogParameters(liquibaseProperties.getParameters());
		springLiquibase.setLiquibaseSchema(liquibaseProperties.getLiquibaseSchema());
		return springLiquibase;
	}

	@Bean
	public DatabaseConfigBean dbUnitDatabaseConfig() {
		DatabaseConfigBean dbUnitDbConfig = new DatabaseConfigBean();
		dbUnitDbConfig.setDatatypeFactory(new PostgresqlDataTypeFactoryWithJson());
		return dbUnitDbConfig;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean nwis() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(dataSourceNwis());
		dbUnitDatabaseConnection.setSchema("nwis");
		return dbUnitDatabaseConnection;
	}

	@Bean
	public DatabaseDataSourceConnectionFactoryBean natdb() throws SQLException {
		DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection = new DatabaseDataSourceConnectionFactoryBean();
		dbUnitDatabaseConnection.setDatabaseConfig(dbUnitDatabaseConfig());
		dbUnitDatabaseConnection.setDataSource(dataSourceNatdb());
		return dbUnitDatabaseConnection;
	}

}
