package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IRulePartValue;

public class ActionDefinitionTest {
	private ActionDefinition createUnderTest(String id, String text) {
		return new ActionDefinition(id, text);
	}
	
	@Test
	public void testActionDefinition() throws Exception {
		ActionDefinition action = createUnderTest("A-01", "text");
		assertEquals("A-01", action.getId());
		assertEquals("text", action.getText());
		assertNotNull(action.getDocumentation());
		assertEquals(2, action.getValueSet().getValues().size());
		assertEquals(IRulePartValue.DONT_CARE_VALUE, action.getValueSet().getValues().get(0).getValue());
		assertEquals(IActionDefinition.DEFAULT_VALUE_ACTION_SET, action.getValueSet().getValues().get(1).getValue());
	}
}
