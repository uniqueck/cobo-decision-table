package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IRulePartValue;

public class ActionTest {
	private Action createUnderTest(IActionDefinition actionDefinition) {
		return new Action(actionDefinition);
	}
	
	@Test
	public void testAction() throws Exception {
		IActionDefinition actionDefinition = new ActionDefinition("id", "text");
		Action action = createUnderTest(actionDefinition);
		assertSame(actionDefinition, action.getDefinition());
		assertEquals(IRulePartValue.DONT_CARE_VALUE, action.getValue().getValue());
	}
}
