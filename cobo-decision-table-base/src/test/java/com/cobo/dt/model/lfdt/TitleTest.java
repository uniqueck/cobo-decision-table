package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.simpleframework.xml.core.AttributeException;

public class TitleTest extends AbstractLfdtTest<Title> {
	private Title createUnderTest() {
		return createUnderTest("English", "myTitle");
	}
	
	private Title createUnderTest(String language, String value) {
		return new Title(language, value);
	}

	private String createExpectedXml() {
		return "<Title value='myTitle' language='English'/>".replaceAll("'", "\"");
	}

	@Test
	public void testTitle() throws Exception {
		Title title = createUnderTest("English", null);
		assertTitle(title, null, "English");
		
		title = createUnderTest();
		assertTitle(title, "myTitle", "English");
	}
	
	@Test
	public void testPersistTitle_LanguageAndValueGiven_noError() throws Exception {
		Title title = createUnderTest();
		String xml = persist(title);
		assertEquals(createExpectedXml(), xml);
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
	
	@Test
	public void testConvertTitleXML() throws Exception {
		Title title = convertToModel(createExpectedXml());
		assertTitle(title, "myTitle", "English");
	}
}
