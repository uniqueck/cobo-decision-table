package com.cobo.dt.mapper.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.impl.DecisionTable;
import com.cobo.dt.model.lfet.LFDecisionTable;

public class LFDecisionTableMapperTest {

	@Test
	public void testMap() throws Exception {
		DecisionTable dt = new LFDecisionTableMapper()
				.map(new Persister().read(LFDecisionTable.class, new File("src/test/resources/StateMachine_1_Eng.lfet")));
		assertNotNull(dt);
		assertNotNull(dt.getDocumentation());
		assertEquals("A Statemachine, only for testing purpose", dt.getTitle());
		assertEquals("a long documentation", dt.getDocumentation().getDescription());
	
		assertEquals(1, dt.getDefinition().getConditionDefinitions().size());
		IConditionDefinition conditionDefinition = dt.getDefinition().getConditionDefinitions().get(0);
		assertEquals("State", conditionDefinition.getText());
		assertEquals("documentation for C01", conditionDefinition.getDocumentation().getDescription());
		// FIXME BG/CK @20180208 - default values should not be in value list
		assertEquals(2, conditionDefinition.getValueSet().getValues().size());
		
		
		
		assertEquals(2, dt.getDefinition().getActionDefinitions().size());
		IActionDefinition actionDefinition = dt.getDefinition().getActionDefinitions().get(0);
		assertEquals("Useless Action", actionDefinition.getText());
		assertEquals("documentation for A01", actionDefinition.getDocumentation().getDescription());
		
		actionDefinition = dt.getDefinition().getActionDefinitions().get(1);
		assertEquals("State", actionDefinition.getText());
		assertEquals("documentation for A02", actionDefinition.getDocumentation().getDescription());
		assertEquals(2, actionDefinition.getValueSet().getValues().size());
	}

}
