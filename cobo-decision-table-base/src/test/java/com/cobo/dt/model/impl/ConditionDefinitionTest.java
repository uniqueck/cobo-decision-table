package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRulePartValue;

public class ConditionDefinitionTest {
	private ConditionDefinition createUnderTest(String id, String text) {
		return new ConditionDefinition(id, text);
	}

	@Test
	public void testConditionDefinition() throws Exception {
		ConditionDefinition condition = createUnderTest("C-01", "text");
		assertEquals("C-01", condition.getId());
		assertEquals("text", condition.getText());
		assertNotNull(condition.getDocumentation());
		assertEquals(3, condition.getValueSet().getValues().size());
		assertEquals(IRulePartValue.DONT_CARE_VALUE, condition.getValueSet().getValues().get(0).getValue());
		assertEquals(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES, condition.getValueSet().getValues().get(1).getValue());
		assertEquals(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO, condition.getValueSet().getValues().get(2).getValue());
	}
}
