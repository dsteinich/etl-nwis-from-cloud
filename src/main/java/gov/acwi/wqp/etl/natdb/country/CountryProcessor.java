package gov.acwi.wqp.etl.natdb.country;

import org.springframework.batch.item.ItemProcessor;

import gov.acwi.wqp.etl.natdb.BaseProcessor;

public class CountryProcessor extends BaseProcessor implements ItemProcessor<Country, Country> {

	@Override
	public Country process(Country source) throws Exception {
		Country result = new Country();
		result.setCountryCd(trimString(source.getCountryCd()));
		result.setCountryNm(trimString(source.getCountryNm()));
		
		return result;
	}
}
