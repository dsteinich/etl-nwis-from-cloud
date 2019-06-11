package gov.acwi.wqp.etl.natdb.bodyPart;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteBodyPart extends BaseTruncateNwisTable {
	
	@Autowired
	public DeleteBodyPart(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "body_part");
	}
}
