package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExampleDecisionTableModelTest {
	@Test
	public void testCreateExampleDecisionTable() {
		TestModelCreator testModelCreator = new TestModelCreator();
		
		DecisionTable decisionTable = testModelCreator.createDecisionTable_With3ConditionsAnd4Actions();
		
		assertEquals(8, decisionTable.getRules().size());
		assertEquals(3, decisionTable.getDefinition().getConditionDefinitions().size());
		assertEquals(4, decisionTable.getDefinition().getActionDefinitions().size());
	}
}
