package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRulePartValue;

public class ConditionTest {
	private Condition createUnderTest(IConditionDefinition conditionDefinition) {
		return new Condition(conditionDefinition);
	}
	
	@Test
	public void testCondition() throws Exception {
		IConditionDefinition conditionDefinition = new ConditionDefinition("id", "text");
		Condition condition = createUnderTest(conditionDefinition);
		assertSame(conditionDefinition, condition.getDefinition());
		assertEquals(IRulePartValue.DONT_CARE_VALUE, condition.getValue().getValue());
	}
}
