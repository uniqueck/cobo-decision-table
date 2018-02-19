package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

public class ConditionTest {
	private static String NEW_LINE = "\n";
	
	private Condition createUnderTest(String uid, Title title, Text text, List<SourceCode> sourceCodes, List<ConditionOccurrence> occurences) {
		return new Condition(uid, title, text, sourceCodes, occurences);
	}

	private String createExpectedXml_withoutOccurences() {
		return "<condition uId='12345'>" + NEW_LINE
		     + "   <Title value='title' language='English'/>" + NEW_LINE
		     + "   <Text value='docuText' language='English'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem = ();' codeLanguage='Perl' sourceCodeType='Prolog'/>" + NEW_LINE
		     + "   <ConditionOccurrences/>" + NEW_LINE
		     + "</condition>";
	}
	
	private String createExpectedXml_withOccurences() {
		return "<condition uId='12345'>" + NEW_LINE
		     + "   <Title value='title' language='English'/>" + NEW_LINE
		     + "   <Text value='docuText' language='English'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem = ();' codeLanguage='Perl' sourceCodeType='Prolog'/>" + NEW_LINE
		     + "   <ConditionOccurrences>" + NEW_LINE
		     + "      <ConditionOccurrence uId='11111'>" + NEW_LINE
		     + "         <Symbol value='symbol1' language='English'/>" + NEW_LINE
		     + "         <Title value='title1' language='English'/>" + NEW_LINE
		     + "         <SourceCode value='sourceCode1' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "      </ConditionOccurrence>" + NEW_LINE
		     + "      <ConditionOccurrence uId='22222'>" + NEW_LINE
		     + "         <Symbol value='symbol2' language='English'/>" + NEW_LINE
		     + "         <Title value='title2' language='English'/>" + NEW_LINE
		     + "         <SourceCode value='sourceCode2' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "      </ConditionOccurrence>" + NEW_LINE
		     + "   </ConditionOccurrences>" + NEW_LINE
		     + "</condition>";
	}

	
	private String persist(Condition condition) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(condition, out);
		return out.toString();		
	}

	@Test
	public void testCondition() throws Exception {
		String uid = "12345";
		Title title = new Title("English", "title");
		Text text = new Text("English", "docuText");
		SourceCode sourceCode = new SourceCode("Perl", "LogArg", "$foundItem");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode);

		List<ConditionOccurrence> occurences = new ArrayList<ConditionOccurrence>();
		
		Condition condition = createUnderTest(uid, title, text, sourceCodes, occurences);
		
		assertSame(uid, condition.getUId());
		assertSame(title, condition.getTitle());
		assertSame(text, condition.getText());
		assertSame(sourceCodes, condition.getSourceCodes());
		assertSame(occurences, condition.getOccurences());
	}

	@Test
	public void testPersistModel_withoutOccurences() throws Exception {
		SourceCode sourceCode1 = new SourceCode("Perl", "LogArg", "$foundItem");
		SourceCode sourceCode2 = new SourceCode("Perl", "Prolog", "$foundItem = ();");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode1);
		sourceCodes.add(sourceCode2);
		
		Condition condition = createUnderTest("12345", new Title("English", "title"),
				new Text("English", "docuText"), sourceCodes, null);
		
		String xml = persist(condition);
		assertEquals(createExpectedXml_withoutOccurences().replaceAll("'", "\""), xml);
	}
	
	@Test
	public void testPersistModel_withOccurences() throws Exception {
		SourceCode sourceCode1 = new SourceCode("Perl", "LogArg", "$foundItem");
		SourceCode sourceCode2 = new SourceCode("Perl", "Prolog", "$foundItem = ();");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode1);
		sourceCodes.add(sourceCode2);

		ConditionOccurrence occurence1 = new ConditionOccurrence("11111", new Symbol("English", "symbol1"), new Title("English", "title1"), new SourceCode("Perl", "LogArg", "sourceCode1"));
		ConditionOccurrence occurence2 = new ConditionOccurrence("22222", new Symbol("English", "symbol2"), new Title("English", "title2"), new SourceCode("Perl", "LogArg", "sourceCode2"));			
		List<ConditionOccurrence> occurences = new ArrayList<ConditionOccurrence>();
		occurences.add(occurence1);
		occurences.add(occurence2);
		
		Condition condition = createUnderTest("12345", new Title("English", "title"),
				new Text("English", "docuText"), sourceCodes, occurences);
		
		String xml = persist(condition);
		assertEquals(createExpectedXml_withOccurences().replaceAll("'", "\""), xml);
	}
}
