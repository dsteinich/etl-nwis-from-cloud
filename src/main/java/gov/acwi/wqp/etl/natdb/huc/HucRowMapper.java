package gov.acwi.wqp.etl.natdb.huc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HucRowMapper implements RowMapper<Huc> {

	public static final String HUC_CD = "huc_cd";
	public static final String HUC_NM = "huc_nm";
	public static final String HUC_CLASS_CD = "huc_class_cd";

	public Huc mapRow(ResultSet rs, int rowNum) throws SQLException {
		Huc huc = new Huc();

		huc.setCode(rs.getString(HUC_CD));
		huc.setName(rs.getString(HUC_NM));
		huc.setClassCode(rs.getString(HUC_CLASS_CD));

		return huc;
	}

}
