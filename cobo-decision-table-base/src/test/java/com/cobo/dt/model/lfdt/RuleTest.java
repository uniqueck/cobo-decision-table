package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RuleTest {

	@Test
	public void testRule() throws Exception {
		Rule rule = new Rule();
		assertNotNull(rule.getActionLinks());
		assertNotNull(rule.getActionnOccurrenceLinks());
		assertNotNull(rule.getConditionLinks());
		assertNotNull(rule.getConditionOccurrenceLinks());
	}

}
