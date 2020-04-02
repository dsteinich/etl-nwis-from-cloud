package gov.acwi.wqp.etl.natdb.gw.reflist;

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
import gov.acwi.wqp.etl.natdb.gw.GwReflist;
import gov.acwi.wqp.etl.natdb.gw.GwReflistRowMapper;

@Component
public class TransformGwReflist {

	@Autowired
	@Qualifier(Application.DATASOURCE_NWIS_QUALIFIER)
	private DataSource dataSourceNwis;

	@Autowired
	@Qualifier(Application.DATASOURCE_NATDB_QUALIFIER)
	private DataSource dataSourceNatdb;

	@Bean
	public JdbcCursorItemReader<GwReflist> gwReflistReader() {
		return new JdbcCursorItemReaderBuilder<GwReflist>()
				.dataSource(dataSourceNatdb)
				.name("natdbGwReflist")
				.sql("select * from gw_reflist")
				.rowMapper(new GwReflistRowMapper())
				.build();
	}

	@Bean
	public GwReflistProcessor gwReflistProcessor() {
		return new GwReflistProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<GwReflist> gwReflistWriter() {
		return new JdbcBatchItemWriterBuilder<GwReflist>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("insert into gw_reflist"
						+ " (gw_ed_tbl_nm, gw_ref_cd, gw_ref_nm, gw_sort_nu, gw_ref_ds, gw_valid_fg)"
						+ " VALUES (:gwEdTblNm, :gwRefCd, :gwRefNm, :gwSortNu, :gwRefDs, :gwValidFg)")
				.dataSource(dataSourceNwis)
				.build();
	}
}
