package gov.acwi.wqp.etl.natdb;

import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NatdbConfig {

	@Autowired
	@Qualifier("agencyFlow")
	private Flow agencyFlow;

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
	@Qualifier("aqfrFlow")
	private Flow aqfrFlow;

	@Autowired
	@Qualifier("bodyPartFlow")
	private Flow bodyPartFlow;

	@Autowired
	@Qualifier("citMethFlow")
	private Flow citMethFlow;

	@Autowired
	@Qualifier("countryFlow")
	private Flow countryFlow;

	@Autowired
	@Qualifier("countyFlow")
	private Flow countyFlow;

	@Autowired
	@Qualifier("dataReliabilityFlow")
	private Flow dataReliabilityFlow;

	@Autowired
	@Qualifier("fxdFlow")
	private Flow fxdFlow;

	@Autowired
	@Qualifier("hucFlow")
	private Flow hucFlow;

	@Autowired
	@Qualifier("hydCondCdFlow")
	private Flow hydCondCdFlow;

	@Autowired
	@Qualifier("hydEventCdFlow")
	private Flow hydEventCdFlow;

	@Autowired
	@Qualifier("latLongDatumFlow")
	private Flow latLongDatumFlow;

	@Autowired
	@Qualifier("latLongMethodFlow")
	private Flow latLongMethodFlow;

	@Autowired
	@Qualifier("methFlow")
	private Flow methFlow;

	@Autowired
	@Qualifier("methWithCitFlow")
	private Flow methWithCitFlow;

	@Autowired
	@Qualifier("natAqfrFlow")
	private Flow natAqfrFlow;

	@Autowired
	@Qualifier("parmMethFlow")
	private Flow parmMethFlow;

	@Autowired
	@Qualifier("parmAliasFlow")
	private Flow parmAliasFlow;

	@Autowired
	@Qualifier("parmFlow")
	private Flow parmFlow;

	@Autowired
	@Qualifier("protoOrgFlow")
	private Flow protoOrgFlow;

	@Autowired
	@Qualifier("siteTpFlow")
	private Flow siteTpFlow;

	@Autowired
	@Qualifier("stateFlow")
	private Flow stateFlow;

	@Autowired
	@Qualifier("topographicSettingFlow")
	private Flow topographicSettingFlow;

	@Autowired
	@Qualifier("tuFlow")
	private Flow tuFlow;

	@Autowired
	@Qualifier("valQualCdFlow")
	private Flow valQualCdFlow;

	@Bean
	public Flow natdbFlow() {
		return new FlowBuilder<SimpleFlow>("natdbFlow")
				.start(agencyFlow)
				.next(altitudeDatumFlow)
				.next(altitudeMethodFlow)
				.next(aquiferTypeFlow)
				.next(aqfrFlow)
				.next(bodyPartFlow)
				.next(citMethFlow)
				.next(countryFlow)
				.next(countyFlow)
				.next(dataReliabilityFlow)
				.next(fxdFlow)
				.next(hucFlow)
				.next(hydCondCdFlow)
				.next(hydEventCdFlow)
				.next(latLongDatumFlow)
				.next(latLongMethodFlow)
				.next(methFlow)
				.next(methWithCitFlow)
				.next(natAqfrFlow)
				.next(parmMethFlow)
				.next(parmAliasFlow)
				// parmMethFlow has to occur before parmFlow
				.next(parmFlow)
				.next(protoOrgFlow)
				.next(siteTpFlow)
				.next(stateFlow)
				.next(topographicSettingFlow)
				.next(tuFlow)
				.next(valQualCdFlow)
				.build();
	}

}
