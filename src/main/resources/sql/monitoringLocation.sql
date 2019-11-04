insert
  into monitoring_location (agency,
                            site_identification_number,
                            site_name,
                            site_type,
                            dms_latitude,
                            dms_longitude,
                            decimal_latitude,
                            demical_longitude,
                            latitude_longitude_method,
                            latitude_longitude_accuracy,
                            latitude_longitude_datum,
                            decimal_latitude_longitude_datum,
                            district,
                            state,
                            county,
                            country,
                            land_net_location_description,
                            name_of_location_map,
                            scale_of_location_map,
                            altitude_of_guage_land_surface,
                            method_altitude_determined,
                            altitude_accuracy,
                            altitude_datum,
                            subbasin_hydrologic_unit,
                            drainage_basin,
                            topographic_setting,
                            flags_for_instruments_at_site,
                            date_of_first_construction,
                            date_site_established_or_inventoried,
                            drainage_area,
                            contributing_drainage_area,
                            time_zone_abbreviation,
                            site_honors_daylight_savings_time,
                            data_reliability,
                            data_other_gw_files,
                            national_aquifer,
                            local_aquifer,
                            local_aquifer_type,
                            well_depth,
                            hole_depth,
                            source_of_hole_depth,
                            project_numer,
                            site_id,
                            agency_cd,
                            site_tp_cd,
                            coord_meth_cd,
                            coord_acy_cd,
                            coord_datum_cd,
                            dec_coord_datum_cd,
                            district_cd,
                            state_cd,
                            county_cd,
                            country_cd,
                            alt_meth_cd,
                            alt_datum_cd,
                            huc_cd,
                            basin_cd,
                            topo_cd,
                            tz_cd,
                            local_time_fg,
                            reliability_cd,
                            gw_file_cd,
                            nat_aqfr_cd,
                            aqfr_cd,
                            aqfr_type_cd,
                            depth_src_cd)
select agency.agency_nm agency,
       sitefile.site_no site_identification_number,
       sitefile.station_nm site_name,
       site_tp.site_tp_ln site_type,
       sitefile.la_va dms_latitude,
       sitefile.long_va dms_longitude,
       sitefile.dec_lat_va decimal_latitude,
       sitefile.dec_long_va decimal_longitude,
       lat_long_method.description latitude_longitude_method,
       lat_long_accuracy.description latitude_longitude_accuracy,
       lat_long_datum.description latitude_longitude_datum,
       decimal_lat_long_datum.description decimal_latitude_longitude_datum,
       district.state_nm district,
       state.state_nm state,
       county.county_nm county,
       sitefile.country_cd country,
       sitefile.land_net_ds land_net_location_description,
       sitefile.map_nm name_of_location_map,
       sitefile.map_scale_fc scale_of_location_map,
       sitefile.alt_va altitude_of_guage_land_surface,
       altitude_method.description method_altitude_determined,
       sitefile.alt_acy_va altitude_accuracy,
       altitude_datum.description altitude_datum,
       huc.name subbasin_hydrologic_unit,
       sitefile.basin_cd drainage_basin,
       topographic_setting.name topographic_setting,
       sitefile.instruments_cd flags_for_instruments_at_site,
       sitefile.construction_dt date_of_first_construction,
       sitefile.inventory_dt date_site_established_or_inventoried,
       sitefile.drain_area_va drainage_area,
       sitefile.contrib_drain_area_va contributing_drainage_area,
       sitefile.tz_cd time_zone_abbreviation,
       sitefile.local_time_fg site_honors_daylight_savings_time,
       data_reliabilty.description data_reliability,
       sitefile.gw_file_cd data_other_gw_files,
       nat_aqfr.nat_aqfr_nm national_aquifer,
       aqfr.aqfr_nm local_aquifer,
       aqfr_type.description local_aquifer_type,
       sitefile.weel_depth_va well_depth,
       sitefile.hole_depth_va hole_depth,
       sitefile.depth_src_cd source_of_hole_depth,
       sitefile..project_no project_numer,
       sitefile.site_id,
       sitefile.agency_cd,
       sitefile.site_tp_cd,
       sitefile.coord_meth_cd,
       sitefile.coord_acy_cd,
       sitefile.coord_datum_cd,
       sitefile.dec_coord_datum_cd,
       sitefile.district_cd,
       sitefile.state_cd,
       sitefile.county_cd,
       sitefile.country_cd,
       sitefile.alt_meth_cd,
       sitefile.alt_datum_cd,
       sitefile.huc_cd,
       sitefile.basin_cd,
       sitefile.topo_cd,
       sitefile.tz_cd,
       sitefile.local_time_fg,
       sitefile.reliability_cd,
       sitefile.gw_file_cd,
       sitefile.nat_aqfr_cd,
       sitefile.aqfr_cd,
       sitefile.aqfr_type_cd,
       sitefile.depth_src_cd
  from sitefile
       left join agency
         on sitefile.agency_cd = agency.code
       left join site_tp
         on sitefile.site_tp_cd = site_tp.site_tp_cd
       left join lat_long_method
         on sitefile.coor_meth_cd = lat_long_method.code
       left join lat_long_accuracy
         on sitefile.coord_acy_cd = lat_long_accuracy.code
       left join lat_long_datum
         on sitefile.coord_datum_cd = lat_long_datum.code
       left join lat_long_datum decimal_lat_long_datum
         on sitefile.dec_coord_datum_cd = decimal_lat_long_datum.code
       left join state district
         on sitefile.state_cd = district.state_cd and
            sitefile.country_cd = district.country_cd
       left join state
         on sitefile.state_cd = state.state_cd and
            sitefile.country_cd = state.country_cd
       left join county
         on sitefile.county_cd = county.county_cd and
            sitefile.state_cd = county.state_cd and
            sitefile.country_cd = county.country_cd
       left join altitude_method
         on sitefile.alt_meth_cd = altitude_method.code
       left join altitude_datum
         on sitefile.alt_datum_cd = altitude_datum.code
       left join huc
         on sitefile.huc_cd = huc.code
       left join topographic_setting
         on sitefile.topo_cd = topographic_setting.code
       left join data_reliability
         on sitefile.reliability_cd = data_reliability.code
       left join nat_aqfr
         on sitefile.nat_aqfr_cd = nat_aqfr.nat_aqfr_cd and
            sitefile.country_cd = nat_aqfr.country_cd and
            sitefile.state_cd = nat_aqfr.state_cd
       left join aqfr
         on sitefile.aqfr_cd = aqfr.aqfr_cd and
            sitefile.country_cd = aqfr.country_cd and
            sitefile.state_cd = aqfr.state_cd
       left join aqfr_type
         on sitefile.aqfr_type_cd = aqfr_type.code
