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
		LFET lfet = LFETDao.fromFile(new File("src/test/resources/ABFALL_getEvents.lfet"));
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
		
		occ = occurences.get(1);
		assertNotNull(occ);
		assertEquals("1504126505502475", occ.getuId());
		assertValueBasedOnLanguage(occ.getSymbol(), "2");
		assertValueBasedOnLanguage(occ.getTitle(), "event step");
		
		occ = occurences.get(2);
		assertNotNull(occ);
		assertEquals("15042103579651680", occ.getuId());
		assertValueBasedOnLanguage(occ.getSymbol(), "3");
		assertValueBasedOnLanguage(occ.getTitle(), "start time step");
		
		occ = occurences.get(3);
		assertNotNull(occ);
		assertEquals("15042131247763480", occ.getuId());
		assertValueBasedOnLanguage(occ.getSymbol(), "4");
		assertValueBasedOnLanguage(occ.getTitle(), "check start time step");
		
		occ = occurences.get(4);
		assertNotNull(occ);
		assertEquals("15042156349605690", occ.getuId());
		assertValueBasedOnLanguage(occ.getSymbol(), "5");
		assertValueBasedOnLanguage(occ.getTitle(), "search for duplicate step");
		
		
		

	}

	private void assertValueBasedOnLanguage(ValueBasedOnLanguage actualTitle, String expectedValue) {
		assertNotNull(actualTitle);
		assertEquals("German", actualTitle.getLanguage());
		assertEquals(expectedValue, actualTitle.getValue());

	}

}
