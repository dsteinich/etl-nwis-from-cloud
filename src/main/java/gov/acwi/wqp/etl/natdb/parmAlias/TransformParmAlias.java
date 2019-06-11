package gov.acwi.wqp.etl.natdb.parmAlias;

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
public class TransformParmAlias {
	
	@Autowired
	@Qualifier(Application.DATASOURCE_NWIS_QUALIFIER)
	private DataSource dataSourceNwis;

	@Autowired
	@Qualifier(Application.DATASOURCE_NATDB_QUALIFIER)
	private DataSource dataSourceNatdb;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public JdbcCursorItemReader<ParmAlias> parmAliasReader() {
		return new JdbcCursorItemReaderBuilder<ParmAlias>()
				.dataSource(dataSourceNatdb)
				.name("natdbParmAlias")
				.sql("select parm_cd, parm_alias_cd, parm_alias_nm, parm_alias_init_dt, parm_alias_init_nm,  parm_alias_rev_dt, parm_alias_rev_nm " + 
						"from parm_alias")
				.rowMapper(new ParmAliasRowManager())
				.build();
	}

	@Bean
	public ParmAliasProcessor parmAliasProcessor() {
		return new ParmAliasProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<ParmAlias> parmAliasWriter() {
		return new JdbcBatchItemWriterBuilder<ParmAlias>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO parm_alias(parm_cd, parm_alias_cd, parm_alias_nm, parm_alias_init_dt, parm_alias_init_nm,  parm_alias_rev_dt, parm_alias_rev_nm)" + 
						" VALUES (:parmCd, :parmAliasCd, :parmAliasNm, :parmAliasInitDt, :parmAliasInitNm, :parmAliasRevDt, :parmAliasRevNm)")
				.dataSource(dataSourceNwis)
				.build();
	}
}
