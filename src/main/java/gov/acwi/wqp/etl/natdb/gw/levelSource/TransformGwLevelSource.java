package gov.acwi.wqp.etl.natdb.gw.levelSource;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.Application;
import gov.acwi.wqp.etl.natdb.gw.BasicLookup;
import gov.acwi.wqp.etl.natdb.gw.BasicLookupProcessor;
import gov.acwi.wqp.etl.natdb.gw.GwReferenceCode;
import gov.acwi.wqp.etl.natdb.gw.GwReflist;
import gov.acwi.wqp.etl.natdb.gw.GwReflistRowMapper;

@Component
public class TransformGwLevelSource {

	@Autowired
	@Qualifier(Application.DATASOURCE_NWIS_QUALIFIER)
	private DataSource dataSourceNwis;

	@Bean
	public JdbcCursorItemReader<GwReflist> gwReflistGwLevelSourceReader() {
		return new JdbcCursorItemReaderBuilder<GwReflist>()
				.dataSource(dataSourceNwis)
				.name("gwReflistGwLevelSource")
				.preparedStatementSetter(new PreparedStatementSetter() {
					public void setValues(PreparedStatement preparedStatement) throws SQLException {
						preparedStatement.setString(1, GwReflist.GW_LEVEL_SOURCE);
					}
				})
				.sql(GwReferenceCode.SELECT_GW_REFLIST)
				.rowMapper(new GwReflistRowMapper())
				.build();
	}

	@Bean
	public BasicLookupProcessor gwLevelSourceProcessor() {
		return new BasicLookupProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<BasicLookup> gwLevelSourceWriter() {
		return new JdbcBatchItemWriterBuilder<BasicLookup>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BasicLookup>())
				.sql(String.format(GwReferenceCode.INSERT_GENERIC_LOOKUP, BasicLookup.GW_LEVEL_SOURCE))
				.dataSource(dataSourceNwis)
				.build();
	}
}
