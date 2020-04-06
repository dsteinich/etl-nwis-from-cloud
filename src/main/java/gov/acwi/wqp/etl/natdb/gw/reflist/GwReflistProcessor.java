package gov.acwi.wqp.etl.natdb.gw.reflist;

import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.natdb.BaseProcessor;
import gov.acwi.wqp.etl.natdb.gw.GwReflist;

public class GwReflistProcessor extends BaseProcessor implements ItemProcessor<GwReflist, GwReflist> {

	@Override
	public GwReflist process(GwReflist source) throws Exception {
		GwReflist result = new GwReflist();

		result.setGwEdTblNm(trimString(source.getGwEdTblNm()));
		result.setGwRefCd(trimString(source.getGwRefCd()));
		result.setGwRefNm(trimString(source.getGwRefNm()));
		result.setGwSortNu(source.getGwSortNu());
		result.setGwRefDs(trimString(source.getGwRefDs()));
		result.setGwValidFg(source.getGwValidFg());

		return result;
	}
}
