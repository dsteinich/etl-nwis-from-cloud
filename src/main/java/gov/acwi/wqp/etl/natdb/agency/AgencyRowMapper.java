package gov.acwi.wqp.etl.natdb.agency;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AgencyRowMapper implements RowMapper<Agency> {

	public static final String AGENCY_CD = "agency_cd";
	public static final String AGENCY_NM = "agency_nm";

	public Agency mapRow(ResultSet rs, int rowNum) throws SQLException {
		Agency agency = new Agency();

		agency.setCode(rs.getString(AGENCY_CD));
		agency.setName(rs.getString(AGENCY_NM));

		return agency;
	}

}
