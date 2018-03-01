package com.cobo.dt.mapper.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

import com.cobo.dt.model.IAction;
import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.ICondition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;
import com.cobo.dt.model.IRulePartValue;
import com.cobo.dt.model.impl.DecisionTable;
import com.cobo.dt.model.lfdt.ActionLink;
import com.cobo.dt.model.lfdt.ActionOccurrenceLink;
import com.cobo.dt.model.lfdt.ConditionLink;
import com.cobo.dt.model.lfdt.ConditionOccurrenceLink;
import com.cobo.dt.model.lfdt.LFDecisionTable;
import com.cobo.dt.model.lfdt.Rule;

public class LFDecisionTableMapperTest {

	@Test
	public void testMap() throws Exception {
		LFDecisionTable lfDecisionTable = new Persister().read(LFDecisionTable.class,
				new File("src/test/resources/StateMachine_1_Eng.lfet"));
		assertLFDecisionTable(lfDecisionTable);
		DecisionTable dt = new LFDecisionTableMapper().map(lfDecisionTable);
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
		assertDtRule_1(rules.get(0));
		assertDtRule_2(rules.get(1));
		assertDtRule_3(rules.get(2));
		assertDtRule_4(rules.get(3));
	}

	private void assertDtRule_1(IRule rule) {
		assertNotNull(rule);
		assertEquals(2, rule.getConditions().size());

		ICondition condition = rule.getConditions().get(0);
		IConditionDefinition conditionDefinition = (IConditionDefinition) condition.getDefinition();
		assertEquals("State", conditionDefinition.getText());
		IRulePartValue value = rule.getCondition(conditionDefinition).getValue();
		assertNotNull(value);
		assertEquals("INIT", value.getValue());

		condition = rule.getConditions().get(1);
		conditionDefinition = (IConditionDefinition) condition.getDefinition();
		assertEquals("Useless Condition", conditionDefinition.getText());
		value = rule.getCondition(conditionDefinition).getValue();
		assertNotNull(value);
		assertEquals("Y", value.getValue());

		assertEquals(2, rule.getActions().size());
		IAction action = rule.getActions().get(0);
		assertNotNull(action);
		IActionDefinition actionDefinition = (IActionDefinition) action.getDefinition();
		assertNotNull(actionDefinition);
		assertEquals("Useless Action", actionDefinition.getText());
		assertEquals("-", action.getValue().getValue());
		
		action = rule.getActions().get(1);
		assertNotNull(action);
		actionDefinition = (IActionDefinition) action.getDefinition();
		assertNotNull(actionDefinition);
		assertEquals("State", actionDefinition.getText());
		assertEquals("CHECK", action.getValue().getValue());
		
	}
	
	private void assertDtRule_2(IRule rule) {
		assertNotNull(rule);
		assertEquals(2, rule.getConditions().size());

		ICondition condition = rule.getConditions().get(0);
		IConditionDefinition conditionDefinition = (IConditionDefinition) condition.getDefinition();
		assertEquals("State", conditionDefinition.getText());
		IRulePartValue value = rule.getCondition(conditionDefinition).getValue();
		assertNotNull(value);
		assertEquals("INIT", value.getValue());

		condition = rule.getConditions().get(1);
		conditionDefinition = (IConditionDefinition) condition.getDefinition();
		assertEquals("Useless Condition", conditionDefinition.getText());
		value = rule.getCondition(conditionDefinition).getValue();
		assertNotNull(value);
		assertEquals("N", value.getValue());

		assertEquals(2, rule.getActions().size());
		IAction action = rule.getActions().get(0);
		assertNotNull(action);
		IActionDefinition actionDefinition = (IActionDefinition) action.getDefinition();
		assertNotNull(actionDefinition);
		assertEquals("Useless Action", actionDefinition.getText());
		assertEquals("-", action.getValue().getValue());
		
		action = rule.getActions().get(1);
		assertNotNull(action);
		actionDefinition = (IActionDefinition) action.getDefinition();
		assertNotNull(actionDefinition);
		assertEquals("State", actionDefinition.getText());
		assertEquals("CHECK", action.getValue().getValue());
		
	}
	
	
	private void assertDtRule_3(IRule rule) {
		assertNotNull(rule);
		assertEquals(2, rule.getConditions().size());

		ICondition condition = rule.getConditions().get(0);
		IConditionDefinition conditionDefinition = (IConditionDefinition) condition.getDefinition();
		assertEquals("State", conditionDefinition.getText());
		IRulePartValue value = rule.getCondition(conditionDefinition).getValue();
		assertNotNull(value);
		assertEquals("CHECK", value.getValue());

		condition = rule.getConditions().get(1);
		conditionDefinition = (IConditionDefinition) condition.getDefinition();
		assertEquals("Useless Condition", conditionDefinition.getText());
		value = rule.getCondition(conditionDefinition).getValue();
		assertNotNull(value);
		assertEquals("Y", value.getValue());

		assertEquals(2, rule.getActions().size());
		IAction action = rule.getActions().get(0);
		assertNotNull(action);
		IActionDefinition actionDefinition = (IActionDefinition) action.getDefinition();
		assertNotNull(actionDefinition);
		assertEquals("Useless Action", actionDefinition.getText());
		assertEquals("X", action.getValue().getValue());
		
		action = rule.getActions().get(1);
		assertNotNull(action);
		actionDefinition = (IActionDefinition) action.getDefinition();
		assertNotNull(actionDefinition);
		assertEquals("State", actionDefinition.getText());
		assertEquals("EXIT", action.getValue().getValue());
		
	}
	
	private void assertDtRule_4(IRule rule) {
		assertNotNull(rule);
		assertEquals(2, rule.getConditions().size());

		ICondition condition = rule.getConditions().get(0);
		IConditionDefinition conditionDefinition = (IConditionDefinition) condition.getDefinition();
		assertEquals("State", conditionDefinition.getText());
		IRulePartValue value = rule.getCondition(conditionDefinition).getValue();
		assertNotNull(value);
		assertEquals("CHECK", value.getValue());

		condition = rule.getConditions().get(1);
		conditionDefinition = (IConditionDefinition) condition.getDefinition();
		assertEquals("Useless Condition", conditionDefinition.getText());
		value = rule.getCondition(conditionDefinition).getValue();
		assertNotNull(value);
		assertEquals("N", value.getValue());

		assertEquals(2, rule.getActions().size());
		IAction action = rule.getActions().get(0);
		assertNotNull(action);
		IActionDefinition actionDefinition = (IActionDefinition) action.getDefinition();
		assertNotNull(actionDefinition);
		assertEquals("Useless Action", actionDefinition.getText());
		assertEquals("X", action.getValue().getValue());
		
		action = rule.getActions().get(1);
		assertNotNull(action);
		actionDefinition = (IActionDefinition) action.getDefinition();
		assertNotNull(actionDefinition);
		assertEquals("State", actionDefinition.getText());
		assertEquals("EXIT", action.getValue().getValue());
		
	}
	

	private void assertLFDecisionTable(LFDecisionTable lfDecisionTable) {
		assertNotNull(lfDecisionTable);
		List<Rule> rules = lfDecisionTable.getRules();
		assertNotNull(rules);

		assertEquals(4, rules.size());
		assertLFRule_1(rules.get(0));
		assertLFRule_2(rules.get(1));
		assertLFRule_3(rules.get(2));
		assertLFRule_4(rules.get(3));

	}

	private void assertLFRule_1(Rule rule) {
		assertNotNull(rule);
		List<ConditionOccurrenceLink> conditionOccurrenceLinks = rule.getConditionOccurrenceLinks();
		assertNotNull(conditionOccurrenceLinks);
		assertEquals(1, conditionOccurrenceLinks.size());
		ConditionOccurrenceLink conditionOccurrenceLink = conditionOccurrenceLinks.get(0);
		assertNotNull(conditionOccurrenceLink.getConditionOccurrence());
		assertEquals("INIT", conditionOccurrenceLink.getConditionOccurrence().getSymbol().getValue());

		List<ConditionLink> conditionLinks = rule.getConditionLinks();
		assertNotNull(conditionLinks);
		assertEquals(1, conditionLinks.size());
		ConditionLink conditionLink = conditionLinks.get(0);
		assertTrue(conditionLink.getConditionState());

		List<ActionOccurrenceLink> actionOccurrenceLinks = rule.getActionOccurrenceLinks();
		assertNotNull(actionOccurrenceLinks);
		assertEquals(1, actionOccurrenceLinks.size());
		ActionOccurrenceLink actionOccurrenceLink = actionOccurrenceLinks.get(0);
		assertNotNull(actionOccurrenceLink.getActionOccurrence());
		assertEquals("CHECK", actionOccurrenceLink.getActionOccurrence().getSymbol().getValue());

		List<ActionLink> actionLinks = rule.getActionLinks();
		assertNull(actionLinks);
	}

	private void assertLFRule_2(Rule rule) {
		assertNotNull(rule);
		List<ConditionOccurrenceLink> conditionOccurrenceLinks = rule.getConditionOccurrenceLinks();
		assertNotNull(conditionOccurrenceLinks);
		assertEquals(1, conditionOccurrenceLinks.size());
		ConditionOccurrenceLink conditionOccurrenceLink = conditionOccurrenceLinks.get(0);
		assertNotNull(conditionOccurrenceLink.getConditionOccurrence());
		assertEquals("INIT", conditionOccurrenceLink.getConditionOccurrence().getSymbol().getValue());

		List<ConditionLink> conditionLinks = rule.getConditionLinks();
		assertNotNull(conditionLinks);
		assertEquals(1, conditionLinks.size());
		ConditionLink conditionLink = conditionLinks.get(0);
		assertFalse(conditionLink.getConditionState());

		List<ActionOccurrenceLink> actionOccurrenceLinks = rule.getActionOccurrenceLinks();
		assertNotNull(actionOccurrenceLinks);
		assertEquals(1, actionOccurrenceLinks.size());
		ActionOccurrenceLink actionOccurrenceLink = actionOccurrenceLinks.get(0);
		assertNotNull(actionOccurrenceLink.getActionOccurrence());
		assertEquals("CHECK", actionOccurrenceLink.getActionOccurrence().getSymbol().getValue());

		List<ActionLink> actionLinks = rule.getActionLinks();
		assertNull(actionLinks);
	}

	private void assertLFRule_3(Rule rule) {
		assertNotNull(rule);
		List<ConditionOccurrenceLink> conditionOccurrenceLinks = rule.getConditionOccurrenceLinks();
		assertNotNull(conditionOccurrenceLinks);
		assertEquals(1, conditionOccurrenceLinks.size());
		ConditionOccurrenceLink conditionOccurrenceLink = conditionOccurrenceLinks.get(0);
		assertNotNull(conditionOccurrenceLink.getConditionOccurrence());
		assertEquals("CHECK", conditionOccurrenceLink.getConditionOccurrence().getSymbol().getValue());

		List<ConditionLink> conditionLinks = rule.getConditionLinks();
		assertNotNull(conditionLinks);
		assertEquals(1, conditionLinks.size());
		ConditionLink conditionLink = conditionLinks.get(0);
		assertTrue(conditionLink.getConditionState());

		List<ActionOccurrenceLink> actionOccurrenceLinks = rule.getActionOccurrenceLinks();
		assertNotNull(actionOccurrenceLinks);
		assertEquals(1, actionOccurrenceLinks.size());
		ActionOccurrenceLink actionOccurrenceLink = actionOccurrenceLinks.get(0);
		assertNotNull(actionOccurrenceLink.getActionOccurrence());
		assertEquals("EXIT", actionOccurrenceLink.getActionOccurrence().getSymbol().getValue());

		List<ActionLink> actionLinks = rule.getActionLinks();
		assertNotNull(actionLinks);
		assertEquals(1, actionLinks.size());
		ActionLink actionLink = actionLinks.get(0);
		assertNotNull(actionLink);
		assertNotNull(actionLink.getAction());

	}

	private void assertLFRule_4(Rule rule) {
		assertNotNull(rule);
		List<ConditionOccurrenceLink> conditionOccurrenceLinks = rule.getConditionOccurrenceLinks();
		assertNotNull(conditionOccurrenceLinks);
		assertEquals(1, conditionOccurrenceLinks.size());
		ConditionOccurrenceLink conditionOccurrenceLink = conditionOccurrenceLinks.get(0);
		assertNotNull(conditionOccurrenceLink.getConditionOccurrence());
		assertEquals("CHECK", conditionOccurrenceLink.getConditionOccurrence().getSymbol().getValue());

		List<ConditionLink> conditionLinks = rule.getConditionLinks();
		assertNotNull(conditionLinks);
		assertEquals(1, conditionLinks.size());
		ConditionLink conditionLink = conditionLinks.get(0);
		assertFalse(conditionLink.getConditionState());

		List<ActionOccurrenceLink> actionOccurrenceLinks = rule.getActionOccurrenceLinks();
		assertNotNull(actionOccurrenceLinks);
		assertEquals(1, actionOccurrenceLinks.size());
		ActionOccurrenceLink actionOccurrenceLink = actionOccurrenceLinks.get(0);
		assertNotNull(actionOccurrenceLink.getActionOccurrence());
		assertEquals("EXIT", actionOccurrenceLink.getActionOccurrence().getSymbol().getValue());

		List<ActionLink> actionLinks = rule.getActionLinks();
		assertNotNull(actionLinks);
		assertEquals(1, actionLinks.size());
		ActionLink actionLink = actionLinks.get(0);
		assertNotNull(actionLink);
		assertNotNull(actionLink.getAction());
	}

}
