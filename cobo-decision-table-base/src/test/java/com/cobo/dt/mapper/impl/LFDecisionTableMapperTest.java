package com.cobo.dt.mapper.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.ICondition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;
import com.cobo.dt.model.IRulePartValue;
import com.cobo.dt.model.impl.DecisionTable;
import com.cobo.dt.model.lfdt.LFDecisionTable;

public class LFDecisionTableMapperTest {

	@Test
	public void testMap() throws Exception {
		DecisionTable dt = new LFDecisionTableMapper().map(
				new Persister().read(LFDecisionTable.class, new File("src/test/resources/StateMachine_1_Eng.lfet")));
		assertNotNull(dt);
		assertNotNull(dt.getDocumentation());
		assertEquals("A Statemachine, only for testing purpose", dt.getTitle());
		assertEquals("a long documentation", dt.getDocumentation().getDescription());

		assertEquals(2, dt.getDefinition().getConditionDefinitions().size());
		IConditionDefinition conditionDefinition = dt.getDefinition().getConditionDefinitions().get(0);
		assertEquals("State", conditionDefinition.getText());
		assertEquals("documentation for C01", conditionDefinition.getDocumentation().getDescription());
		assertEquals(2, conditionDefinition.getValueSet().getValues().size());
		IRulePartValue valuePart = conditionDefinition.getValueSet().getValues().get(0);
		assertNotNull(valuePart);
		assertEquals("INIT", valuePart.getValue());
		assertEquals("Init State", valuePart.getDocumentation().getDescription());

		valuePart = conditionDefinition.getValueSet().getValues().get(1);
		assertNotNull(valuePart);
		assertEquals("CHECK", valuePart.getValue());
		assertEquals("Check State", valuePart.getDocumentation().getDescription());

		assertEquals(2, dt.getDefinition().getActionDefinitions().size());
		IActionDefinition actionDefinition = dt.getDefinition().getActionDefinitions().get(0);
		assertEquals("Useless Action", actionDefinition.getText());
		assertEquals("documentation for A01", actionDefinition.getDocumentation().getDescription());

		actionDefinition = dt.getDefinition().getActionDefinitions().get(1);
		assertEquals("State", actionDefinition.getText());
		assertEquals("documentation for A02", actionDefinition.getDocumentation().getDescription());
		assertEquals(2, actionDefinition.getValueSet().getValues().size());

		valuePart = actionDefinition.getValueSet().getValues().get(0);
		assertNotNull(valuePart);
		assertEquals("EXIT", valuePart.getValue());
		assertEquals("Exit State", valuePart.getDocumentation().getDescription());
		valuePart = actionDefinition.getValueSet().getValues().get(1);
		assertNotNull(valuePart);
		assertEquals("CHECK", valuePart.getValue());
		assertEquals("Check State", valuePart.getDocumentation().getDescription());

		List<IRule> rules = dt.getRules();
		assertNotNull(rules);
		assertEquals(4, rules.size());
		IRule rule = rules.get(0);
		assertNotNull(rule);
		assertEquals(2, rule.getConditions().size());
		ICondition condition = rule.getConditions().get(0);
		IConditionDefinition definition = (IConditionDefinition) condition.getDefinition();
		IRulePartValue value = rule.getCondition(definition).getValue();
		assertNotNull(value);
		assertEquals("INIT", value.getValue());

		condition = rule.getConditions().get(1);
		definition = (IConditionDefinition) condition.getDefinition();
		value = rule.getCondition(definition).getValue();
		assertNotNull(value);
		assertEquals("CHECK", value.getValue());

		assertEquals(2, rule.getActions().size());
	}

}
