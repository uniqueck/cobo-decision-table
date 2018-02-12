package com.cobo.dt.model.lfet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringWriter;

import org.junit.Test;
import org.simpleframework.xml.core.AttributeException;
import org.simpleframework.xml.core.Persister;

public class TextTest {
	private Text createUnderTest() {
		return createUnderTest("English", "this is a docu");
	}
	
	private Text createUnderTest(String language, String value) {
		return new Text(language, value);
	}

	private String createExpectedXml() {
		return "<Text value='this is a docu' language='English'/>";
	}

	private String persist(Text text) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(text, out);
		return out.toString();		
	}

	@Test
	public void testText() throws Exception {
		Text text = createUnderTest("English", null);
		assertEquals("English", text.getLanguage());
		assertNull(text.getValue());
		
		text = createUnderTest();
		assertEquals("English", text.getLanguage());
		assertEquals("this is a docu", text.getValue());
	}
	
	@Test
	public void testPersistText_LanguageAndValueGiven_noError() throws Exception {
		Text text = createUnderTest();
		String xml = persist(text);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}

	@Test
	public void testPersistText_ValueNotGiven_error() throws Exception {
		Text text = createUnderTest("English", null);
		
		try {
			persist(text);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("value is null"));
		}
	}
	
	@Test
	public void testPersistText_LanguageNotGiven_error() throws Exception {
		Text text = createUnderTest(null, "this is a docu");
		
		try {
			persist(text);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("language is null"));
		}
	}
}
