package gov.acwi.wqp.etl.natdb.methWithCit;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteMethWithCit extends BaseTruncateNwisTable {
	
	@Autowired
	public DeleteMethWithCit(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "meth_with_cit");
	}
}
