package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringWriter;

import org.junit.Test;
import org.simpleframework.xml.core.AttributeException;
import org.simpleframework.xml.core.Persister;

public class AbstractValueTest {
	private AbstractValue createUnderTest(String value) {
		return new AbstractValue(value) {};
	}

	private String createExpectedXml() {
		return "< value='myValue'/>";
	}

	private String persist(AbstractValue value) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(value, out);
		return out.toString();		
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
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
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
