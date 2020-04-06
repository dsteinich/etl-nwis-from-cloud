package gov.acwi.wqp.etl.natdb;

import org.junit.jupiter.api.Test;

import gov.acwi.wqp.etl.natdb.gw.BasicLookup;
import gov.acwi.wqp.etl.natdb.gw.BasicLookupProcessor;
import gov.acwi.wqp.etl.natdb.gw.GwReflist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasicLookupProcessorTest {

	private BasicLookupProcessor processor = new BasicLookupProcessor();

	@Test
	public void processorTest() throws Exception {
		GwReflist source1 = new GwReflist("Tblname", "ABC", "A name", 1, "A description", 'Y');
		GwReflist source2 = new GwReflist("Tblname", "XYZ", "Another name", 2, "Another description  ", 'N');

		BasicLookup dest1 = processor.process(source1);
		BasicLookup dest2 = processor.process(source2);

		assertEquals(dest1.getCode(), "ABC");
		assertEquals(dest1.getName(), "A name");
		assertEquals(dest1.getDescription(), "A description");
		assertEquals(dest1.getSortOrder(), 1);
		assertTrue(dest1.isValidFlag());

		assertEquals(dest2.getCode(), "XYZ");
		assertEquals(dest2.getName(), "Another name");
		assertEquals(dest2.getDescription(), "Another description");
		assertEquals(dest2.getSortOrder(), 2);
		assertFalse(dest2.isValidFlag());
	}

}
