package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;

public class DecisionTableDefinitionTest {
	private DecisionTableDefinition createUnderTest() {
		return new DecisionTableDefinition();
	}
	
	@Test
	public void testDecisionTableDefinition() throws Exception {
		DecisionTableDefinition definition = createUnderTest();
		assertTrue(definition.getConditionDefinitions().isEmpty());
		assertTrue(definition.getActionDefinitions().isEmpty());
	}

	@Test
	public void testCreateNewRule() throws Exception {
		DecisionTableDefinition definition = createUnderTest();
		IRule rule = definition.createNewRule();
		assertTrue(rule.getConditions().isEmpty());
		assertTrue(rule.getActions().isEmpty());
		assertFalse(rule.getId().isEmpty());
	}

	@Test
	public void testCreateNewActionDefinition() throws Exception {
		DecisionTableDefinition definition = createUnderTest();
		IActionDefinition actionDefinition = definition.createNewActionDefinition("MyAction");
		assertNotNull(actionDefinition);
		assertFalse(actionDefinition.getId().isEmpty());
		assertEquals("MyAction", actionDefinition.getText());
	}
	
	@Test
	public void testCreateNewConditionDefinition() throws Exception {
		DecisionTableDefinition definition = createUnderTest();
		IConditionDefinition conditionDefinition = definition.createNewConditionDefinition("MyCondition");
		assertNotNull(conditionDefinition);
		assertFalse(conditionDefinition.getId().isEmpty());
		assertEquals("MyCondition", conditionDefinition.getText());
	}

	@Test
	public void testAddNewActionDefinition() throws Exception {
		DecisionTableDefinition definition = createUnderTest();
		assertTrue(definition.getActionDefinitions().isEmpty());

		IActionDefinition actionDefinition1 = definition.addNewActionDefinition("MyAction");
		assertEquals(1, definition.getActionDefinitions().size());
		assertSame(actionDefinition1, definition.getActionDefinitions().get(0));

		IActionDefinition actionDefinition2 = definition.addNewActionDefinition("MyAction");
		assertEquals(2, definition.getActionDefinitions().size());
		assertSame(actionDefinition1, definition.getActionDefinitions().get(0));
		assertSame(actionDefinition2, definition.getActionDefinitions().get(1));
	}
	
	@Test
	public void testAddNewConditionDefinition() throws Exception {
		DecisionTableDefinition definition = createUnderTest();
		assertTrue(definition.getConditionDefinitions().isEmpty());

		IConditionDefinition conditionDefinition1 = definition.addNewConditionDefinition("MyCondition");
		assertEquals(1, definition.getConditionDefinitions().size());
		assertSame(conditionDefinition1, definition.getConditionDefinitions().get(0));

		IConditionDefinition conditionDefinition2 = definition.addNewConditionDefinition("MyCondition");
		assertEquals(2, definition.getConditionDefinitions().size());
		assertSame(conditionDefinition1, definition.getConditionDefinitions().get(0));
		assertSame(conditionDefinition2, definition.getConditionDefinitions().get(1));
	}

	@Test
	public void testToString() throws Exception {
		String NEW_LINE = System.lineSeparator();
		
		DecisionTableDefinition definition = createUnderTest();
		assertEquals("Conditions:" + NEW_LINE + "[]" + NEW_LINE + "Actions:" + NEW_LINE + "[]", definition.toString());
		
		IConditionDefinition conditionDefinition = definition.addNewConditionDefinition("Condition 1");
		((ConditionDefinition)conditionDefinition).setId("id1");
		IActionDefinition actionDefinition = definition.addNewActionDefinition("Action 1");
		((ActionDefinition)actionDefinition).setId("id2");
		String condText = "ID=id1; Text=Condition 1; Possible Values=[" + IConditionDefinition.DONT_CARE_VALUE + ", " + IConditionDefinition.DEFAULT_VALUE_CONDITION_YES + ", " + IConditionDefinition.DEFAULT_VALUE_CONDITION_NO + "]" + NEW_LINE + "Documentation=";
		String actionText = "ID=id2; Text=Action 1; Possible Values=[" + IActionDefinition.DONT_CARE_VALUE + ", " + IActionDefinition.DEFAULT_VALUE_ACTION_SET + "]" + NEW_LINE + "Documentation=";
		assertEquals("Conditions:" + NEW_LINE + "[" + condText + "]" + NEW_LINE + "Actions:" + NEW_LINE + "[" + actionText + "]", definition.toString());
	}
}
