package gov.acwi.wqp.etl.natdb.gw.levelApprovalStatus;

import javax.sql.DataSource;

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
import gov.acwi.wqp.etl.natdb.gw.BasicLookup;
import gov.acwi.wqp.etl.natdb.gw.BasicLookupProcessor;
import gov.acwi.wqp.etl.natdb.gw.GwReferenceCode;
import gov.acwi.wqp.etl.natdb.gw.GwReflist;

@Component
public class TransformGwLevelApprovalStatus {

	@Autowired
	@Qualifier(Application.DATASOURCE_NWIS_QUALIFIER)
	private DataSource dataSourceNwis;

	@Autowired
	@Qualifier(Application.DATASOURCE_NATDB_QUALIFIER)
	private DataSource dataSourceNatdb;

	@Bean
	public JdbcCursorItemReader<GwReflist> gwReflistGwLevelApprovalStatusReader() {
		return new JdbcCursorItemReaderBuilder<GwReflist>()
				.dataSource(dataSourceNatdb)
				.name("natdbGwReflistGwLevelApprovalStatus")
				.sql("select * from lev_age_cd")
				.rowMapper(new LevelAgeCdRowMapper())
				.build();
	}

	@Bean
	public BasicLookupProcessor gwLevelApprovalStatusProcessor() {
		return new BasicLookupProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<BasicLookup> gwLevelApprovalStatusWriter() {
		return new JdbcBatchItemWriterBuilder<BasicLookup>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BasicLookup>())
				.sql(String.format(GwReferenceCode.INSERT_GENERIC_LOOKUP, BasicLookup.GW_LEVEL_APPROVAL_STATUS))
				.dataSource(dataSourceNwis)
				.build();
	}
}
