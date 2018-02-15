package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.simpleframework.xml.core.Persister;

public class LFDecisionTableTest {
	private static String NEW_LINE = "\n";

	private LFDecisionTable createUnderTest(String version, String language, String saveUser, String saveDate,
			Title title, Text text, List<SourceCode> sourceCodes, List<Condition> conditions, List<Action> actions, List<Rule> rules) {
		return new LFDecisionTable(version, language, saveUser, saveDate,
				title, text, sourceCodes, conditions, actions, rules);
	}

	private String createExpectedXml() {
		return "<LFET version='version' language='English' saveUser='user' saveDate='date'>" + NEW_LINE
				+ "   <Title value='title' language='English'/>" + NEW_LINE
				+ "   <Text value='text' language='English'/>" + NEW_LINE
				+ "   <Conditions/>" + NEW_LINE
				+ "   <Actions/>" + NEW_LINE
				+ "   <Rules class=\"java.util.ArrayList\"/>" + NEW_LINE
				+ "</LFET>";		
	}
	
	private String persist(LFDecisionTable dt) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(dt, out);
		return out.toString();		
	}
	
	
	
	@Test
	public void testLFDecisionTable() throws Exception {
		Title title = new Title("English", "title");
		Text text = new Text("English", "text");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		List<Condition> conditions = new ArrayList<Condition>();
		List<Action> actions = new ArrayList<Action>();
		List<Rule> rules = new ArrayList<Rule>();
		
		LFDecisionTable dt = createUnderTest("version", "English", "user", "date",
				title, text, sourceCodes,
				conditions, actions, rules);
		
		assertEquals("version", dt.getVersion());
		assertEquals("English", dt.getLanguage());
		assertEquals("user", dt.getSaveUser());
		assertEquals("date", dt.getSaveDate());
		assertSame(title, dt.getTitle());
		assertSame(text, dt.getText());
		assertSame(sourceCodes, dt.getSourceCodes());
		assertSame(conditions, dt.getConditions());
		assertSame(actions, dt.getActions());
		assertSame(rules, dt.getRules());
	}
	
	@Test
	public void testPersistModel() throws Exception {
		Title title = new Title("English", "title");
		Text text = new Text("English", "text");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		List<Condition> conditions = new ArrayList<Condition>();
		List<Action> actions = new ArrayList<Action>();
		List<Rule> rules = new ArrayList<Rule>();
		
		LFDecisionTable dt = createUnderTest("version", "English", "user", "date",
				title, text, sourceCodes,
				conditions, actions, rules);

		String xml = persist(dt);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}

	
	@Test
	@Ignore
	public void testLFDT_AbfallGetEvents() throws Exception {
		LFDecisionTable lfet = new Persister().read(LFDecisionTable.class,
				new File("src/test/resources/ABFALL_getEvents.lfet"));
		Assert.assertNotNull(lfet);
		assertEquals("LF-ET 2.1.5 (170306b)", lfet.getVersion());
		assertEquals("2017.09.08 at 13:21:39 CEST", lfet.getSaveDate());
		assertEquals("German", lfet.getLanguage());
		assertEquals("constantin", lfet.getSaveUser());

		assertNotNull(lfet.getActions());
		assertEquals(13, lfet.getActions().size());

		assertNotNull(lfet.getRules());
		assertEquals(19, lfet.getRules().size());

		assertConditions(lfet.getConditions());
	}
	
	@Test
	public void testLFDT_StateMachine_1_Eng() throws Exception {
		LFDecisionTable lfet = new Persister().read(LFDecisionTable.class,
				new File("src/test/resources/StateMachine_1_Eng.lfet"));
		Assert.assertNotNull(lfet);
		assertEquals("LF-ET 2.1.5 (171120a)", lfet.getVersion());
		assertEquals("2018.02.08 at 00:02:08 CET", lfet.getSaveDate());
		assertEquals("English", lfet.getLanguage());
		assertEquals("constantin", lfet.getSaveUser());
		assertEquals("A Statemachine, only for testing purpose", lfet.getTitle().getValue());;
		assertEquals("English", lfet.getTitle().getLanguage());;
		assertEquals("a long documentation", lfet.getText().getValue());
		assertEquals("English", lfet.getText().getLanguage());

		assertNotNull(lfet.getConditions());
		
		lfet.getConditions().forEach(t -> {
			if (t.getOccurences() != null) {
				t.getOccurences().forEach(c -> System.out.println(c.getSymbol().getValue()));
			}
		});
		assertNotNull(lfet.getActions());
		lfet.getActions().forEach(t -> {
			if (t.getOccurences() != null) {
				t.getOccurences().forEach(a -> System.out.println(a.getSymbol().getValue()));
			}
		});
		
		assertNotNull(lfet.getActions());
		assertEquals(2, lfet.getActions().size());

		assertNotNull(lfet.getRules());
		assertEquals(2, lfet.getRules().size());
		
		assertNotNull(lfet.getConditions());
		assertEquals(1, lfet.getConditions().size());


	}

	private void assertConditions(List<Condition> conditions) {
		assertNotNull(conditions);
		assertEquals(12, conditions.size());

		Condition condition = conditions.get(0);
		assertNotNull(condition);
		assertNotNull(condition.getUId());
		assertEquals("1504126444840415", condition.getUId());

		assertValueBasedOnLanguage(condition.getTitle(), "step");

		List<ConditionOccurence> occurences = condition.getOccurences();
		assertNotNull(occurences);
		assertEquals(5, occurences.size());

		AbstractOccurence occ = occurences.get(0);
		assertNotNull(occ);
		assertEquals("1504126505502473", occ.getUId());
		assertValueBasedOnLanguage(occ.getSymbol(), "1");
		assertValueBasedOnLanguage(occ.getTitle(), "calendar step");

		occ = occurences.get(1);
		assertNotNull(occ);
		assertEquals("1504126505502475", occ.getUId());
		assertValueBasedOnLanguage(occ.getSymbol(), "2");
		assertValueBasedOnLanguage(occ.getTitle(), "event step");

		occ = occurences.get(2);
		assertNotNull(occ);
		assertEquals("15042103579651680", occ.getUId());
		assertValueBasedOnLanguage(occ.getSymbol(), "3");
		assertValueBasedOnLanguage(occ.getTitle(), "start time step");

		occ = occurences.get(3);
		assertNotNull(occ);
		assertEquals("15042131247763480", occ.getUId());
		assertValueBasedOnLanguage(occ.getSymbol(), "4");
		assertValueBasedOnLanguage(occ.getTitle(), "check start time step");

		occ = occurences.get(4);
		assertNotNull(occ);
		assertEquals("15042156349605690", occ.getUId());
		assertValueBasedOnLanguage(occ.getSymbol(), "5");
		assertValueBasedOnLanguage(occ.getTitle(), "search for duplicate step");

		/**
		 * <Condition uId="11446800829457960">
		 * <Title language="German" value="has more calendars"/>
		 * <SourceCode codeLanguage="Perl" sourceCodeType="LogArg" value="$calIndex &lt;
		 * scalar(@calendernamen)"/> </Condition>
		 */
		condition = conditions.get(1);
		assertNotNull(condition);
		assertNotNull(condition.getUId());
		assertEquals("11446800829457960", condition.getUId());

		assertValueBasedOnLanguage(condition.getTitle(), "has more calendars");

		/**
		 * <Condition uId="15041328493073228">
		 * <Title language="German" value="has more events"/>
		 * <SourceCode codeLanguage="Perl" sourceCodeType="LogArg" value="$eventIndex
		 * &lt; scalar(@termine)"/> </Condition>
		 */
		condition = conditions.get(2);
		assertNotNull(condition);
		assertNotNull(condition.getUId());
		assertEquals("15041328493073228", condition.getUId());

		assertValueBasedOnLanguage(condition.getTitle(), "has more events");

	}

	private void assertValueBasedOnLanguage(AbstractValueBasedOnLanguage actualTitle, String expectedValue) {
		assertNotNull(actualTitle);
		assertEquals("German", actualTitle.getLanguage());
		assertEquals(expectedValue, actualTitle.getValue());

	}
}
