package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

public class ConditionOccurrenceTest {
	private static String NEW_LINE = "\n";
	
	private ConditionOccurrence createUnderTest(String uid, Symbol symbol, Title title, Text text, List<SourceCode> sourceCodes, List<Url> urls) {
		return new ConditionOccurrence(uid, symbol, title, text, sourceCodes, urls);
	}

	private String createExpectedXml() {
		return "<conditionOccurrence uId='12345'>" + NEW_LINE
			 + "   <Symbol value='symbol' language='English'/>" + NEW_LINE
			 + "   <Title value='title' language='English'/>" + NEW_LINE
			 + "   <Text value='docuText' language='English'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem = ();' codeLanguage='Perl' sourceCodeType='Prolog'/>" + NEW_LINE
		     + "   <UrlsOut>" + NEW_LINE
		     + "      <Url title='title1' url='http://url1'/>" + NEW_LINE
		     + "      <Url title='title2' url='http://url2'/>" + NEW_LINE
		     + "   </UrlsOut>" + NEW_LINE
		     + "</conditionOccurrence>";
	}
	
	private String persist(ConditionOccurrence occurrence) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(occurrence, out);
		return out.toString();		
	}

	@Test
	public void testActionOccurrence() throws Exception {
		String uid = "12345";
		Symbol symbol = new Symbol("English", "symbol");
		Title title = new Title("English", "title");
		Text text = new Text("English", "docuText");
		
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		SourceCode sourceCode1 = new SourceCode("Perl", "LogArg", "$foundItem");
		sourceCodes.add(sourceCode1);
		SourceCode sourceCode2 = new SourceCode("Perl", "LogArg2", "$foundItem2");
		sourceCodes.add(sourceCode2);
		
		List<Url> urls = new ArrayList<Url>();
		urls.add(new Url("title1", "http://url1", null));
		urls.add(new Url("title2", "http://url2", null));
		
		ConditionOccurrence occurrence = createUnderTest(uid, symbol, title, text, sourceCodes, urls);
		assertSame(uid, occurrence.getUId());
		assertSame(symbol, occurrence.getSymbol());
		assertSame(title, occurrence.getTitle());
		assertSame(text, occurrence.getText());
		assertSame(sourceCodes, occurrence.getSourceCodes());
		assertSame(urls, occurrence.getUrls());
	}

	@Test
	public void testPersistModel() throws Exception {
		SourceCode sourceCode1 = new SourceCode("Perl", "LogArg", "$foundItem");
		SourceCode sourceCode2 = new SourceCode("Perl", "Prolog", "$foundItem = ();");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode1);
		sourceCodes.add(sourceCode2);
		List<Url> urls = new ArrayList<Url>();
		urls.add(new Url("title1", "http://url1", null));
		urls.add(new Url("title2", "http://url2", null));

		ConditionOccurrence occurrence = createUnderTest("12345", new Symbol("English", "symbol"), new Title("English", "title"),
				new Text("English", "docuText"), sourceCodes, urls);
		
		String xml = persist(occurrence);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}
}
