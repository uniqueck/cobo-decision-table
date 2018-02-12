package com.cobo.dt.model.lfet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringWriter;

import org.junit.Test;
import org.simpleframework.xml.core.AttributeException;
import org.simpleframework.xml.core.Persister;

public class SourceCodeTest {
	private SourceCode createUnderTest() {
		return createUnderTest("Perl", "LogArg", "$step eq $$symbol");
	}
	
	private SourceCode createUnderTest(String codeLanguage, String codeType, String value) {
		return new SourceCode(codeLanguage, codeType, value);
	}

	private String createExpectedXml() {
		return "<SourceCode value='$step eq $$symbol' codeLanguage='Perl' sourceCodeType='LogArg'/>";
	}

	private String persist(SourceCode sourceCode) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(sourceCode, out);
		return out.toString();		
	}
	
	@Test
	public void testSourceCode() throws Exception {
		SourceCode sourceCode = createUnderTest();
		assertEquals("Perl", sourceCode.getCodeLanguage());
		assertEquals("LogArg", sourceCode.getSourceCodeType());
		assertEquals("$step eq $$symbol", sourceCode.getValue());
	}

	@Test
	public void testPersistSourceCode_codeLanguageAndTypeAndValueGiven_noError() throws Exception {
		SourceCode sourceCode = createUnderTest();
		String xml = persist(sourceCode);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}
	
	@Test
	public void testPersistSourceCode_codeLanguageNotGiven_Error() throws Exception {
		SourceCode sourceCode = createUnderTest(null, "LogArg", "$step eq $$symbol");
		
		try {
			persist(sourceCode);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("codeLanguage is null"));
		}	
	}

	@Test
	public void testPersistSourceCode_sourceCodeTypeNotGiven_Error() throws Exception {
		SourceCode sourceCode = createUnderTest("Perl", null, "$step eq $$symbol");
		
		try {
			persist(sourceCode);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("sourceCodeType is null"));
		}	
	}

	@Test
	public void testPersistSourceCode_valueNotGiven_Error() throws Exception {
		SourceCode sourceCode = createUnderTest("Perl", "LogArg", null);
		
		try {
			persist(sourceCode);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("value is null"));
		}	
	}

}
