package gov.acwi.wqp.etl.natdb.agency;

import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.natdb.BaseProcessor;

public class AgencyProcessor extends BaseProcessor implements ItemProcessor<Agency, Agency> {

	@Override
	public Agency process(Agency source) throws Exception {
		Agency result = new Agency();
		result.setCode(trimString(source.getCode()));
		result.setName(trimString(source.getName()));

		return result;
	}
}
