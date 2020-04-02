package gov.acwi.wqp.etl.natdb.gw.levelApprovalStatus;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;
import gov.acwi.wqp.etl.natdb.gw.BasicLookup;

@Component
@StepScope
public class DeleteGwLevelApprovalStatus extends BaseTruncateNwisTable {

	@Autowired
	public DeleteGwLevelApprovalStatus(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, BasicLookup.GW_LEVEL_APPROVAL_STATUS);
	}

}
