package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cobo.dt.model.IAction;
import com.cobo.dt.model.ICondition;

public class RuleTest {
	private Rule createUnderTest(String id, List<ICondition> conditions, List<IAction> actions) {
		return new Rule(id, conditions, actions);
	}
	
	@Test
	public void testRule() throws Exception {
		List<ICondition> conditions = new ArrayList<ICondition>();
		List<IAction> actions = new ArrayList<IAction>();

		Rule rule = createUnderTest("R-01", conditions, actions);
		assertEquals("R-01", rule.getId());
		assertSame(conditions, rule.getConditions());
		assertSame(actions, rule.getActions());
	}
}
