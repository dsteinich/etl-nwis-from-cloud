package gov.acwi.wqp.etl.natdb.gw.reflist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gov.acwi.wqp.etl.natdb.gw.GwReflist;

public class GwReflistProcessorTest {

	private GwReflistProcessor processor = new GwReflistProcessor();

	@Test
	public void processorTest() throws Exception {
		GwReflist source1 = new GwReflist(" Tblname ", " ABC ", " A name ", 1, " A description ", 'Y');
		GwReflist source2 = new GwReflist(" Tblname ", " XYZ ", " Another name ", 2, " Another description  ", 'N');

		GwReflist dest1 = processor.process(source1);
		GwReflist dest2 = processor.process(source2);

		assertEquals(dest1.getGwEdTblNm(), "Tblname");
		assertEquals(dest1.getGwRefCd(), "ABC");
		assertEquals(dest1.getGwRefNm(), "A name");
		assertEquals(dest1.getGwRefDs(), "A description");
		assertEquals(dest1.getGwSortNu(), 1);
		assertEquals(dest1.getGwValidFg(), 'Y');

		assertEquals(dest2.getGwEdTblNm(), "Tblname");
		assertEquals(dest2.getGwRefCd(), "XYZ");
		assertEquals(dest2.getGwRefNm(), "Another name");
		assertEquals(dest2.getGwRefDs(), "Another description");
		assertEquals(dest2.getGwSortNu(), 2);
		assertEquals(dest2.getGwValidFg(), 'N');
	}

}
