package com.cobo.dt.model.lfet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringWriter;

import org.junit.Test;
import org.simpleframework.xml.core.AttributeException;
import org.simpleframework.xml.core.Persister;

public class SymbolTest {
	private Symbol createUnderTest() {
		return createUnderTest("English", "myValue");
	}
	
	private Symbol createUnderTest(String language, String value) {
		return new Symbol(language, value);
	}

	private String createExpectedXml() {
		return "<Symbol value='myValue' language='English'/>";
	}

	private String persist(Symbol symbol) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(symbol, out);
		return out.toString();		
	}

	@Test
	public void testSymbol() throws Exception {
		Symbol symbol = createUnderTest("English", null);
		assertEquals("English", symbol.getLanguage());
		assertNull(symbol.getValue());
		
		symbol = createUnderTest();
		assertEquals("English", symbol.getLanguage());
		assertEquals("myValue", symbol.getValue());
	}
	
	@Test
	public void testPersistSymbol_LanguageAndValueGiven_noError() throws Exception {
		Symbol value = createUnderTest();
		String xml = persist(value);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}

	@Test
	public void testPersistSymbol_ValueNotGiven_error() throws Exception {
		Symbol symbol = createUnderTest("English", null);
		
		try {
			persist(symbol);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("value is null"));
		}
	}
	
	@Test
	public void testPersistSymbol_LanguageNotGiven_error() throws Exception {
		Symbol symbol = createUnderTest(null, "myValue");
		
		try {
			persist(symbol);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("language is null"));
		}
	}
}
