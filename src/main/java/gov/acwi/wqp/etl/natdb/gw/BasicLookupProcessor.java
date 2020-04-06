package gov.acwi.wqp.etl.natdb.gw;

import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.natdb.BaseProcessor;

public class BasicLookupProcessor extends BaseProcessor implements ItemProcessor<GwReflist, BasicLookup> {

	@Override
	public BasicLookup process(GwReflist gwReflist) throws Exception {
		BasicLookup result = new BasicLookup();
		result.setCode(trimString(gwReflist.getGwRefCd()));
		result.setName(trimString(gwReflist.getGwRefNm()));
		result.setSortOrder(gwReflist.getGwSortNu());
		result.setDescription(trimString(gwReflist.getGwRefDs()));
		result.setValidFlag(gwReflist.getGwValidFg() == 'Y');

		return result;
	}

}
