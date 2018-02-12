package com.cobo.dt.model.lfet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringWriter;

import org.junit.Test;
import org.simpleframework.xml.core.AttributeException;
import org.simpleframework.xml.core.Persister;

public class TitleTest {
	private Title createUnderTest() {
		return createUnderTest("English", "myTitle");
	}
	
	private Title createUnderTest(String language, String value) {
		return new Title(language, value);
	}

	private String createExpectedXml() {
		return "<Title value='myTitle' language='English'/>";
	}

	private String persist(Title title) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(title, out);
		return out.toString();		
	}

	@Test
	public void testTitle() throws Exception {
		Title title = createUnderTest("English", null);
		assertEquals("English", title.getLanguage());
		assertNull(title.getValue());
		
		title = createUnderTest();
		assertEquals("English", title.getLanguage());
		assertEquals("myTitle", title.getValue());
	}
	
	@Test
	public void testPersistTitle_LanguageAndValueGiven_noError() throws Exception {
		Title title = createUnderTest();
		String xml = persist(title);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}

	@Test
	public void testPersistTitle_ValueNotGiven_error() throws Exception {
		Title title = createUnderTest("English", null);
		
		try {
			persist(title);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("value is null"));
		}
	}
	
	@Test
	public void testPersistTitle_LanguageNotGiven_error() throws Exception {
		Title title = createUnderTest(null, "myTitle");
		
		try {
			persist(title);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("language is null"));
		}
	}
}
