package gov.acwi.wqp.etl.natdb.parmMeth;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteParmMeth extends BaseTruncateNwisTable {
	
	@Autowired
	public DeleteParmMeth(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "parm_meth");
	}
}
