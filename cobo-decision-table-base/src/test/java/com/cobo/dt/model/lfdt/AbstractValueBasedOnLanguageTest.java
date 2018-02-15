package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringWriter;

import org.junit.Test;
import org.simpleframework.xml.core.AttributeException;
import org.simpleframework.xml.core.Persister;

public class AbstractValueBasedOnLanguageTest {
	private AbstractValueBasedOnLanguage createUnderTest() {
		return createUnderTest("English", "myValue");
	}
	
	private AbstractValueBasedOnLanguage createUnderTest(String language, String value) {
		return new AbstractValueBasedOnLanguage(language, value) {};
	}

	private String createExpectedXml() {
		return "< value='myValue' language='English'/>";
	}

	private String persist(AbstractValueBasedOnLanguage value) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(value, out);
		return out.toString();		
	}

	@Test
	public void testValueBasedOnLanguage() throws Exception {
		AbstractValueBasedOnLanguage value = createUnderTest("English", null);
		assertEquals("English", value.getLanguage());
		assertNull(value.getValue());
		
		value = createUnderTest();
		assertEquals("English", value.getLanguage());
		assertEquals("myValue", value.getValue());
	}
	
	@Test
	public void testPersistValueBasedOnLanguage_LanguageAndValueGiven_noError() throws Exception {
		AbstractValueBasedOnLanguage value = createUnderTest();
		String xml = persist(value);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}

	@Test
	public void testPersistValueBasedOnLanguage_ValueNotGiven_error() throws Exception {
		AbstractValueBasedOnLanguage value = createUnderTest("English", null);
		
		try {
			persist(value);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("value is null"));
		}
	}
	
	@Test
	public void testPersistValueBasedOnLanguage_LanguageNotGiven_error() throws Exception {
		AbstractValueBasedOnLanguage value = createUnderTest(null, "myValue");
		
		try {
			persist(value);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("language is null"));
		}
	}
}
