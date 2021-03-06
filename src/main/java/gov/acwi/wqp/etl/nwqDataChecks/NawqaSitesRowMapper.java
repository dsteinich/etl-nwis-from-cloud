package gov.acwi.wqp.etl.nwqDataChecks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;

public class NawqaSitesRowMapper implements RowMapper<NawqaSites> {

    private static final String NWIS_HOST_NM_COLUMN_NAME = "nwis_host_nm";
    private static final String DB_NO_COLUMN_NAME = "db_no";
    private static final String AGENCY_CD_COLUMN_NAME = "agency_cd";
    private static final String SITE_NO_COLUMN_NAME = "site_no";
    private static final String STATION_NM_COLUMN_NAME = "station_nm";
    private static final String STATION_IX_COLUMN_NAME = "station_ix";
    private static final String LAT_VA_COLUMN_NAME = "lat_va";
    private static final String LONG_VA_COLUMN_NAME = "long_va";
    private static final String DEC_LAT_VA_COLUMN_NAME = "dec_lat_va";
    private static final String DEC_LONG_VA_COLUMN_NAME = "dec_long_va";
    private static final String COORD_METH_CD_COLUMN_NAME = "coord_meth_cd";
    private static final String COORD_ACY_CD_COLUMN_NAME = "coord_acy_cd";
    private static final String COORD_DATUM_CD_COLUMN_NAME = "coord_datum_cd";
    private static final String DISTRICT_CD_COLUMN_NAME = "district_cd";
    private static final String LAND_NET_DS_COLUMN_NAME = "land_net_ds";
    private static final String MAP_NM_COLUMN_NAME = "map_nm";
    private static final String COUNTRY_CD_COLUMN_NAME = "country_cd";
    private static final String STATE_CD_COLUMN_NAME = "state_cd";
    private static final String COUNTY_CD_COLUMN_NAME = "county_cd";
    private static final String MCD_CD_COLUMN_NAME = "mcd_cd";
    private static final String MAP_SCALE_FC_COLUMN_NAME = "map_scale_fc";
    private static final String ALT_VA_COLUMN_NAME = "alt_va";
    private static final String ALT_METH_CD_COLUMN_NAME = "alt_meth_cd";
    private static final String ALT_ACY_VA_COLUMN_NAME = "alt_acy_va";
    private static final String ALT_DATUM_CD_COLUMN_NAME = "alt_datum_cd";
    private static final String HUC_CD_COLUMN_NAME = "huc_cd";
    private static final String AGENCY_USE_CD_COLUMN_NAME = "agency_use_cd";
    private static final String BASIN_CD_COLUMN_NAME = "basin_cd";
    private static final String SITE_TP_CD_COLUMN_NAME = "site_tp_cd";
    private static final String TOPO_CD_COLUMN_NAME = "topo_cd";
    private static final String DATA_TYPES_CD_COLUMN_NAME = "data_types_cd";
    private static final String INSTRUMENTS_CD_COLUMN_NAME = "instruments_cd";
    private static final String SITE_RMKS_TX_COLUMN_NAME = "site_rmks_tx";
    private static final String INVENTORY_DT_COLUMN_NAME = "inventory_dt";
    private static final String DRAIN_AREA_VA_COLUMN_NAME = "drain_area_va";
    private static final String CONTRIB_DRAIN_AREA_VA_COLUMN_NAME = "contrib_drain_area_va";
    private static final String TZ_CD_COLUMN_NAME = "tz_cd";
    private static final String LOCAL_TIME_FG_COLUMN_NAME = "local_time_fg";
    private static final String GW_FILE_CD_COLUMN_NAME = "gw_file_cd";
    private static final String CONSTRUCTION_DT_COLUMN_NAME = "construction_dt";
    private static final String RELIABILITY_CD_COLUMN_NAME = "reliability_cd";
    private static final String AQFR_CD_COLUMN_NAME = "aqfr_cd";
    private static final String NAT_AQFR_CD_COLUMN_NAME = "nat_aqfr_cd";
    private static final String SITE_USE_1_CD_COLUMN_NAME = "site_use_1_cd";
    private static final String SITE_USE_2_CD_COLUMN_NAME = "site_use_2_cd";
    private static final String SITE_USE_3_CD_COLUMN_NAME = "site_use_3_cd";
    private static final String WATER_USE_1_CD_COLUMN_NAME = "water_use_1_cd";
    private static final String WATER_USE_2_CD_COLUMN_NAME = "water_use_2_cd";
    private static final String WATER_USE_3_CD_COLUMN_NAME = "water_use_3_cd";
    private static final String NAT_WATER_USE_CD_COLUMN_NAME = "nat_water_use_cd";
    private static final String AQFR_TYPE_CD_COLUMN_NAME = "aqfr_type_cd";
    private static final String WELL_DEPTH_VA_COLUMN_NAME = "well_depth_va";
    private static final String HOLE_DEPTH_VA_COLUMN_NAME = "hole_depth_va";
    private static final String DEPTH_SRC_CD_COLUMN_NAME = "depth_src_cd";
    private static final String PROJECT_NO_COLUMN_NAME = "project_no";
    private static final String SITE_WEB_CD_COLUMN_NAME = "site_web_cd";
    private static final String SITE_CN_COLUMN_NAME = "site_cn";
    private static final String SITE_CR_COLUMN_NAME = "site_cr";
    private static final String SITE_MN_COLUMN_NAME = "site_mn";
    private static final String SITE_MD_COLUMN_NAME = "site_md";
    private static final String DEPRECATED_FG_COLUMN_NAME = "deprecated_fg";
    private static final String SITE_LD_COLUMN_NAME = "site_ld";
    private static final String NAWQA_SITE_FG_COLUMN_NAME = "nawqa_site_fg";
    private static final String NASQAN_NMN_SITE_FG_COLUMN_NAME = "nasqan_nmn_site_fg";

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public NawqaSites mapRow(ResultSet rs, int rowNum) throws SQLException {
        NawqaSites nawqaSites = new NawqaSites();

        nawqaSites.setNwisHostNm(rs.getString(NWIS_HOST_NM_COLUMN_NAME));
        nawqaSites.setDbNo(rs.getString(DB_NO_COLUMN_NAME));
        nawqaSites.setAgencyCd(rs.getString(AGENCY_CD_COLUMN_NAME));
        nawqaSites.setSiteNo(rs.getString(SITE_NO_COLUMN_NAME));
        nawqaSites.setStationNm(rs.getString(STATION_NM_COLUMN_NAME));
        nawqaSites.setStationIx(rs.getString(STATION_IX_COLUMN_NAME));
        nawqaSites.setLatVa(rs.getString(LAT_VA_COLUMN_NAME));
        nawqaSites.setLongVa(rs.getString(LONG_VA_COLUMN_NAME));
        nawqaSites.setDecLatVa(rs.getString(DEC_LAT_VA_COLUMN_NAME));
        nawqaSites.setDecLongVa(rs.getString(DEC_LONG_VA_COLUMN_NAME));
        nawqaSites.setCoordMethCd(rs.getString(COORD_METH_CD_COLUMN_NAME));
        nawqaSites.setCoordAcyCd(rs.getString(COORD_ACY_CD_COLUMN_NAME));
        nawqaSites.setCoordDatumCd(rs.getString(COORD_DATUM_CD_COLUMN_NAME));
        nawqaSites.setDistrictCd(rs.getString(DISTRICT_CD_COLUMN_NAME));
        nawqaSites.setLandNetDs(rs.getString(LAND_NET_DS_COLUMN_NAME));
        nawqaSites.setMapNm(rs.getString(MAP_NM_COLUMN_NAME));
        nawqaSites.setCountryCd(rs.getString(COUNTRY_CD_COLUMN_NAME));
        nawqaSites.setStateCd(rs.getString(STATE_CD_COLUMN_NAME));
        nawqaSites.setCountyCd(rs.getString(COUNTY_CD_COLUMN_NAME));
        nawqaSites.setMcdCd(rs.getString(MCD_CD_COLUMN_NAME));
        nawqaSites.setMapScaleFc(rs.getString(MAP_SCALE_FC_COLUMN_NAME));
        nawqaSites.setAltVa(rs.getString(ALT_VA_COLUMN_NAME));
        nawqaSites.setAltMethCd(rs.getString(ALT_METH_CD_COLUMN_NAME));
        nawqaSites.setAltAcyVa(rs.getString(ALT_ACY_VA_COLUMN_NAME));
        nawqaSites.setAltDatumCd(rs.getString(ALT_DATUM_CD_COLUMN_NAME));
        nawqaSites.setHucCd(rs.getString(HUC_CD_COLUMN_NAME));
        nawqaSites.setAgencyUseCd(rs.getString(AGENCY_USE_CD_COLUMN_NAME));
        nawqaSites.setBasinCd(rs.getString(BASIN_CD_COLUMN_NAME));
        nawqaSites.setSiteTpCd(rs.getString(SITE_TP_CD_COLUMN_NAME));
        nawqaSites.setTopoCd(rs.getString(TOPO_CD_COLUMN_NAME));
        nawqaSites.setDataTypesCd(rs.getString(DATA_TYPES_CD_COLUMN_NAME));
        nawqaSites.setInstrumentsCd(rs.getString(INSTRUMENTS_CD_COLUMN_NAME));
        nawqaSites.setSiteRmksTx(rs.getString(SITE_RMKS_TX_COLUMN_NAME));
        nawqaSites.setInventoryDt(rs.getString(INVENTORY_DT_COLUMN_NAME));
        nawqaSites.setDrainAreaVa(rs.getString(DRAIN_AREA_VA_COLUMN_NAME));
        nawqaSites.setContribDrainAreaVa(rs.getString(CONTRIB_DRAIN_AREA_VA_COLUMN_NAME));
        nawqaSites.setTzCd(rs.getString(TZ_CD_COLUMN_NAME));
        nawqaSites.setLocalTimeFg(rs.getString(LOCAL_TIME_FG_COLUMN_NAME).charAt(0) == 'Y');
        nawqaSites.setGwFileCd(rs.getString(GW_FILE_CD_COLUMN_NAME));
        nawqaSites.setConstructionDt(rs.getString(CONSTRUCTION_DT_COLUMN_NAME));
        nawqaSites.setReliabilityCd(rs.getString(RELIABILITY_CD_COLUMN_NAME));
        nawqaSites.setAqfrCd(rs.getString(AQFR_CD_COLUMN_NAME));
        nawqaSites.setNatAqfrCd(rs.getString(NAT_AQFR_CD_COLUMN_NAME));
        nawqaSites.setSiteUse1Cd(rs.getString(SITE_USE_1_CD_COLUMN_NAME));
        nawqaSites.setSiteUse2Cd(rs.getString(SITE_USE_2_CD_COLUMN_NAME));
        nawqaSites.setSiteUse3Cd(rs.getString(SITE_USE_3_CD_COLUMN_NAME));
        nawqaSites.setWaterUse1Cd(rs.getString(WATER_USE_1_CD_COLUMN_NAME));
        nawqaSites.setWaterUse2Cd(rs.getString(WATER_USE_2_CD_COLUMN_NAME));
        nawqaSites.setWaterUse3Cd(rs.getString(WATER_USE_3_CD_COLUMN_NAME));
        nawqaSites.setNatWaterUseCd(rs.getString(NAT_WATER_USE_CD_COLUMN_NAME));
        nawqaSites.setAqfrTypeCd(rs.getString(AQFR_TYPE_CD_COLUMN_NAME));
        nawqaSites.setWellDepthVa(rs.getString(WELL_DEPTH_VA_COLUMN_NAME));
        nawqaSites.setHoleDepthVa(rs.getString(HOLE_DEPTH_VA_COLUMN_NAME));
        nawqaSites.setDepthSrcCd(rs.getString(DEPTH_SRC_CD_COLUMN_NAME));
        nawqaSites.setProjectNo(rs.getString(PROJECT_NO_COLUMN_NAME));
        nawqaSites.setSiteWebCd(rs.getString(SITE_WEB_CD_COLUMN_NAME));
        nawqaSites.setSiteCn(rs.getString(SITE_CN_COLUMN_NAME));
        nawqaSites.setSiteCr(LocalDateTime.parse(rs.getString(SITE_CR_COLUMN_NAME), dateTimeFormatter).toLocalDate());
        nawqaSites.setSiteMn(rs.getString(SITE_MN_COLUMN_NAME));
        nawqaSites.setSiteMd(LocalDateTime.parse(rs.getString(SITE_MD_COLUMN_NAME), dateTimeFormatter).toLocalDate());
        nawqaSites.setDeprecatedFg(rs.getString(DEPRECATED_FG_COLUMN_NAME).charAt(0) == 'Y');
        nawqaSites.setSiteLd(rs.getString(SITE_LD_COLUMN_NAME));
        nawqaSites.setNawqaSiteFg(rs.getString(NAWQA_SITE_FG_COLUMN_NAME).charAt(0) == 'Y');
        nawqaSites.setNasqanNmnSiteFg(rs.getString(NASQAN_NMN_SITE_FG_COLUMN_NAME).charAt(0) == 'Y');

        return nawqaSites;
    }
}

