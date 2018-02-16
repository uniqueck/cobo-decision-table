package com.cobo.dt.mapper.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRulePartValue;
import com.cobo.dt.model.impl.DecisionTable;
import com.cobo.dt.model.lfdt.LFDecisionTable;

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
		
	}

}
