package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RuleTest {

	@Test
	public void testRule() throws Exception {
		String id = "10";
		Text text = new Text("English", "docuText");
		List<ConditionLink> conditionLinks = new ArrayList<ConditionLink>();
		List<ActionLink> actionLinks = new ArrayList<ActionLink>();
		List<ConditionOccurrenceLink> conditionOccurrenceLinks = new ArrayList<ConditionOccurrenceLink>();
		List<ActionOccurrenceLink> actionOccurrenceLinks = new ArrayList<ActionOccurrenceLink>();
		
		Rule rule = new Rule(id, text, conditionLinks, actionLinks, conditionOccurrenceLinks, actionOccurrenceLinks);

		assertEquals(id, rule.getId());
		assertSame(text, rule.getText());
		assertSame(actionLinks, rule.getActionLinks());
		assertSame(actionOccurrenceLinks, rule.getActionOccurrenceLinks());
		assertSame(conditionLinks, rule.getConditionLinks());
		assertSame(conditionOccurrenceLinks, rule.getConditionOccurrenceLinks());
	}

}
