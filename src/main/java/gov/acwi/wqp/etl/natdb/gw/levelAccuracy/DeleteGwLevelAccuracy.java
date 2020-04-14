package gov.acwi.wqp.etl.natdb.gw.levelAccuracy;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;
import gov.acwi.wqp.etl.natdb.gw.BasicLookup;

@Component
@StepScope
public class DeleteGwLevelAccuracy extends BaseTruncateNwisTable {

	@Autowired
	public DeleteGwLevelAccuracy(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, BasicLookup.GW_LEVEL_ACCURACY);
	}

}
