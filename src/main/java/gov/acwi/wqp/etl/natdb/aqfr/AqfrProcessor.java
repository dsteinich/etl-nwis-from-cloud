package gov.acwi.wqp.etl.natdb.aqfr;

import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.natdb.BaseProcessor;

public class AqfrProcessor extends BaseProcessor implements ItemProcessor<Aqfr, Aqfr> {

	@Override
	public Aqfr process(Aqfr source) throws Exception {
		Aqfr result = new Aqfr();
		result.setCountryCd(trimString(source.getCountryCd()));
		result.setStateCd(trimString(source.getStateCd()));
		result.setAqfrCd(trimString(source.getAqfrCd()));
		result.setAqfrNm(trimString(source.getAqfrNm()));
		result.setAqfrMd(trimString(source.getAqfrMd()));
		
		return result;
	}
}
