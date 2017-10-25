package org.ckr.lfet.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class LFETTest {

	@Test
	public void testLFET() throws Exception {
		org.simpleframework.xml.core.Persister persister = new org.simpleframework.xml.core.Persister();
		LFET lfet = persister.read(LFET.class, new File("src/test/resources/ABFALL_getEvents.lfet"));
		Assert.assertNotNull(lfet);
		assertEquals("LF-ET 2.1.5 (170306b)", lfet.getVersion());
		assertEquals("2017.09.08 at 13:21:39 CEST", lfet.getSaveDate());
		assertEquals("German", lfet.getLanguage());
		assertEquals("constantin", lfet.getSaveUser());

		assertNotNull(lfet.getActions());
		assertEquals(13, lfet.getActions().size());

		assertNotNull(lfet.getRules());
		assertEquals(19, lfet.getRules().size());

		assertConditions(lfet.getConditions());

	}

	private void assertConditions(List<Condition> conditions) {
		assertNotNull(conditions);
		assertEquals(12, conditions.size());

		Condition condition = conditions.get(0);
		assertNotNull(condition);
		assertNotNull(condition.getuId());
		assertEquals("1504126444840415", condition.getuId());

		assertValueBasedOnLanguage(condition.getTitle(), "step");

		List<Occurences> occurences = condition.getOccurences();
		assertNotNull(occurences);
		assertEquals(5, occurences.size());
		Occurences occ = occurences.get(0);
		assertNotNull(occ);
		assertEquals("1504126505502473", occ.getuId());
		assertValueBasedOnLanguage(occ.getSymbol(), "1");
		assertValueBasedOnLanguage(occ.getTitle(), "calendar step");
		

	}

	private void assertValueBasedOnLanguage(ValueBasedOnLanguage actualTitle, String expectedValue) {
		assertNotNull(actualTitle);
		assertEquals("German", actualTitle.getLanguage());
		assertEquals(expectedValue, actualTitle.getValue());

	}

}
