package gov.acwi.wqp.etl.natdb.siteTp;

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
public class TransformSiteTp {
	
	@Autowired
	@Qualifier(Application.DATASOURCE_NWIS_QUALIFIER)
	private DataSource dataSourceNwis;

	@Autowired
	@Qualifier(Application.DATASOURCE_NATDB_QUALIFIER)
	private DataSource dataSourceNatdb;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public JdbcCursorItemReader<SiteTp> siteTpReader() {
		return new JdbcCursorItemReaderBuilder<SiteTp>()
				.dataSource(dataSourceNatdb)
				.name("natdbProtoOrg")
				.sql("select site_tp_cd, site_tp_srt_nu, site_tp_vld_fg, site_tp_prim_fg, site_tp_nm, site_tp_ln, site_tp_ds " + 
						"from site_tp")
				.rowMapper(new SiteTpRowManager())
				.build();
	}

	@Bean
	public SiteTpProcessor siteTpProcessor() {
		return new SiteTpProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<SiteTp> siteTpWriter() {
		return new JdbcBatchItemWriterBuilder<SiteTp>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO site_tp (site_tp_cd, site_tp_srt_nu, site_tp_vld_fg, site_tp_prim_fg, site_tp_nm, site_tp_ln, site_tp_ds)" + 
						" VALUES (:siteTpCd, :siteTpSrtNu, :siteTpVldFg, :siteTpPrimFg, :siteTpNm, :siteTpLn, :siteTpDs)")
				.dataSource(dataSourceNwis)
				.build();
	}
}
