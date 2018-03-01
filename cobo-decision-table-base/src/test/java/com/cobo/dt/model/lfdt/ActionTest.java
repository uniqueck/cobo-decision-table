package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

public class ActionTest {
	private static String NEW_LINE = "\n";
	
	private Action createUnderTest(String uid, Title title, Text text, List<SourceCode> sourceCodes, List<ActionOccurrence> occurences, List<Url> urls) {
		return new Action(uid, title, text, sourceCodes, occurences, urls);
	}

	private String createExpectedXml_withoutOccurencesAndUrls() {
		return "<action uId='12345'>" + NEW_LINE
		     + "   <Title value='title' language='English'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem = ();' codeLanguage='Perl' sourceCodeType='Prolog'/>" + NEW_LINE
		     + "   <Text value='docuText' language='English'/>" + NEW_LINE
		     + "   <UrlsOut/>" + NEW_LINE 
		     + "   <ActionOccurrences/>" + NEW_LINE
		     + "</action>";
	}

	private String createExpectedXml_withOccurencesAndUrls() {
		return "<action uId='12345'>" + NEW_LINE
		     + "   <Title value='title' language='English'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem = ();' codeLanguage='Perl' sourceCodeType='Prolog'/>" + NEW_LINE
		     + "   <Text value='docuText' language='English'/>" + NEW_LINE
		     + "   <UrlsOut>" + NEW_LINE
		     + "      <Url title='title1' url='http://url1'/>" + NEW_LINE
		     + "      <Url title='title2' url='http://url2'/>" + NEW_LINE
		     + "   </UrlsOut>" + NEW_LINE
		     + "   <ActionOccurrences>" + NEW_LINE
		     + "      <ActionOccurrence uId='11111'>" + NEW_LINE
		     + "         <Symbol value='symbol1' language='English'/>" + NEW_LINE
		     + "         <Title value='title1' language='English'/>" + NEW_LINE
	         + "         <Text value='docuText1' language='English'/>" + NEW_LINE
		     + "         <SourceCode value='value11' codeLanguage='Perl' sourceCodeType='sourceCodeType11'/>" + NEW_LINE
		     + "         <SourceCode value='value12' codeLanguage='Perl' sourceCodeType='sourceCodeType12'/>" + NEW_LINE
		     + "         <UrlsOut>" + NEW_LINE
		     + "            <Url title='title11' url='http://url11'/>" + NEW_LINE
		     + "            <Url title='title21' url='http://url21'/>" + NEW_LINE
		     + "         </UrlsOut>" + NEW_LINE 
		     + "      </ActionOccurrence>" + NEW_LINE
		     + "      <ActionOccurrence uId='22222'>" + NEW_LINE
		     + "         <Symbol value='symbol2' language='English'/>" + NEW_LINE
		     + "         <Title value='title2' language='English'/>" + NEW_LINE
	         + "         <Text value='docuText2' language='English'/>" + NEW_LINE
		     + "         <SourceCode value='value11' codeLanguage='Perl' sourceCodeType='sourceCodeType11'/>" + NEW_LINE
		     + "         <SourceCode value='value12' codeLanguage='Perl' sourceCodeType='sourceCodeType12'/>" + NEW_LINE
		     + "         <UrlsOut>" + NEW_LINE
		     + "            <Url title='title12' url='http://url12'/>" + NEW_LINE
		     + "            <Url title='title22' url='http://url22'/>" + NEW_LINE
		     + "         </UrlsOut>" + NEW_LINE
		     + "      </ActionOccurrence>" + NEW_LINE
		     + "   </ActionOccurrences>" + NEW_LINE
		     + "</action>";
	}
	
	private String persist(Action action) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(action, out);
		return out.toString();		
	}

	@Test
	public void testAction() throws Exception {
		String uid = "12345";
		Title title = new Title("English", "title");
		Text text = new Text("English", "docuText");
		
		SourceCode sourceCode = new SourceCode("Perl", "LogArg", "$foundItem");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode);

		List<ActionOccurrence> occurences = new ArrayList<ActionOccurrence>();
		
		List<Url> urls = new ArrayList<Url>();
		urls.add(new Url("title1", "http://url1", null));
		urls.add(new Url("title2", "http://url2", null));
		
		Action action = createUnderTest(uid, title, text, sourceCodes, occurences, urls);
		
		assertSame(uid, action.getUId());
		assertSame(title, action.getTitle());
		assertSame(text, action.getText());
		assertSame(sourceCodes, action.getSourceCodes());
		assertSame(occurences, action.getOccurrences());
		assertSame(urls, action.getUrls());
	}

	@Test
	public void testPersistModel_withoutOccurencesAndUrls() throws Exception {
		SourceCode sourceCode1 = new SourceCode("Perl", "LogArg", "$foundItem");
		SourceCode sourceCode2 = new SourceCode("Perl", "Prolog", "$foundItem = ();");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode1);
		sourceCodes.add(sourceCode2);

		Action action = createUnderTest("12345", new Title("English", "title"),
				new Text("English", "docuText"), sourceCodes, null, null);
		
		String xml = persist(action);
		assertEquals(createExpectedXml_withoutOccurencesAndUrls().replaceAll("'", "\""), xml);
	}
	
	@Test
	public void testPersistModel_withOccurences() throws Exception {
		SourceCode sourceCode1 = new SourceCode("Perl", "LogArg", "$foundItem");
		SourceCode sourceCode2 = new SourceCode("Perl", "Prolog", "$foundItem = ();");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode1);
		sourceCodes.add(sourceCode2);

		List<SourceCode> sourceCodesForActionOccurrence1 = new ArrayList<SourceCode>();
		sourceCodesForActionOccurrence1.add(new SourceCode("Perl", "sourceCodeType11", "value11"));
		sourceCodesForActionOccurrence1.add(new SourceCode("Perl", "sourceCodeType12", "value12"));
		List<Url> urlsForConditionOccurrence1 = new ArrayList<Url>();
		urlsForConditionOccurrence1.add(new Url("title11", "http://url11", null));
		urlsForConditionOccurrence1.add(new Url("title21", "http://url21", null));
		ActionOccurrence occurence1 = new ActionOccurrence("11111", new Symbol("English", "symbol1"), new Title("English", "title1"), new Text("English", "docuText1"), sourceCodesForActionOccurrence1, urlsForConditionOccurrence1);
		
		List<SourceCode> sourceCodesForActionOccurrence2 = new ArrayList<SourceCode>();
		sourceCodesForActionOccurrence2.add(new SourceCode("Perl", "sourceCodeType11", "value11"));
		sourceCodesForActionOccurrence2.add(new SourceCode("Perl", "sourceCodeType12", "value12"));
		List<Url> urlsForConditionOccurrence2 = new ArrayList<Url>();
		urlsForConditionOccurrence2.add(new Url("title12", "http://url12", null));
		urlsForConditionOccurrence2.add(new Url("title22", "http://url22", null));
		ActionOccurrence occurence2 = new ActionOccurrence("22222", new Symbol("English", "symbol2"), new Title("English", "title2"), new Text("English", "docuText2"), sourceCodesForActionOccurrence2, urlsForConditionOccurrence2);			
		
		List<ActionOccurrence> occurences = new ArrayList<ActionOccurrence>();
		occurences.add(occurence1);
		occurences.add(occurence2);

		List<Url> urls = new ArrayList<Url>();
		urls.add(new Url("title1", "http://url1", null));
		urls.add(new Url("title2", "http://url2", null));

		Action action = createUnderTest("12345", new Title("English", "title"),
				new Text("English", "docuText"), sourceCodes, occurences, urls);
		
		String xml = persist(action);
		assertEquals(createExpectedXml_withOccurencesAndUrls().replaceAll("'", "\""), xml);
	}
}
