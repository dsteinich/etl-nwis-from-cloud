package gov.acwi.wqp.etl.natdb.altitudeDatum;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteAltitudeDatum extends BaseTruncateNwisTable {

	@Autowired
	public DeleteAltitudeDatum(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "altitude_datum");
	}

}
