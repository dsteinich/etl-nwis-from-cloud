package gov.acwi.wqp.etl.natdb.gw;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GwReferenceCode {

	public static final String SELECT_GW_REFLIST = "select * from gw_reflist where gw_ed_tbl_nm = ?";
	public static final String INSERT_GENERIC_LOOKUP = "insert into %s"
			+ " (code, name, sort_order, description, valid_flag) VALUES (:code, :name, :sortOrder, :description, :validFlag)";

	@Autowired
	@Qualifier("gwReflistFlow")
	private Flow gwReflistFlow;

	@Autowired
	@Qualifier("altitudeDatumFlow")
	private Flow altitudeDatumFlow;

	@Autowired
	@Qualifier("altitudeMethodFlow")
	private Flow altitudeMethodFlow;

	@Autowired
	@Qualifier("aquiferTypeFlow")
	private Flow aquiferTypeFlow;

	@Autowired
	@Qualifier("dataReliabilityFlow")
	private Flow dataReliabilityFlow;

	@Autowired
	@Qualifier("gwLevelAccuracyFlow")
	private Flow gwLevelAccuracyFlow;

	@Autowired
	@Qualifier("gwLevelDateTimeAccuracyFlow")
	private Flow gwLevelDateTimeAccuracyFlow;

	@Autowired
	@Qualifier("gwLevelMethodFlow")
	private Flow gwLevelMethodFlow;

	@Autowired
	@Qualifier("gwLevelSiteStatusFlow")
	private Flow gwLevelSiteStatusFlow;

	@Autowired
	@Qualifier("gwLevelSourceFlow")
	private Flow gwLevelSourceFlow;

	@Autowired
	@Qualifier("latLongDatumFlow")
	private Flow latLongDatumFlow;

	@Autowired
	@Qualifier("latLongMethodFlow")
	private Flow latLongMethodFlow;

	@Autowired
	@Qualifier("topographicSettingFlow")
	private Flow topographicSettingFlow;

	@Bean
	public Flow gwReferenceCodeFlow() {
		return new FlowBuilder<SimpleFlow>("gwReferenceCodeFlow")
				.start(gwReflistFlow)
				.next(altitudeDatumFlow)
				.next(altitudeMethodFlow)
				.next(aquiferTypeFlow)
				.next(dataReliabilityFlow)
				.next(gwLevelAccuracyFlow)
				.next(gwLevelDateTimeAccuracyFlow)
				.next(gwLevelMethodFlow)
				.next(gwLevelSiteStatusFlow)
				.next(gwLevelSourceFlow)
				.next(latLongDatumFlow)
				.next(latLongMethodFlow)
				.next(topographicSettingFlow)
				.build();
	}
}
