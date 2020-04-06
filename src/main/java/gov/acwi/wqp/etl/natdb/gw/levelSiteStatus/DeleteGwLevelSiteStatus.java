package gov.acwi.wqp.etl.natdb.gw.levelSiteStatus;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;
import gov.acwi.wqp.etl.natdb.gw.BasicLookup;

@Component
@StepScope
public class DeleteGwLevelSiteStatus extends BaseTruncateNwisTable {

	@Autowired
	public DeleteGwLevelSiteStatus(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, BasicLookup.GW_LEVEL_SITE_STATUS);
	}

}
