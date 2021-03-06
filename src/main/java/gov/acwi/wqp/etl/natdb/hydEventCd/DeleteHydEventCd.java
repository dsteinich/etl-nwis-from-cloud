package gov.acwi.wqp.etl.natdb.hydEventCd;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteHydEventCd extends BaseTruncateNwisTable {
	
	@Autowired
	public DeleteHydEventCd(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "hyd_event_cd");
	}
}
