package gov.acwi.wqp.etl.natdb.agency;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.Application;

@Component
public class TransformAgency {

	@Autowired
	@Qualifier(Application.DATASOURCE_NWIS_QUALIFIER)
	private DataSource dataSourceNwis;

	@Autowired
	@Qualifier(Application.DATASOURCE_NATDB_QUALIFIER)
	private DataSource dataSourceNatdb;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public JdbcCursorItemReader<Agency> agencyReader() {
		return new JdbcCursorItemReaderBuilder<Agency>()
				.dataSource(dataSourceNatdb)
				.name("natdbAgency")
				.sql("select agency_cd, agency_nm from agency")
				.rowMapper(new AgencyRowMapper())
				.build();
	}

	@Bean
	public AgencyProcessor agencyProcessor() {
		return new AgencyProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Agency> agencyWriter() {
		return new JdbcBatchItemWriterBuilder<Agency>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("insert into agency" + 
						" (code, name) VALUES (:code, :name)")
				.dataSource(dataSourceNwis)
				.build();
	}
}
