package gov.acwi.wqp.etl.natdb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;

public class BaseProcessorTest {
	private BaseProcessor absCls = Mockito.mock(BaseProcessor.class, Mockito.CALLS_REAL_METHODS);

	@Test
	public void noLeadingOrTrailingSpaceTest() {
		assertEquals(absCls.trimString("My Name"), "My Name");
	}

	@Test
	public void leadingWhitespaceTest() {
		assertEquals(absCls.trimString("\t My Name"), "My Name");
	}

	@Test
	public void trailingWhitespaceTest() {
		assertEquals(absCls.trimString("My Name \t"), "My Name");
	}

	@Test
	public void leadingAndTrailingWhitespacTest() {
		assertEquals(absCls.trimString("  \t My Name  \n"), "My Name");
	}

}
