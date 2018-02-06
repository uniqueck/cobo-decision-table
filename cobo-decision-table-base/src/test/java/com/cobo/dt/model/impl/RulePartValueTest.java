package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RulePartValueTest {
	private RulePartValue createUnderTest(String value) {
		return new RulePartValue(value);
	}

	@Test
	public void testRulePartValue() throws Exception {
		RulePartValue value = createUnderTest("value1");
		assertEquals("value1", value.getValue());
		assertNotNull(value.getDocumentation());
		assertEquals("", value.getDocumentation().getDescription());
	}

	@Test
	public void testEquals() throws Exception {
		RulePartValue value1_1 = createUnderTest("value1");
		Docu docu1_1 = new Docu();
		docu1_1.setDescription("description1_1");
		value1_1.setDocumentation(docu1_1);
		
		RulePartValue value1_2 = createUnderTest("value1");
		Docu docu1_2 = new Docu();
		docu1_2.setDescription("description1_2");
		value1_2.setDocumentation(docu1_2);

		RulePartValue value1_3 = createUnderTest("VALUE1");
		Docu docu1_3 = new Docu();
		docu1_3.setDescription("description1_3");
		value1_3.setDocumentation(docu1_3);

		RulePartValue value2 = createUnderTest("value2");
		Docu docu2 = new Docu();
		docu2.setDescription("description2");
		value2.setDocumentation(docu2);

		RulePartValue value3 = createUnderTest("");
		Docu docu3 = new Docu();
		docu3.setDescription("emptyValue");
		value3.setDocumentation(docu3);

		assertEquals(value1_1, value1_1);
		assertEquals(value1_2, value1_2);
		assertEquals(value1_3, value1_3);
		assertEquals(value2, value2);
		assertEquals(value3, value3);
		
		assertEquals(value1_1, value1_2);
		assertEquals(value1_1, value1_3);
		assertEquals(value1_2, value1_1);
		assertEquals(value1_2, value1_3);
		assertEquals(value1_3, value1_1);
		assertEquals(value1_3, value1_2);
		
		assertFalse(value1_1.equals(value2));
		assertFalse(value1_2.equals(value2));
		assertFalse(value1_3.equals(value2));

		assertFalse(value1_1.equals(value3));
		assertFalse(value1_2.equals(value3));
		assertFalse(value1_3.equals(value3));

		assertFalse(value2.equals(value1_1));
		assertFalse(value2.equals(value1_2));
		assertFalse(value2.equals(value1_3));
		assertFalse(value2.equals(value3));

		assertFalse(value3.equals(value1_1));
		assertFalse(value3.equals(value1_2));
		assertFalse(value3.equals(value1_3));
		assertFalse(value3.equals(value2));
	}

	@Test
	public void testToString() throws Exception {
		RulePartValue value = createUnderTest("value1");
		assertEquals("value1", value.toString());
	}
}
