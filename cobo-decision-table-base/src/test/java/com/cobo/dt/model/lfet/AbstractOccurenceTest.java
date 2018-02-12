package com.cobo.dt.model.lfet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

public class AbstractOccurenceTest {
	private static String NEW_LINE = "\n";
	
	private AbstractOccurence createUnderTest(String uid, Symbol symbol, Title title, SourceCode sourceCode) {
		return new AbstractOccurence(uid, symbol, title, sourceCode) {};
	}

	private String createExpectedXml() {
		return "< uId='12345'>" + NEW_LINE
			 + "   <Symbol value='symbol' language='English'/>" + NEW_LINE
			 + "   <Title value='title' language='English'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "</>";
	}
	
	private String persist(AbstractOccurence occurence) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(occurence, out);
		return out.toString();		
	}

	@Test
	public void testOccurences() throws Exception {
		String uid = "12345";
		Symbol symbol = new Symbol("English", "symbol");
		Title title = new Title("English", "title");
		SourceCode sourceCode = new SourceCode("Perl", "LogArg", "$foundItem");
		
		AbstractOccurence occurence = createUnderTest(uid, symbol, title, sourceCode);
		assertSame(uid, occurence.getUId());
		assertSame(symbol, occurence.getSymbol());
		assertSame(title, occurence.getTitle());
		assertSame(sourceCode, occurence.getSourceCode());
	}

	@Test
	public void testPersistModel() throws Exception {
		SourceCode sourceCode1 = new SourceCode("Perl", "LogArg", "$foundItem");
		SourceCode sourceCode2 = new SourceCode("Perl", "Prolog", "$foundItem = ();");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode1);
		sourceCodes.add(sourceCode2);

		AbstractOccurence occurence = createUnderTest("12345", new Symbol("English", "symbol"), new Title("English", "title"),
				new SourceCode("Perl", "LogArg", "$foundItem"));
		
		String xml = persist(occurence);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}
}
