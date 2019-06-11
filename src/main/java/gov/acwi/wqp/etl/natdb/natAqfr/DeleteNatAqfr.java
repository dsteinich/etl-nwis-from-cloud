package gov.acwi.wqp.etl.natdb.natAqfr;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteNatAqfr extends BaseTruncateNwisTable {
	
	@Autowired
	public DeleteNatAqfr(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "nat_aqfr");
	}
}
