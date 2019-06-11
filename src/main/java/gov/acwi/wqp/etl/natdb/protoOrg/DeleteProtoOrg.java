package gov.acwi.wqp.etl.natdb.protoOrg;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import gov.acwi.wqp.etl.BaseTruncateNwisTable;

@Component
@StepScope
public class DeleteProtoOrg extends BaseTruncateNwisTable {
	
	@Autowired
	public DeleteProtoOrg(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, "proto_org");
	}
}
