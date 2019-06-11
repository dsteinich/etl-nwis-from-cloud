package gov.acwi.wqp.etl.natdb.citMeth;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteCitMeth extends BaseTruncateNwisTable {
	
	@Autowired
	public DeleteCitMeth(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "cit_meth");
	}
}
