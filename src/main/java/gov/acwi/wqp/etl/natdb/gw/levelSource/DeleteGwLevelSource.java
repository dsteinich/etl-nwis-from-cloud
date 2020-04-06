package gov.acwi.wqp.etl.natdb.gw.levelSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;
import gov.acwi.wqp.etl.natdb.gw.BasicLookup;

@Component
@StepScope
public class DeleteGwLevelSource extends BaseTruncateNwisTable {

	@Autowired
	public DeleteGwLevelSource(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, BasicLookup.GW_LEVEL_SOURCE);
	}

}
