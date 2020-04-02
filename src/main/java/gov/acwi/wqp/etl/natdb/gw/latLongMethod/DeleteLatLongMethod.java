package gov.acwi.wqp.etl.natdb.gw.latLongMethod;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteLatLongMethod extends BaseTruncateNwisTable {

	@Autowired
	public DeleteLatLongMethod(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "lat_long_method");
	}

}
