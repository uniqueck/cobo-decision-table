package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ConditionLinkTest extends AbstractLfdtTest<ConditionLink> {
	private ConditionLink createUnderTest() {
		return new ConditionLink();
	}

	private String createExpectedXml() {
		return "<conditionLink link='condId' conditionState='false'/>".replaceAll("'", "\"");
	}

	@Test
	public void testConditionLink() throws Exception {
		ConditionLink conditionLink = createUnderTest();
		assertNull(conditionLink.getLinkedModel());
		assertNull(conditionLink.getLink());
		assertFalse(conditionLink.getConditionState());
	}

	@Test
	public void testPersistConditionLink() throws Exception {
		Condition condition = new Condition("condId", new Title("English", "title"), new Text("English", "text"), null, null, null);

		ConditionLink conditionLink = createUnderTest();
		conditionLink.setLinkedModel(condition);
		conditionLink.setLink(condition.getUId());
		
		String xml = persist(conditionLink);
		assertEquals(createExpectedXml(), xml);
	}
}
