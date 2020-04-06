package gov.acwi.wqp.etl.natdb.gw.levelMethod;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;
import gov.acwi.wqp.etl.natdb.gw.BasicLookup;

@Component
@StepScope
public class DeleteGwLevelMethod extends BaseTruncateNwisTable {

	@Autowired
	public DeleteGwLevelMethod(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, BasicLookup.GW_LEVEL_METHOD);
	}

}
