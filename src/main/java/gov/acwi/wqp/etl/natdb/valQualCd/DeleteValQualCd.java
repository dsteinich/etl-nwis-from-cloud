package gov.acwi.wqp.etl.natdb.valQualCd;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteValQualCd extends BaseTruncateNwisTable {
	
	@Autowired
	public DeleteValQualCd(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "val_qual_cd");
	}
}
