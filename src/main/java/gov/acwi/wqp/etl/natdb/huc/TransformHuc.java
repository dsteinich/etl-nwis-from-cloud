package gov.acwi.wqp.etl.natdb.huc;

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
public class TransformHuc {

	@Autowired
	@Qualifier(Application.DATASOURCE_NWIS_QUALIFIER)
	private DataSource dataSourceNwis;

	@Autowired
	@Qualifier(Application.DATASOURCE_NATDB_QUALIFIER)
	private DataSource dataSourceNatdb;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public JdbcCursorItemReader<Huc> hucReader() {
		return new JdbcCursorItemReaderBuilder<Huc>()
				.dataSource(dataSourceNatdb)
				.name("natdbHuc")
				.sql("select huc_cd, huc_nm, huc_class_cd from huc")
				.rowMapper(new HucRowMapper())
				.build();
	}

	@Bean
	public HucProcessor hucProcessor() {
		return new HucProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Huc> hucWriter() {
		return new JdbcBatchItemWriterBuilder<Huc>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("insert into huc" + 
						" (code, name, class_code) VALUES (:code, :name, :classCode)")
				.dataSource(dataSourceNwis)
				.build();
	}
}
