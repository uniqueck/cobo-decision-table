package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.simpleframework.xml.core.Persister;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;

public class DecisionTableTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder(new File("."));

	private DecisionTable createUnderTest() {
		return new DecisionTable();
	}

	private DecisionTable createUnderTest_With3ConditionsAnd4Actions() {
		TestModelCreator testModelCreator = new TestModelCreator();
		return testModelCreator.createDecisionTable_With3ConditionsAnd4Actions();
	}

	@Test
	public void testDecisionTable() throws Exception {
		DecisionTable decisionTable = createUnderTest();

		assertNotNull(decisionTable.getDefinition());
		assertTrue(decisionTable.getDefinition().getConditionDefinitions().isEmpty());
		assertTrue(decisionTable.getDefinition().getActionDefinitions().isEmpty());
		assertTrue(decisionTable.getRules().isEmpty());
		assertNotNull(decisionTable.getDocumentation());
		assertEquals("", decisionTable.getDocumentation().getDescription());
	}

	@Test
	public void testAddNewRule() throws Exception {
		DecisionTable decisionTable = createUnderTest();
		assertTrue(decisionTable.getRules().isEmpty());

		IRule rule1 = decisionTable.addNewRule();
		assertEquals(1, decisionTable.getRules().size());
		assertSame(rule1, decisionTable.getRules().get(0));

		IRule rule2 = decisionTable.addNewRule();
		assertEquals(2, decisionTable.getRules().size());
		assertSame(rule1, decisionTable.getRules().get(0));
		assertSame(rule2, decisionTable.getRules().get(1));

		IRule rule3 = decisionTable.addNewRule();
		assertEquals(3, decisionTable.getRules().size());
		assertSame(rule1, decisionTable.getRules().get(0));
		assertSame(rule2, decisionTable.getRules().get(1));
		assertSame(rule3, decisionTable.getRules().get(2));
	}

	@Test
	public void testAddNewRuleInt_insertAtBeginning() throws Exception {
		DecisionTable decisionTable = createUnderTest();
		assertTrue(decisionTable.getRules().isEmpty());

		IRule rule1 = decisionTable.addNewRule(0);
		assertEquals(1, decisionTable.getRules().size());
		assertSame(rule1, decisionTable.getRules().get(0));

		IRule rule2 = decisionTable.addNewRule(0);
		assertEquals(2, decisionTable.getRules().size());
		assertSame(rule2, decisionTable.getRules().get(0));
		assertSame(rule1, decisionTable.getRules().get(1));

		IRule rule3 = decisionTable.addNewRule(0);
		assertEquals(3, decisionTable.getRules().size());
		assertSame(rule3, decisionTable.getRules().get(0));
		assertSame(rule2, decisionTable.getRules().get(1));
		assertSame(rule1, decisionTable.getRules().get(2));
	}

	@Test
	public void testAddNewRuleInt_insertAtEnd() throws Exception {
		DecisionTable decisionTable = createUnderTest();
		assertTrue(decisionTable.getRules().isEmpty());

		IRule rule1 = decisionTable.addNewRule(decisionTable.getRules().size());
		assertEquals(1, decisionTable.getRules().size());
		assertSame(rule1, decisionTable.getRules().get(0));

		IRule rule2 = decisionTable.addNewRule(decisionTable.getRules().size());
		assertEquals(2, decisionTable.getRules().size());
		assertSame(rule1, decisionTable.getRules().get(0));
		assertSame(rule2, decisionTable.getRules().get(1));

		IRule rule3 = decisionTable.addNewRule(decisionTable.getRules().size());
		assertEquals(3, decisionTable.getRules().size());
		assertSame(rule1, decisionTable.getRules().get(0));
		assertSame(rule2, decisionTable.getRules().get(1));
		assertSame(rule3, decisionTable.getRules().get(2));
	}

	@Test
	public void testInitDefault() throws Exception {
		DecisionTable decisionTable = createUnderTest();
		assertTrue(decisionTable.getDefinition().getActionDefinitions().isEmpty());
		assertTrue(decisionTable.getDefinition().getConditionDefinitions().isEmpty());

		decisionTable.initDefault();

		assertEquals(1, decisionTable.getDefinition().getConditionDefinitions().size());
		assertEquals("New Condition", decisionTable.getDefinition().getConditionDefinitions().get(0).getText());
		assertEquals(2, decisionTable.getDefinition().getActionDefinitions().size());
		assertEquals("New Action 1", decisionTable.getDefinition().getActionDefinitions().get(0).getText());
		assertEquals("New Action 2", decisionTable.getDefinition().getActionDefinitions().get(1).getText());

		assertEquals(2, decisionTable.getRules().size());
		IRule rule1 = decisionTable.getRules().get(0);
		assertEquals(1, rule1.getConditions().size());
		assertEquals(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES,
				rule1.getConditions().get(0).getValue().getValue());
		assertEquals(2, rule1.getActions().size());
		assertEquals(IActionDefinition.DEFAULT_VALUE_ACTION_SET, rule1.getActions().get(0).getValue().getValue());
		assertEquals(IActionDefinition.DONT_CARE_VALUE, rule1.getActions().get(1).getValue().getValue());

		IRule rule2 = decisionTable.getRules().get(1);
		assertEquals(1, rule2.getConditions().size());
		assertEquals(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO,
				rule2.getConditions().get(0).getValue().getValue());
		assertEquals(2, rule2.getActions().size());
		assertEquals(IActionDefinition.DONT_CARE_VALUE, rule2.getActions().get(0).getValue().getValue());
		assertEquals(IActionDefinition.DEFAULT_VALUE_ACTION_SET, rule2.getActions().get(1).getValue().getValue());
	}

	@Test
	public void testToString() throws Exception {
		assertEquals(expectedDecisionTableAsString(), createUnderTest_With3ConditionsAnd4Actions().toString());
	}

	@Test
	public void testReplicate() throws Exception {
		DecisionTable decisionTable = createUnderTest();
		assertEquals("", decisionTable.replicate("text", 0));
		assertEquals("text", decisionTable.replicate("text", 1));
		assertEquals("texttext", decisionTable.replicate("text", 2));
		assertEquals("BlahBlahBlah", decisionTable.replicate("Blah", 3));
		assertEquals("     ", decisionTable.replicate(" ", 5));
	}

	@Test
	public void testPaddingRight() throws Exception {
		DecisionTable decisionTable = createUnderTest();
		assertEquals("", decisionTable.paddingRight("", 0));
		assertEquals(" ", decisionTable.paddingRight("", 1));
		assertEquals("     ", decisionTable.paddingRight("", 5));

		assertEquals("", decisionTable.paddingRight(null, 0));
		assertEquals("      ", decisionTable.paddingRight(null, 6));
		assertEquals("       ", decisionTable.paddingRight(null, 7));
		assertEquals("          ", decisionTable.paddingRight(null, 10));

		assertEquals("", decisionTable.paddingRight("text", 0));
		assertEquals("t", decisionTable.paddingRight("text", 1));
		assertEquals("te", decisionTable.paddingRight("text", 2));
		assertEquals("tex", decisionTable.paddingRight("text", 3));
		assertEquals("text", decisionTable.paddingRight("text", 4));
		assertEquals("text ", decisionTable.paddingRight("text", 5));
		assertEquals("text      ", decisionTable.paddingRight("text", 10));
	}

	private String expectedDecisionTableAsString() {
		return "Lieferfähig?                  Y  Y  Y  Y  N  N  N  N  " + System.lineSeparator()
				+ "Angaben vollständig?          Y  Y  N  N  Y  Y  N  N  " + System.lineSeparator()
				+ "Bonität in Ordnung?           Y  N  Y  N  Y  N  Y  N  " + System.lineSeparator()
				+ System.lineSeparator() + "Lieferung mit Rechnung        X  -  -  -  -  -  -  -  "
				+ System.lineSeparator() + "Lieferung als Nachnahme       -  X  -  -  -  -  -  -  "
				+ System.lineSeparator() + "Angaben vervollständigen      -  -  X  X  -  -  -  -  "
				+ System.lineSeparator() + "Mitteilen: nicht lieferbar    -  -  -  -  X  X  X  X  "
				+ System.lineSeparator();
	}

	@Test
	public void testConvertAsXmlAndReadFromXml() throws Exception {
		DecisionTable dt = createUnderTest_With3ConditionsAnd4Actions();
		StringWriter out = new StringWriter();
		Persister persister = new Persister();
		persister.write(dt, out);
		String xml = out.toString();
		assertNotNull(xml);
		assertFalse(xml.isEmpty());
		assertEquals(deleteUUIDsInXmlForCompare(readFile2String()), deleteUUIDsInXmlForCompare(xml));
		DecisionTable readedDecisionTable = persister.read(DecisionTable.class, xml);
		assertEquals(dt.toString(), readedDecisionTable.toString());
	}

	@Test
	public void testReadFromXML() throws Exception {
		DecisionTable dt = createUnderTest_With3ConditionsAnd4Actions();
		assertEquals(dt.toString(), new Persister()
				.read(DecisionTable.class, new File("src/test/resources/DecisionTableToXMLTest.xml")).toString());
	}

	protected String deleteUUIDsInXmlForCompare(String xml) {
		return xml.replaceAll("(?<=(id|refId)\\=\")[0-9a-z\\-]*(?=\")", "");
	}

	public String readFile2String() throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		try (InputStream resourceAsStream = getClass().getClassLoader()
				.getResourceAsStream("DecisionTableToXMLTest.xml")) {

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (stringBuffer.length() > 0) {
					stringBuffer.append(System.lineSeparator());
				}
				stringBuffer.append(line);
			}
			resourceAsStream.close();

		}
		return stringBuffer.toString();
	}

}
