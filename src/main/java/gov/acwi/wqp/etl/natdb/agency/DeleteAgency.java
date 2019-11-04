package gov.acwi.wqp.etl.natdb.agency;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteAgency extends BaseTruncateNwisTable {

	@Autowired
	public DeleteAgency(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "agency");
	}
}
