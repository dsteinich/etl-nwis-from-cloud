package gov.acwi.wqp.etl.natdb.gw;

public class BasicLookup {

	public static final String ALTITUDE_DATUM = "altitude_datum";
	public static final String ALTITUDE_METHOD = "altitude_method";
	public static final String AQUIFER_TYPE = "aquifer_type";
	public static final String DATA_RELIABILITY = "data_reliability";
	public static final String GW_LEVEL_ACCURACY = "gw_level_accuracy";
	public static final String GW_LEVEL_DATE_TIME_ACCURACY = "gw_level_date_time_accuracy";
	public static final String GW_LEVEL_METHOD = "gw_level_method";
	public static final String GW_LEVEL_SITE_STATUS = "gw_level_site_status";
	public static final String GW_LEVEL_SOURCE = "gw_level_source";
	public static final String LAT_LONG_DATUM = "lat_long_datum";
	public static final String LAT_LONG_METHOD = "lat_long_method";
	public static final String TOPGRAPHIC_SETTING = "topographic_setting";

	private String code;
	private String name;
	private int sortOrder;
	private String description;
	private boolean validFlag;

	public BasicLookup() {
	}

	public BasicLookup(String code, String name, int sortOrder, String description, boolean validFlag) {
		this.code = code;
		this.name = name;
		this.sortOrder = sortOrder;
		this.description = description;
		this.validFlag = validFlag;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isValidFlag() {
		return validFlag;
	}
	public void setValidFlag(Boolean validFlag) {
		this.validFlag = validFlag;
	}

	@Override
	public String toString() {
		return "code: " + code + ", name: " + name;
	}
}
