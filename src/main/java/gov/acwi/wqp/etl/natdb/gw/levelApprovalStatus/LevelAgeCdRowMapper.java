package gov.acwi.wqp.etl.natdb.gw.levelApprovalStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import gov.acwi.wqp.etl.natdb.gw.GwReflist;

public class LevelAgeCdRowMapper implements RowMapper<GwReflist> {

	public static final String LEV_AGE_CD = "lev_age_cd";
	public static final String LEV_AGE_NM = "lev_age_nm";
	public static final String LEV_AGE_SRT_NU = "lev_age_srt_nu";
	public static final String LEV_AGE_DS = "lev_age_ds";
	public static final String LEV_AGE_VLD_FG = "lev_age_vld_fg";

	public GwReflist mapRow(ResultSet rs, int rowNum) throws SQLException {
		GwReflist gwReflist = new GwReflist();

		gwReflist.setGwRefCd(rs.getString(LEV_AGE_CD));
		gwReflist.setGwRefNm(rs.getString(LEV_AGE_NM));
		gwReflist.setGwSortNu(rs.getInt(LEV_AGE_SRT_NU));
		gwReflist.setGwRefDs(rs.getString(LEV_AGE_DS));
		gwReflist.setGwValidFg(rs.getString(LEV_AGE_VLD_FG).charAt(0));
		return gwReflist;
	}
}
