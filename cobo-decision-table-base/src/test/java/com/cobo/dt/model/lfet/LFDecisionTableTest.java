package com.cobo.dt.model.lfet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.simpleframework.xml.core.Persister;

public class LFDecisionTableTest {

	@Test
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
		assertEquals("A Statemachine, only for testing purpose", lfet.getTitle());;
		assertEquals("a long documentation", lfet.getText());

		lfet.getConditions().forEach(t -> {
			if (t.getOccurences() != null) {
				t.getOccurences().forEach(c -> System.out.println(c.getSymbol().getValue()));
			}
		});
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
		assertNotNull(condition.getuId());
		assertEquals("1504126444840415", condition.getuId());

		assertValueBasedOnLanguage(condition.getTitle(), "step");

		List<Occurences> occurences = condition.getOccurences();
		assertNotNull(occurences);
		assertEquals(5, occurences.size());

		Occurences occ = occurences.get(0);
		assertNotNull(occ);
		assertEquals("1504126505502473", occ.getuId());
		assertValueBasedOnLanguage(occ.getSymbol(), "1");
		assertValueBasedOnLanguage(occ.getTitle(), "calendar step");

		occ = occurences.get(1);
		assertNotNull(occ);
		assertEquals("1504126505502475", occ.getuId());
		assertValueBasedOnLanguage(occ.getSymbol(), "2");
		assertValueBasedOnLanguage(occ.getTitle(), "event step");

		occ = occurences.get(2);
		assertNotNull(occ);
		assertEquals("15042103579651680", occ.getuId());
		assertValueBasedOnLanguage(occ.getSymbol(), "3");
		assertValueBasedOnLanguage(occ.getTitle(), "start time step");

		occ = occurences.get(3);
		assertNotNull(occ);
		assertEquals("15042131247763480", occ.getuId());
		assertValueBasedOnLanguage(occ.getSymbol(), "4");
		assertValueBasedOnLanguage(occ.getTitle(), "check start time step");

		occ = occurences.get(4);
		assertNotNull(occ);
		assertEquals("15042156349605690", occ.getuId());
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
		assertNotNull(condition.getuId());
		assertEquals("11446800829457960", condition.getuId());

		assertValueBasedOnLanguage(condition.getTitle(), "has more calendars");

		/**
		 * <Condition uId="15041328493073228">
		 * <Title language="German" value="has more events"/>
		 * <SourceCode codeLanguage="Perl" sourceCodeType="LogArg" value="$eventIndex
		 * &lt; scalar(@termine)"/> </Condition>
		 */
		condition = conditions.get(2);
		assertNotNull(condition);
		assertNotNull(condition.getuId());
		assertEquals("15041328493073228", condition.getuId());

		assertValueBasedOnLanguage(condition.getTitle(), "has more events");

	}

	private void assertValueBasedOnLanguage(ValueBasedOnLanguage actualTitle, String expectedValue) {
		assertNotNull(actualTitle);
		assertEquals("German", actualTitle.getLanguage());
		assertEquals(expectedValue, actualTitle.getValue());

	}

}
