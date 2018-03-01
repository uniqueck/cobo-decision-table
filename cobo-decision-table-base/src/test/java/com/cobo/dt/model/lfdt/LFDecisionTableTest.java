package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.simpleframework.xml.core.Persister;

public class LFDecisionTableTest extends AbstractLfdtTest<LFDecisionTable> {
	private static String NEW_LINE = "\n";

	private LFDecisionTable createUnderTest(String version, String language, String saveUser, String saveDate,
			Title title, Text text, List<SourceCode> sourceCodes, List<Condition> conditions, List<Action> actions,
			List<Rule> rules, List<Url> urls, String lastId, List<Snapshot> snapshots) {
		return new LFDecisionTable(version, language, saveUser, saveDate, title, text, sourceCodes, conditions, actions,
				lastId, rules, urls, snapshots);
	}

	private String createExpectedXml() {
		return "<LFET version='version' language='English' saveUser='user' saveDate='date'>" + NEW_LINE
			 + "   <Title value='title' language='English'/>" + NEW_LINE
			 + "   <Text value='text' language='English'/>" + NEW_LINE 
			 + "   <Conditions/>" + NEW_LINE
			 + "   <Actions/>" + NEW_LINE 
			 + "   <Rules lastId='10'/>" + NEW_LINE 
			 + "   <UrlsOut/>" + NEW_LINE
			 + "   <Snapshots/>" + NEW_LINE
			 + "</LFET>";
	}

	@Test
	public void testLFDecisionTable() throws Exception {
		Title title = new Title("English", "title");
		Text text = new Text("English", "text");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		List<Condition> conditions = new ArrayList<Condition>();
		List<Action> actions = new ArrayList<Action>();
		List<Rule> rules = new ArrayList<Rule>();
		List<Url> urls = new ArrayList<Url>();
		List<Snapshot> snapshots = new ArrayList<Snapshot>();
		
		LFDecisionTable dt = createUnderTest("version", "English", "user", "date", title, text, sourceCodes, conditions,
				actions, rules, urls, "10", snapshots);

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
		assertSame(urls, dt.getUrls());
		assertEquals("10", dt.getLastRuleId());
		assertSame(snapshots, dt.getSnapshots());
	}

	@Test
	public void testPersistModel() throws Exception {
		Title title = new Title("English", "title");
		Text text = new Text("English", "text");
		List<SourceCode> sourceCodes = new ArrayList<SourceCode>();
		List<Condition> conditions = new ArrayList<Condition>();
		List<Action> actions = new ArrayList<Action>();
		List<Rule> rules = new ArrayList<Rule>();
		List<Url> urls = new ArrayList<Url>();
		List<Snapshot> snapshots = new ArrayList<Snapshot>();
		
		LFDecisionTable dt = createUnderTest("version", "English", "user", "date", title, text, sourceCodes, conditions,
				actions, rules, urls, "10", snapshots);

		String xml = persist(dt);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}

	@Test
	@Ignore
	// FIXME BG 01.03.2018: fix Test: Use assertion methods of this test
	public void testLFDT_AbfallGetEvents() throws Exception {
		LFDecisionTable lfet = new Persister().read(LFDecisionTable.class, new File("src/test/resources/ABFALL_getEvents.lfet"));
		assertNotNull(lfet);
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
	// FIXME BG 01.03.2018: fix Test: Use assertion methods of this test
	public void testLFDT_StateMachine_1_Eng() throws Exception {
		LFDecisionTable lfet = new Persister().read(LFDecisionTable.class, new File("src/test/resources/StateMachine_1_Eng.lfet"));
		
		assertNotNull(lfet);
		
		assertEquals("LF-ET 2.1.5 (171120a)", lfet.getVersion());
		assertEquals("2018.02.19 at 00:16:45 CET", lfet.getSaveDate());
		assertEquals("English", lfet.getLanguage());
		assertEquals("constantin", lfet.getSaveUser());
		assertEquals("A Statemachine, only for testing purpose", lfet.getTitle().getValue());
		assertEquals("English", lfet.getTitle().getLanguage());
		assertEquals("a long documentation", lfet.getText().getValue());
		assertEquals("English", lfet.getText().getLanguage());

		assertNotNull(lfet.getConditions());
		assertEquals(2, lfet.getConditions().size());
		Condition condition = lfet.getConditions().get(0);
		assertNotNull(condition);
		assertEquals("State", condition.getTitle().getValue());
		assertEquals("documentation for C01", condition.getText().getValue());
		List<ConditionOccurrence> conditionOccurences = condition.getOccurrences();
		assertNotNull(conditionOccurences);
		assertEquals(2, conditionOccurences.size());
		assertEquals("INIT", conditionOccurences.get(0).getSymbol().getValue());
		assertEquals("Init State", conditionOccurences.get(0).getTitle().getValue());
		assertEquals("CHECK", conditionOccurences.get(1).getSymbol().getValue());
		assertEquals("Check State", conditionOccurences.get(1).getTitle().getValue());

		condition = lfet.getConditions().get(1);
		assertNotNull(condition);
		assertEquals("Useless Condition", condition.getTitle().getValue());
		assertEquals("documentation for C02", condition.getText().getValue());
		conditionOccurences = condition.getOccurrences();
		assertNull(conditionOccurences);

		assertNotNull(lfet.getActions());
		assertEquals(2, lfet.getActions().size());
		List<Action> actions = lfet.getActions();
		assertNotNull(actions.get(0));
		assertEquals("Useless Action", actions.get(0).getTitle().getValue());
		assertEquals("documentation for A01", actions.get(0).getText().getValue());
		assertNull(actions.get(0).getOccurrences());
		assertNotNull(actions.get(1));
		assertEquals("State", actions.get(1).getTitle().getValue());
		assertEquals("documentation for A02", actions.get(1).getText().getValue());
		List<ActionOccurrence> occurences = actions.get(1).getOccurrences();
		assertNotNull(occurences);
		assertEquals("EXIT", occurences.get(0).getSymbol().getValue());
		assertEquals("Exit State", occurences.get(0).getTitle().getValue());
		assertEquals("CHECK", occurences.get(1).getSymbol().getValue());
		assertEquals("Check State", occurences.get(1).getTitle().getValue());

		List<Rule> rules = lfet.getRules();
		assertNotNull(rules);
		assertEquals(4, rules.size());
		Rule rule = rules.get(0);
		assertNotNull(rule);
		assertNotNull(rule.getConditionLinks());
		assertEquals(1, rule.getConditionLinks().size());
		ConditionLink conditionLink = rule.getConditionLinks().get(0);
		assertNotNull(conditionLink);
		assertNotNull(conditionLink.getCondition());
		assertSame(lfet.getConditions().get(1), conditionLink.getCondition());
		assertTrue(conditionLink.getConditionState());
		
		assertNotNull(rule.getConditionOccurrenceLinks());
		assertEquals(1, rule.getConditionOccurrenceLinks().size());
		ConditionOccurrenceLink conditionOccurenceLink = rule.getConditionOccurrenceLinks().get(0);
		assertNotNull(conditionOccurenceLink);
		assertNotNull(conditionOccurenceLink.getConditionOccurrence());
		assertSame(lfet.getConditions().get(0).getOccurrences().get(0), conditionOccurenceLink.getConditionOccurrence());
		
//		assertNotNull(rule.getActionLinks());
		
		assertNotNull(rule.getActionOccurrenceLinks());
		assertEquals(1, rule.getActionOccurrenceLinks().size());
		ActionOccurrenceLink actionOccurrenceLink = rule.getActionOccurrenceLinks().get(0);
		assertNotNull(actionOccurrenceLink);
		assertNotNull(actionOccurrenceLink.getActionOccurrence());
		assertSame(lfet.getActions().get(1).getOccurrences().get(1), actionOccurrenceLink.getActionOccurrence());
		
		rule =  rules.get(2);
		assertNotNull(rule);
		assertNotNull(rule.getActionLinks());
		assertEquals(1, rule.getActionLinks().size());
		ActionLink actionLink = rule.getActionLinks().get(0);
		assertNotNull(actionLink);
		assertNotNull(actionLink.getAction());
		assertSame(lfet.getActions().get(0), actionLink.getAction());
		

	}

	private void assertConditions(List<Condition> conditions) {
		assertNotNull(conditions);
		assertEquals(12, conditions.size());

		Condition condition = conditions.get(0);
		assertNotNull(condition);
		assertNotNull(condition.getUId());
		assertEquals("1504126444840415", condition.getUId());

		assertValueBasedOnLanguage(condition.getTitle(), "step");

		List<ConditionOccurrence> occurences = condition.getOccurrences();
		assertNotNull(occurences);
		assertEquals(5, occurences.size());

		AbstractOccurrence occ = occurences.get(0);
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
	
	
	@Test
	public void testLFDT_DTWhichContainsAllPossibleFeatures() throws Exception {
		LFDecisionTable lfet = new Persister().read(LFDecisionTable.class, new File("src/test/resources/lfet_all_features_used.lfet"));
		
		assertNotNull(lfet);
		
		assertEquals("LF-ET 2.1.5 (160720a)", lfet.getVersion());
		assertEquals("2018.02.25 at 22:19:21 CET", lfet.getSaveDate());
		assertEquals("German", lfet.getLanguage());
		assertEquals("bg", lfet.getSaveUser());
		assertTitle(lfet.getTitle(), "Titel Entscheidungstabelle", "German");
		assertText(lfet.getText(), "Text für Entscheidungstabelle<br/>über mehrere<br/>Zeilen hinweg.", "German");
		assertEquals("18", lfet.getLastRuleId());
		
		List<SourceCode> sourceCodes = lfet.getSourceCodes();
		assertEquals(8, sourceCodes.size());
		assertSourceCode(sourceCodes.get(0), "Java", "Prolog", "Java Prolog für ET");
		assertSourceCode(sourceCodes.get(1), "Java", "Epilog", "Java Epilog für ET");
		assertSourceCode(sourceCodes.get(2), "Java", "Trace", "Java Trace für ET");
		assertSourceCode(sourceCodes.get(3), "Java", "Error", "Java Error für ET");
		assertSourceCode(sourceCodes.get(4), "Natural", "Prolog", "Natural Prolog für ET");
		assertSourceCode(sourceCodes.get(5), "Natural", "Epilog", "Natural Epilog für ET");
		assertSourceCode(sourceCodes.get(6), "Natural", "Trace", "Natural Trace für ET");
		assertSourceCode(sourceCodes.get(7), "Natural", "Error", "Natural Error für ET");

		List<Url> urls = lfet.getUrls();
		assertEquals(3, urls.size());
		assertUrl(urls.get(0), "urlET1", "file:///./", true);
		assertUrl(urls.get(1), "urlET2", "http://urlET2", true);
		assertUrl(urls.get(2), "urlET3", "http://urlET3", false);

		List<Snapshot> snapshots = lfet.getSnapshots();
		assertEquals(2, snapshots.size());
		assertSnapshot(snapshots.get(0), "2018.02.25 at 21:58:49 CET", "17:18 13:16", null, null, null, null);
		assertSnapshot(snapshots.get(1), "2018.02.25 at 22:02:34 CET", "17:18 13:16", "16", "13", "13", "11446800829457960");

		List<Condition> conditions = lfet.getConditions();
		assertEquals(3, conditions.size());
		
		Condition condition = conditions.get(0);
		assertEquals("11446800829457960", condition.getUId());
		assertTitle(condition.getTitle(), "Titel Bedingung 1 ohne Wertemenge", "German");
		assertText(condition.getText(), "Beschreibung Bedingung 1 ohne Wertemenge", "German");
		assertEquals(4, condition.getSourceCodes().size());
		assertSourceCode(condition.getSourceCodes().get(0), "Java", "Prolog", "Java Prolog für B01");
		assertSourceCode(condition.getSourceCodes().get(1), "Java", "LogArg", "Java LogArg für B01");
		assertSourceCode(condition.getSourceCodes().get(2), "Natural", "Prolog", "Natural Prolog für B01");
		assertSourceCode(condition.getSourceCodes().get(3), "Natural", "LogArg", "Natural LogArg für B01");
		assertEquals(2, condition.getUrls().size());
		assertUrl(condition.getUrls().get(0), "urlB01_1", "http://urlB01_1", false);
		assertUrl(condition.getUrls().get(1), "urlB01_2", "http://urlB01_2", true);
		assertNull(condition.getOccurrences());
		
		condition = conditions.get(1);
		assertEquals("15195899594471067", condition.getUId());
		assertTitle(condition.getTitle(), "Titel Bedingung 2 ohne Wertemenge", "German");
		assertText(condition.getText(), "Beschreibung Bedingung 2 ohne Wertemenge", "German");
		assertEquals(4, condition.getSourceCodes().size());
		assertSourceCode(condition.getSourceCodes().get(0), "Java", "Prolog", "Java Prolog für B02");
		assertSourceCode(condition.getSourceCodes().get(1), "Java", "LogArg", "Java LogArg für B02");
		assertSourceCode(condition.getSourceCodes().get(2), "Natural", "Prolog", "Natural Prolog für B02");
		assertSourceCode(condition.getSourceCodes().get(3), "Natural", "LogArg", "Natural LogArg für B02");
		assertNull(condition.getUrls());
		assertNull(condition.getOccurrences());

		condition = conditions.get(2);
		assertEquals("151958646949658", condition.getUId());
		assertTitle(condition.getTitle(), "Titel Bedingung 3 mit Wertemenge", "German");
		assertText(condition.getText(), "Beschreibung Bedingung 3 mit Wertemenge", "German");
		assertEquals(2, condition.getSourceCodes().size());
		assertSourceCode(condition.getSourceCodes().get(0), "Java", "Prolog", "Java Prolog für B03");
		assertSourceCode(condition.getSourceCodes().get(1), "Natural", "Prolog", "Natural Prolog für B03");
		assertNull(condition.getUrls());
		assertEquals(3, condition.getOccurrences().size());
		assertSame(condition, condition.getOccurrences().get(0).getCondition());
		assertEquals("151958653655596", condition.getOccurrences().get(0).getUId());
		assertSymbol(condition.getOccurrences().get(0).getSymbol(), "COND_SYMBOL_1", "German");
		assertTitle(condition.getOccurrences().get(0).getTitle(), "Titel ConditionOccurrence COND_SYMBOL_1", "German");
		assertText(condition.getOccurrences().get(0).getText(), "Beschreibung Bedingung 3 mit Wertemenge - COND_SYMBOL_1", "German");
		assertEquals(2, condition.getOccurrences().get(0).getSourceCodes().size());
		assertSourceCode(condition.getOccurrences().get(0).getSourceCodes().get(0), "Java", "LogArg", "Java LogArg für B03 - COND_SYMBOL_1");
		assertSourceCode(condition.getOccurrences().get(0).getSourceCodes().get(1), "Natural", "LogArg", "Natural LogArg für B03 - COND_SYMBOL_1");
		assertEquals(2, condition.getOccurrences().get(0).getUrls().size());
		assertUrl(condition.getOccurrences().get(0).getUrls().get(0), "urlB01_COND_SYMBOL_1_1", "http://urlB01_COND_SYMBOL_1_1", false);
		assertUrl(condition.getOccurrences().get(0).getUrls().get(1), "urlB01_COND_SYMBOL_1_2", "http://urlB01_COND_SYMBOL_1_2", true);
		assertSame(condition, condition.getOccurrences().get(1).getCondition());
		assertEquals("151958653655598", condition.getOccurrences().get(1).getUId());
		assertSymbol(condition.getOccurrences().get(1).getSymbol(), "COND_SYMBOL_2", "German");
		assertTitle(condition.getOccurrences().get(1).getTitle(), "Titel ConditionOccurrence COND_SYMBOL_2", "German");
		assertText(condition.getOccurrences().get(1).getText(), "Beschreibung Bedingung 3 mit Wertemenge - COND_SYMBOL_2", "German");
		assertEquals(2, condition.getOccurrences().get(1).getSourceCodes().size());
		assertSourceCode(condition.getOccurrences().get(1).getSourceCodes().get(0), "Java", "LogArg", "Java LogArg für B03 - COND_SYMBOL_2");
		assertSourceCode(condition.getOccurrences().get(1).getSourceCodes().get(1), "Natural", "LogArg", "Natural LogArg für B03 - COND_SYMBOL_2");
		assertNull(condition.getOccurrences().get(1).getUrls());
		assertSame(condition, condition.getOccurrences().get(2).getCondition());
		assertEquals("1519587362581737", condition.getOccurrences().get(2).getUId());
		assertSymbol(condition.getOccurrences().get(2).getSymbol(), "COND_SYMBOL_3", "German");
		assertTitle(condition.getOccurrences().get(2).getTitle(), "Titel ConditionOccurrence COND_SYMBOL_3", "German");
		assertText(condition.getOccurrences().get(2).getText(), "Beschreibung Bedingung 3 mit Wertemenge - COND_SYMBOL_3", "German");
		assertEquals(2, condition.getOccurrences().get(2).getSourceCodes().size());
		assertSourceCode(condition.getOccurrences().get(2).getSourceCodes().get(0), "Java", "LogArg", "Java LogArg für B03 - COND_SYMBOL_3");
		assertSourceCode(condition.getOccurrences().get(2).getSourceCodes().get(1), "Natural", "LogArg", "Natural LogArg für B03 - COND_SYMBOL_3");
		assertNull(condition.getOccurrences().get(2).getUrls());

		List<Action> actions = lfet.getActions();
		assertEquals(4, actions.size());
		
		Action action = actions.get(0);
		assertEquals("11446800828767967", action.getUId());
		assertTitle(action.getTitle(), "Titel für Aktion 1 ohne Wertemenge", "German");
		assertText(action.getText(), "Beschreibung Aktion 1 ohne Wertemenge", "German");
		assertEquals(2, action.getSourceCodes().size());
		assertSourceCode(action.getSourceCodes().get(0), "Java", "StmtSeq", "Java StmtSeq für A01");
		assertSourceCode(action.getSourceCodes().get(1), "Natural", "StmtSeq", "Natural StmtSeq für A01");
		assertEquals(2, action.getUrls().size());
		assertUrl(action.getUrls().get(0), "urlA01_1", "http://urlA01_1", false);
		assertUrl(action.getUrls().get(1), "urlA01_2", "http://urlA01_2", true);
		assertNull(action.getOccurrences());
		
		action = actions.get(1);
		assertEquals("11446800829627977", action.getUId());
		assertTitle(action.getTitle(), "Titel für Aktion 2 ohne Wertemenge", "German");
		assertText(action.getText(), "Beschreibung Aktion 2 ohne Wertemenge", "German");
		assertEquals(2, action.getSourceCodes().size());
		assertSourceCode(action.getSourceCodes().get(0), "Java", "StmtSeq", "Java StmtSeq für A02");
		assertSourceCode(action.getSourceCodes().get(1), "Natural", "StmtSeq", "Natural StmtSeq für A02");
		assertNull(action.getUrls());
		assertNull(action.getOccurrences());
		
		action = actions.get(2);
		assertEquals("1519586943356295", action.getUId());
		assertTitle(action.getTitle(), "Titel für Aktion 3 mit Wertemenge", "German");
		assertText(action.getText(), "Beschreibung Aktion 3 mit Wertemenge", "German");
		assertNull(action.getSourceCodes());
		assertNull(action.getUrls());
		assertEquals(2, action.getOccurrences().size());
		assertSame(action, action.getOccurrences().get(0).getAction());
		assertEquals("1519587010174351", action.getOccurrences().get(0).getUId());
		assertSymbol(action.getOccurrences().get(0).getSymbol(), "ACTION_SYMBOL_1", "German");
		assertTitle(action.getOccurrences().get(0).getTitle(), "Titel für ActionOccurrence ACTION_SYMBOL_1", "German");
		assertText(action.getOccurrences().get(0).getText(), "Beschreibung Aktion 3 mit Wertemenge - ACTION_SYMBOL_1", "German");
		assertEquals(2, action.getOccurrences().get(0).getSourceCodes().size());
		assertSourceCode(action.getOccurrences().get(0).getSourceCodes().get(0), "Java", "StmtSeq", "Java StmtSeq für A03 - ACTION_SYMBOL_1");
		assertSourceCode(action.getOccurrences().get(0).getSourceCodes().get(1), "Natural", "StmtSeq", "Natural StmtSeq für A03 - ACTION_SYMBOL_1");
		assertEquals(2, action.getOccurrences().get(0).getUrls().size());
		assertUrl(action.getOccurrences().get(0).getUrls().get(0), "urlA01_ACTION_SYMBOL_1_1", "http://urlA01_ACTION_SYMBOL_1_1", false);
		assertUrl(action.getOccurrences().get(0).getUrls().get(1), "urlA01_ACTION_SYMBOL_1_2", "http://urlA01_ACTION_SYMBOL_1_2", true);
		assertSame(action, action.getOccurrences().get(1).getAction());
		assertEquals("1519587178751524", action.getOccurrences().get(1).getUId());
		assertSymbol(action.getOccurrences().get(1).getSymbol(), "ACTION_SYMBOL_2", "German");
		assertTitle(action.getOccurrences().get(1).getTitle(), "Titel für ActionOccurrence ACTION_SYMBOL_2", "German");
		assertText(action.getOccurrences().get(1).getText(), "Beschreibung Aktion 3 mit Wertemenge - ACTION_SYMBOL_2", "German");
		assertEquals(2, action.getOccurrences().get(1).getSourceCodes().size());
		assertSourceCode(action.getOccurrences().get(1).getSourceCodes().get(0), "Java", "StmtSeq", "Java StmtSeq für A03 - ACTION_SYMBOL_2");
		assertSourceCode(action.getOccurrences().get(1).getSourceCodes().get(1), "Natural", "StmtSeq", "Natural StmtSeq für A03 - ACTION_SYMBOL_2");
		assertNull(action.getOccurrences().get(1).getUrls());

		action = actions.get(3);
		assertEquals("1519586986576297", action.getUId());
		assertTitle(action.getTitle(), "Titel für Aktion 4 mit Wertemenge", "German");
		assertText(action.getText(), "Beschreibung Aktion 4 mit Wertemenge", "German");
		assertNull(action.getSourceCodes());
		assertNull(action.getUrls());
		assertEquals(2, action.getOccurrences().size());
		assertSame(action, action.getOccurrences().get(0).getAction());
		assertEquals("1519587452100799", action.getOccurrences().get(0).getUId());
		assertSymbol(action.getOccurrences().get(0).getSymbol(), "ACTION_SYMBOL_3", "German");
		assertTitle(action.getOccurrences().get(0).getTitle(), "Titel für ActionOccurrence ACTION_SYMBOL_3", "German");
		assertText(action.getOccurrences().get(0).getText(), "Beschreibung Aktion 4 mit Wertemenge - ACTION_SYMBOL_3", "German");
		assertEquals(2, action.getOccurrences().get(0).getSourceCodes().size());
		assertSourceCode(action.getOccurrences().get(0).getSourceCodes().get(0), "Java", "StmtSeq", "Java StmtSeq für A04 - ACTION_SYMBOL_3");
		assertSourceCode(action.getOccurrences().get(0).getSourceCodes().get(1), "Natural", "StmtSeq", "Natural StmtSeq für A04 - ACTION_SYMBOL_3");
		assertNull(action.getOccurrences().get(0).getUrls());
		assertSame(action, action.getOccurrences().get(1).getAction());
		assertEquals("1519587464385883", action.getOccurrences().get(1).getUId());
		assertSymbol(action.getOccurrences().get(1).getSymbol(), "ACTION_SYMBOL_4", "German");
		assertTitle(action.getOccurrences().get(1).getTitle(), "Titel für ActionOccurrence ACTION_SYMBOL_4", "German");
		assertText(action.getOccurrences().get(1).getText(), "Beschreibung Aktion 4 mit Wertemenge - ACTION_SYMBOL_4", "German");
		assertEquals(2, action.getOccurrences().get(1).getSourceCodes().size());
		assertSourceCode(action.getOccurrences().get(1).getSourceCodes().get(0), "Java", "StmtSeq", "Java StmtSeq für A04 - ACTION_SYMBOL_4");
		assertSourceCode(action.getOccurrences().get(1).getSourceCodes().get(1), "Natural", "StmtSeq", "Natural StmtSeq für A04 - ACTION_SYMBOL_4");
		assertNull(action.getOccurrences().get(1).getUrls());

		List<Rule> rules = lfet.getRules();
		assertEquals(6, rules.size());
		
		Rule rule = rules.get(0);
		assertEquals("17", rule.getId());
		assertText(rule.getText(), "Beschreibung für Regel 01", "German");
		assertEquals(2, rule.getConditionLinks().size());
		assertConditionLink(rule.getConditionLinks().get(0), lfet.getConditions().get(0), true);
		assertConditionLink(rule.getConditionLinks().get(1), lfet.getConditions().get(1), true);
		assertEquals(1, rule.getActionLinks().size());
		assertActionLink(rule.getActionLinks().get(0), lfet.getActions().get(0));		
		assertNull(rule.getConditionOccurrenceLinks());
		assertNull(rule.getActionOccurrenceLinks());
		
		rule = rules.get(1);
		assertEquals("18", rule.getId());
		assertText(rule.getText(), "Beschreibung für Regel 02", "German");
		assertEquals(2, rule.getConditionLinks().size());
		assertConditionLink(rule.getConditionLinks().get(0), lfet.getConditions().get(0), true);
		assertConditionLink(rule.getConditionLinks().get(1), lfet.getConditions().get(1), false);
		assertEquals(1, rule.getActionLinks().size());
		assertActionLink(rule.getActionLinks().get(0), lfet.getActions().get(1));		
		assertNull(rule.getConditionOccurrenceLinks());
		assertNull(rule.getActionOccurrenceLinks());
		
		rule = rules.get(2);
		assertEquals("13", rule.getId());
		assertText(rule.getText(), "Beschreibung für Regel 03", "German");
		assertEquals(2, rule.getConditionLinks().size());
		assertConditionLink(rule.getConditionLinks().get(0), lfet.getConditions().get(0), false);
		assertConditionLink(rule.getConditionLinks().get(1), lfet.getConditions().get(1), true);
		assertEquals(2, rule.getActionLinks().size());
		assertActionLink(rule.getActionLinks().get(0), lfet.getActions().get(1));
		assertActionLink(rule.getActionLinks().get(1), lfet.getActions().get(0));
		assertNull(rule.getConditionOccurrenceLinks());
		assertNull(rule.getActionOccurrenceLinks());
		
		rule = rules.get(3);
		assertEquals("14", rule.getId());
		assertText(rule.getText(), "Beschreibung für Regel 04", "German");
		assertEquals(1, rule.getConditionOccurrenceLinks().size());
		assertConditionOccurrenceLink(rule.getConditionOccurrenceLinks().get(0), lfet.getConditions().get(2).getOccurrences().get(0));
		assertEquals(2, rule.getConditionLinks().size());
		assertConditionLink(rule.getConditionLinks().get(0), lfet.getConditions().get(1), false);
		assertConditionLink(rule.getConditionLinks().get(1), lfet.getConditions().get(0), false);
		assertEquals(2, rule.getActionLinks().size());
		assertActionLink(rule.getActionLinks().get(0), lfet.getActions().get(0));
		assertActionLink(rule.getActionLinks().get(1), lfet.getActions().get(1));
		assertNull(rule.getActionOccurrenceLinks());
		
		rule = rules.get(4);
		assertEquals("15", rule.getId());
		assertText(rule.getText(), "Beschreibung für Regel 05", "German");
		assertEquals(1, rule.getConditionOccurrenceLinks().size());
		assertConditionOccurrenceLink(rule.getConditionOccurrenceLinks().get(0), lfet.getConditions().get(2).getOccurrences().get(1));
		assertEquals(2, rule.getConditionLinks().size());
		assertConditionLink(rule.getConditionLinks().get(0), lfet.getConditions().get(1), false);
		assertConditionLink(rule.getConditionLinks().get(1), lfet.getConditions().get(0), false);
		assertEquals(2, rule.getActionOccurrenceLinks().size());
		assertActionOccurrenceLink(rule.getActionOccurrenceLinks().get(0), lfet.getActions().get(2).getOccurrences().get(0));
		assertActionOccurrenceLink(rule.getActionOccurrenceLinks().get(1), lfet.getActions().get(3).getOccurrences().get(0));
		assertNull(rule.getActionLinks());
		
		rule = rules.get(5);
		assertEquals("16", rule.getId());
		assertText(rule.getText(), "Beschreibung für Regel 06", "German");
		assertEquals(1, rule.getConditionOccurrenceLinks().size());
		assertConditionOccurrenceLink(rule.getConditionOccurrenceLinks().get(0), lfet.getConditions().get(2).getOccurrences().get(2));
		assertEquals(2, rule.getConditionLinks().size());
		assertConditionLink(rule.getConditionLinks().get(0), lfet.getConditions().get(1), false);
		assertConditionLink(rule.getConditionLinks().get(1), lfet.getConditions().get(0), false);
		assertEquals(2, rule.getActionOccurrenceLinks().size());
		assertActionOccurrenceLink(rule.getActionOccurrenceLinks().get(0), lfet.getActions().get(2).getOccurrences().get(1));
		assertActionOccurrenceLink(rule.getActionOccurrenceLinks().get(1), lfet.getActions().get(3).getOccurrences().get(1));
		assertEquals(2, rule.getActionLinks().size());
		assertActionLink(rule.getActionLinks().get(0), lfet.getActions().get(0));
		assertActionLink(rule.getActionLinks().get(1), lfet.getActions().get(1));
	}	
}
