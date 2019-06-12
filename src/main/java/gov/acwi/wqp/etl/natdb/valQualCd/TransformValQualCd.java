package gov.acwi.wqp.etl.natdb.valQualCd;

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
public class TransformValQualCd {
	
	@Autowired
	@Qualifier(Application.DATASOURCE_NWIS_QUALIFIER)
	private DataSource dataSourceNwis;

	@Autowired
	@Qualifier(Application.DATASOURCE_NATDB_QUALIFIER)
	private DataSource dataSourceNatdb;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	public JdbcCursorItemReader<ValQualCd> valQualCdReader() {
		return new JdbcCursorItemReaderBuilder<ValQualCd>()
				.dataSource(dataSourceNatdb)
				.name("natdbValQualCd")
				.sql("select val_qual_cd, val_qual_tp, val_qual_srt_nu, val_qual_vld_fg, val_qual_nm, val_qual_ds from val_qual_cd")
				.rowMapper(new ValQualCdRowManager())
				.build();
	}

	@Bean
	public ValQualCdProcessor valQualCdProcessor() {
		return new ValQualCdProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<ValQualCd> valQualCdWriter() {
		return new JdbcBatchItemWriterBuilder<ValQualCd>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO val_qual_cd (val_qual_cd, val_qual_tp, val_qual_srt_nu, val_qual_vld_fg, val_qual_nm, val_qual_ds) "
						+ "VALUES (:valQualCd, :valQualTp, :valQualSrtNu, :valQualVldFg, :valQualNm, :valQualDs)")
				.dataSource(dataSourceNwis)
				.build();
	}
}
