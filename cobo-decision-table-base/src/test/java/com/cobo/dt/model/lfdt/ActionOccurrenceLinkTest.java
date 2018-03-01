package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ActionOccurrenceLinkTest extends AbstractLfdtTest<ActionOccurrenceLink> {
	private ActionOccurrenceLink createUnderTest() {
		return new ActionOccurrenceLink();
	}

	private String createExpectedXml() {
		return "<actionOccurrenceLink link='actionOccId'/>".replaceAll("'", "\"");
	}

	@Test
	public void testActionOccurrenceLink() throws Exception {
		ActionOccurrenceLink actionOccurrenceLink = createUnderTest();
		assertNull(actionOccurrenceLink.getLinkedModel());
		assertNull(actionOccurrenceLink.getLink());
	}

	@Test
	public void testPersistActionOccurrenceLink() throws Exception {
		ActionOccurrence actionOccurrence = new ActionOccurrence("actionOccId", new Symbol("English", "symbol"), new Title("English", "title"), new Text("English", "text"), null, null);

		ActionOccurrenceLink actionOccurrenceLink = createUnderTest();
		actionOccurrenceLink.setLinkedModel(actionOccurrence);
		actionOccurrenceLink.setLink(actionOccurrence.getUId());
		
		String xml = persist(actionOccurrenceLink);
		assertEquals(createExpectedXml(), xml);
	}
}
