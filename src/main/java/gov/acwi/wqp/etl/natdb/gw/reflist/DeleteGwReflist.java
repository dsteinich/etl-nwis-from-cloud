package gov.acwi.wqp.etl.natdb.gw.reflist;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteGwReflist extends BaseTruncateNwisTable {

	@Autowired
	public DeleteGwReflist(@Qualifier("jdbcTemplateNwis") JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "gw_reflist");
	}

}
