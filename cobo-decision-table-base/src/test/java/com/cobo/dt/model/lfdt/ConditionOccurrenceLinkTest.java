package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ConditionOccurrenceLinkTest extends AbstractLfdtTest<ConditionOccurrenceLink> {
	private ConditionOccurrenceLink createUnderTest() {
		return new ConditionOccurrenceLink();
	}

	private String createExpectedXml() {
		return "<conditionOccurrenceLink link='condOccId'/>".replaceAll("'", "\"");
	}

	@Test
	public void testConditionOccurrenceLink() throws Exception {
		ConditionOccurrenceLink conditionOccurrenceLink = createUnderTest();
		assertNull(conditionOccurrenceLink.getConditionOccurrence());
		assertNull(conditionOccurrenceLink.getLink());
	}

	@Test
	public void testPersistConditionLink() throws Exception {
		ConditionOccurrence conditionOccurrence = new ConditionOccurrence("condOccId", new Symbol("English", "symbol"), new Title("English", "title"), new Text("English", "text"), null, null);

		ConditionOccurrenceLink conditionOccurrenceLink = createUnderTest();
		conditionOccurrenceLink.setConditionOccurrence(conditionOccurrence);
		conditionOccurrenceLink.setLink(conditionOccurrence.getUId());
		
		String xml = persist(conditionOccurrenceLink);
		assertEquals(createExpectedXml(), xml);
	}
}
