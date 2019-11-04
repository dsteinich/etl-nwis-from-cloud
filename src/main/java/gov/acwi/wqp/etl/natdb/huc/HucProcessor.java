package gov.acwi.wqp.etl.natdb.huc;

import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.natdb.BaseProcessor;

public class HucProcessor extends BaseProcessor implements ItemProcessor<Huc, Huc> {

	@Override
	public Huc process(Huc source) throws Exception {
		Huc result = new Huc();
		result.setCode(trimString(source.getCode()));
		result.setName(trimString(source.getName()));
		result.setClassCode(trimString(source.getClassCode()));

		return result;
	}
}
