# etl-nwis-from-cloud
WQP ETL from sources in the cloud into the nwis schema

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a2f49e368a69454a92a326b3e649f18e)](https://www.codacy.com/app/usgs_wma_dev/etl-nwis-from-cloud?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=NWQMC/etl-nwis-from-cloud&amp;utm_campaign=Badge_Grade)

This repo contains a spring batch application which runs the ETL for the WQP NWIS
schema where the source of the information is from CHS sources. Currently this includes nwq_data_checks

## Local Configuration
In order to run locally, you will need connection information to the NatDB database. For the WQP NWIS schema, you can use the docker commands in schema-nwis-ws-star to spin up a local postgres database.

You will need to create an application.yml file containing the following:
```yaml
NWIS_DATABASE_ADDRESS: <url to database>
NWIS_DATABASE_PORT: <port # of nwis database>
NWIS_DTABASE_NAME: <wqp_db>
NWIS_SCHEMA_OWNER_USERNAME: <nwis_ws_star>
NWIS_SCHEMA_OWENR_PASSWORD: <changeMe>

NWQ_DATA_CHECKS_DATABASE_ADDRESS: <url to database with nwq_data_checks
NWQ_DATA_CHECKS_DATABASE_PORT: <1521>
NWQ_DATA_CHECKS_DATABASE_NAME: <NNDCTS>
NWQ_DATA_CHECKS_USERNAME: <nwq_data_checks>
NWQ_DATA_CHECKS_PASSWORD: <changeMe>
```

You can then run the ETL with the following:
```% mvn spring-boot:run```

The project is currently set up to run the job every time the command is launched.