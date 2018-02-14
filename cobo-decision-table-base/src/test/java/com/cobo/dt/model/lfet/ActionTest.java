package com.cobo.dt.model.lfet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

public class ActionTest {
	private static String NEW_LINE = "\n";
	
	private Action createUnderTest(String uid, Title title, Text text, List<SourceCode> sourceCodes, List<ActionOccurence> occurences) {
		return new Action(uid, title, text, sourceCodes, occurences);
	}

	private String createExpectedXml_withoutOccurences() {
		return "<action uId='12345'>" + NEW_LINE
		     + "   <Title value='title' language='English'/>" + NEW_LINE
		     + "   <Text value='docuText' language='English'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem = ();' codeLanguage='Perl' sourceCodeType='Prolog'/>" + NEW_LINE
		     + "   <ActionOccurrences/>" +NEW_LINE
		     + "</action>";
	}

	private String createExpectedXml_withOccurences() {
		return "<action uId='12345'>" + NEW_LINE
		     + "   <Title value='title' language='English'/>" + NEW_LINE
		     + "   <Text value='docuText' language='English'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "   <SourceCode value='$foundItem = ();' codeLanguage='Perl' sourceCodeType='Prolog'/>" + NEW_LINE
		     + "   <ActionOccurrences>" + NEW_LINE
		     + "      <ActionOccurrence uId='11111'>" + NEW_LINE
		     + "         <Symbol value='symbol1' language='English'/>" + NEW_LINE
		     + "         <Title value='title1' language='English'/>" + NEW_LINE
		     + "         <SourceCode value='sourceCode1' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
		     + "      </ActionOccurrence>" + NEW_LINE
		     + "      <ActionOccurrence uId='22222'>" + NEW_LINE
		     + "         <Symbol value='symbol2' language='English'/>" + NEW_LINE
		     + "         <Title value='title2' language='English'/>" + NEW_LINE
		     + "         <SourceCode value='sourceCode2' codeLanguage='Perl' sourceCodeType='LogArg'/>" + NEW_LINE
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

		List<ActionOccurence> occurences = new ArrayList<ActionOccurence>();
		
		Action action = createUnderTest(uid, title, text, sourceCodes, occurences);
		
		assertSame(uid, action.getUId());
		assertSame(title, action.getTitle());
		assertSame(text, action.getText());
		assertSame(sourceCodes, action.getSourceCodes());
		assertSame(occurences, action.getOccurences());
	}

	@Test
	public void testPersistModel_withoutOccurences() throws Exception {
		SourceCode sourceCode1 = new SourceCode("Perl", "LogArg", "$foundItem");
		SourceCode sourceCode2 = new SourceCode("Perl", "Prolog", "$foundItem = ();");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode1);
		sourceCodes.add(sourceCode2);

		Action action = createUnderTest("12345", new Title("English", "title"),
				new Text("English", "docuText"), sourceCodes, null);
		
		String xml = persist(action);
		assertEquals(createExpectedXml_withoutOccurences().replaceAll("'", "\""), xml);
	}
	
	@Test
	public void testPersistModel_withOccurences() throws Exception {
		SourceCode sourceCode1 = new SourceCode("Perl", "LogArg", "$foundItem");
		SourceCode sourceCode2 = new SourceCode("Perl", "Prolog", "$foundItem = ();");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		sourceCodes.add(sourceCode1);
		sourceCodes.add(sourceCode2);

		ActionOccurence occurence1 = new ActionOccurence("11111", new Symbol("English", "symbol1"), new Title("English", "title1"), new SourceCode("Perl", "LogArg", "sourceCode1"));
		ActionOccurence occurence2 = new ActionOccurence("22222", new Symbol("English", "symbol2"), new Title("English", "title2"), new SourceCode("Perl", "LogArg", "sourceCode2"));			
		List<ActionOccurence> occurences = new ArrayList<ActionOccurence>();
		occurences.add(occurence1);
		occurences.add(occurence2);
		
		Action action = createUnderTest("12345", new Title("English", "title"),
				new Text("English", "docuText"), sourceCodes, occurences);
		
		String xml = persist(action);
		assertEquals(createExpectedXml_withOccurences().replaceAll("'", "\""), xml);
	}
}
