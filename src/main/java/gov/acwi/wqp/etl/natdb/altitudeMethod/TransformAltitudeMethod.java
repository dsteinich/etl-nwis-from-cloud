package gov.acwi.wqp.etl.natdb.altitudeMethod;

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
import gov.acwi.wqp.etl.natdb.BasicLookup;
import gov.acwi.wqp.etl.natdb.BasicLookupProcessor;
import gov.acwi.wqp.etl.natdb.GwReflist;
import gov.acwi.wqp.etl.natdb.GwReflistRowMapper;

@Component
public class TransformAltitudeMethod {
	
	@Autowired
	@Qualifier(Application.DATASOURCE_NWIS_QUALIFIER)
	private DataSource dataSourceNwis;

	@Autowired
	@Qualifier(Application.DATASOURCE_NATDB_QUALIFIER)
	private DataSource dataSourceNatdb;
	
	@Bean
	public JdbcCursorItemReader<GwReflist> gwReflistAltitudeMethodReader() {
		return new JdbcCursorItemReaderBuilder<GwReflist>()
				.dataSource(dataSourceNatdb)
				.name("natdbGwReflistAltitudeMethod")
				.preparedStatementSetter(new PreparedStatementSetter() {
					 public void setValues(PreparedStatement preparedStatement) throws SQLException {
					      preparedStatement.setString(1, "saltmt");
					   }
				})
				.sql("select * from gw_reflist where gw_ed_tbl_nm = ?")
				.rowMapper(new GwReflistRowMapper())
				.build();
	}
	
	@Bean
	public BasicLookupProcessor altitudeMethodProcessor() {
		return new BasicLookupProcessor();
	}


	@Bean
	public JdbcBatchItemWriter<BasicLookup> altitudeMethodWriter() {
		return new JdbcBatchItemWriterBuilder<BasicLookup>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<BasicLookup>())
				.sql("INSERT INTO altitude_method"
						+ " (code, name, sort_order, description, valid_flag) VALUES (:code, :name, :sortOrder, :description, :validFlag)")
				.dataSource(dataSourceNwis)
				.build();
	}	
}
