package gov.acwi.wqp.etl.natdb.topographicSetting;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteTopographicSetting extends BaseTruncateNwisTable {

	@Autowired
	public DeleteTopographicSetting(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "topographic_setting");
	}

}
