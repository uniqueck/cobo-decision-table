package com.cobo.dt.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.cobo.dt.model.impl.ActionDefinition;
import com.cobo.dt.model.impl.RulePartValue;

public class AbstractRulePartTest {
	private AbstractRulePart createUnderTest(IRulePartDefinition definition) {
		return new AbstractRulePart(definition) {};
	}
	
	@Test
	public void testAbstractRulePart() throws Exception {
		ActionDefinition definition = new ActionDefinition("id", "text");
		AbstractRulePart rulePart = createUnderTest(definition);
		assertSame(definition, rulePart.getDefinition());
		assertEquals(IRulePartValue.DONT_CARE_VALUE, rulePart.getValue().getValue());
	}

	@Test
	public void testSetValue() throws Exception {
		ActionDefinition definition = new ActionDefinition("id", "text");
		AbstractRulePart rulePart = createUnderTest(definition);

		assertEquals(IRulePartValue.DONT_CARE_VALUE, rulePart.getValue().getValue());
		
		rulePart.setValue(new RulePartValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET));
		assertEquals(IActionDefinition.DEFAULT_VALUE_ACTION_SET, rulePart.getValue().getValue());
		
		try {
			rulePart.setValue(new RulePartValue("ValueNotInValueSet"));
			fail("Exception expected!");
		} catch (RuntimeException ex) {
			assertEquals("Value 'ValueNotInValueSet' not contained in value set!", ex.getMessage());
			assertEquals(IActionDefinition.DEFAULT_VALUE_ACTION_SET, rulePart.getValue().getValue());
		}
	}

	@Test
	public void testSetValueString() throws Exception {
		ActionDefinition definition = new ActionDefinition("id", "text");
		AbstractRulePart rulePart = createUnderTest(definition);

		assertEquals(IRulePartValue.DONT_CARE_VALUE, rulePart.getValue().getValue());
		
		rulePart.setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);
		assertEquals(IActionDefinition.DEFAULT_VALUE_ACTION_SET, rulePart.getValue().getValue());
		
		try {
			rulePart.setValue("ValueNotInValueSet");
			fail("Exception expected!");
		} catch (RuntimeException ex) {
			assertEquals("Value 'ValueNotInValueSet' not contained in value set!", ex.getMessage());
			assertEquals(IActionDefinition.DEFAULT_VALUE_ACTION_SET, rulePart.getValue().getValue());
		}	
	}
}
