package gov.acwi.wqp.etl.natdb.huc;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteHuc extends BaseTruncateNwisTable {

	@Autowired
	public DeleteHuc(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "huc");
	}
}
