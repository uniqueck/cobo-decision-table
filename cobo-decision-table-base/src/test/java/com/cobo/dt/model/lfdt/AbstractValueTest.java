package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.simpleframework.xml.core.AttributeException;

public class AbstractValueTest extends AbstractLfdtTest<AbstractValue> {
	private AbstractValue createUnderTest(String value) {
		return new AbstractValue(value) {};
	}

	private String createExpectedXml() {
		return "< value='myValue'/>".replaceAll("'", "\"");
	}

	@Test
	public void testValue() throws Exception {
		AbstractValue value = createUnderTest("myValue");
		assertEquals("myValue", value.getValue());
	}
	
	@Test
	public void testPersistValue_ValueGiven_noError() throws Exception {
		AbstractValue value = createUnderTest("myValue");
		String xml = persist(value);
		assertEquals(createExpectedXml(), xml);
	}

	@Test
	public void testPersistValue_ValueNotGiven_error() throws Exception {
		AbstractValue value = createUnderTest(null);
		
		try {
			persist(value);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("value is null"));
		}
	}
}
