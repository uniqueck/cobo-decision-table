package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.cobo.dt.model.IRulePartValue;

public class RulePartValueSetTest {
	private RulePartValueSet createUnderTest() {
		return new RulePartValueSet();
	}

	@Test
	public void testRulePartValueSet() throws Exception {
		RulePartValueSet valueSet = createUnderTest();
		assertEquals(1, valueSet.getValues().size());
		assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValues().get(0).getValue());
	}

	@Test
	public void testContainsValueIRulePartValue() throws Exception {
		RulePartValueSet valueSet = createUnderTest();

		RulePartValue dontCareRulePartValue = new RulePartValue(IRulePartValue.DONT_CARE_VALUE);
		RulePartValue rulePartValue1 = new RulePartValue("value");

		assertTrue(valueSet.containsValue(dontCareRulePartValue));
		assertFalse(valueSet.containsValue(rulePartValue1));
		
		valueSet.getValues().add(rulePartValue1);
		assertTrue(valueSet.containsValue(dontCareRulePartValue));
		assertTrue(valueSet.containsValue(rulePartValue1));
		assertTrue(valueSet.containsValue(new RulePartValue("Value")));
		assertTrue(valueSet.containsValue(new RulePartValue("VALUE")));
	}

	@Test
	public void testContainsValue() throws Exception {
		RulePartValueSet valueSet = createUnderTest();
		
		assertTrue(valueSet.containsValue(IRulePartValue.DONT_CARE_VALUE));
		assertFalse(valueSet.containsValue("value"));
		
		valueSet.getValues().add(new RulePartValue("value"));
		assertTrue(valueSet.containsValue(IRulePartValue.DONT_CARE_VALUE));
		assertTrue(valueSet.containsValue("value"));
		assertTrue(valueSet.containsValue("Value"));
		assertTrue(valueSet.containsValue("VALUE"));
	}
	
	@Test
	public void testAddValue() throws Exception {
		RulePartValueSet valueSet = createUnderTest();
		
		IRulePartValue rulePartValue = valueSet.addValue("value1");
		assertEquals(2, valueSet.getValues().size());
		assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValues().get(0).getValue());
		assertSame(rulePartValue, valueSet.getValues().get(1));
		assertEquals("VALUE1", valueSet.getValues().get(1).getValue());
		
		try {
			valueSet.addValue("value1");
			fail("Exception expected!");
		} catch (RuntimeException ex) {
			assertEquals("Value 'VALUE1' already exists in value set!", ex.getMessage());
			assertEquals(2, valueSet.getValues().size());
			assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValues().get(0).getValue());
			assertSame(rulePartValue, valueSet.getValues().get(1));
			assertEquals("VALUE1", valueSet.getValues().get(1).getValue());
		}

		try {
			valueSet.addValue("VALUE1");
			fail("Exception expected!");
		} catch (RuntimeException ex) {
			assertEquals("Value 'VALUE1' already exists in value set!", ex.getMessage());
			assertEquals(2, valueSet.getValues().size());
			assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValues().get(0).getValue());
			assertSame(rulePartValue, valueSet.getValues().get(1));
			assertEquals("VALUE1", valueSet.getValues().get(1).getValue());
		}

		IRulePartValue rulePartValue2 = valueSet.addValue("value2");
		assertEquals(3, valueSet.getValues().size());
		assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValues().get(0).getValue());
		assertSame(rulePartValue, valueSet.getValues().get(1));
		assertEquals("VALUE1", valueSet.getValues().get(1).getValue());
		assertSame(rulePartValue2, valueSet.getValues().get(2));
		assertEquals("VALUE2", valueSet.getValues().get(2).getValue());
		
		try {
			valueSet.addValue("value1");
			fail("Exception expected!");
		} catch (RuntimeException ex) {
			assertEquals("Value 'VALUE1' already exists in value set!", ex.getMessage());
			assertEquals(3, valueSet.getValues().size());
			assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValues().get(0).getValue());
			assertSame(rulePartValue, valueSet.getValues().get(1));
			assertEquals("VALUE1", valueSet.getValues().get(1).getValue());
			assertSame(rulePartValue2, valueSet.getValues().get(2));
			assertEquals("VALUE2", valueSet.getValues().get(2).getValue());
		}

		try {
			valueSet.addValue("VALUE1");
			fail("Exception expected!");
		} catch (RuntimeException ex) {
			assertEquals("Value 'VALUE1' already exists in value set!", ex.getMessage());
			assertEquals(3, valueSet.getValues().size());
			assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValues().get(0).getValue());
			assertSame(rulePartValue, valueSet.getValues().get(1));
			assertEquals("VALUE1", valueSet.getValues().get(1).getValue());
			assertSame(rulePartValue2, valueSet.getValues().get(2));
			assertEquals("VALUE2", valueSet.getValues().get(2).getValue());
		}

		try {
			valueSet.addValue("value2");
			fail("Exception expected!");
		} catch (RuntimeException ex) {
			assertEquals("Value 'VALUE2' already exists in value set!", ex.getMessage());
			assertEquals(3, valueSet.getValues().size());
			assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValues().get(0).getValue());
			assertSame(rulePartValue, valueSet.getValues().get(1));
			assertEquals("VALUE1", valueSet.getValues().get(1).getValue());
			assertSame(rulePartValue2, valueSet.getValues().get(2));
			assertEquals("VALUE2", valueSet.getValues().get(2).getValue());
		}

		try {
			valueSet.addValue("VALUE2");
			fail("Exception expected!");
		} catch (RuntimeException ex) {
			assertEquals("Value 'VALUE2' already exists in value set!", ex.getMessage());
			assertEquals(3, valueSet.getValues().size());
			assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValues().get(0).getValue());
			assertSame(rulePartValue, valueSet.getValues().get(1));
			assertEquals("VALUE1", valueSet.getValues().get(1).getValue());
			assertSame(rulePartValue2, valueSet.getValues().get(2));
			assertEquals("VALUE2", valueSet.getValues().get(2).getValue());
		}
	}

	@Test
	public void testGetDontCareValue() throws Exception {
		RulePartValueSet valueSet = createUnderTest();
		valueSet.addValue("AAA");
		valueSet.addValue("BBB");
		
		IRulePartValue dontCareValue = valueSet.getDontCareValue();
		assertEquals(IRulePartValue.DONT_CARE_VALUE, dontCareValue.getValue());
		
		valueSet.getValues().remove(dontCareValue);
		assertNull(valueSet.getDontCareValue());
	}

	@Test
	public void testGetValue() throws Exception {
		RulePartValueSet valueSet = createUnderTest();
		assertEquals(IRulePartValue.DONT_CARE_VALUE, valueSet.getValue(IRulePartValue.DONT_CARE_VALUE).getValue());
		assertNull(valueSet.getValue("valueNotExists"));
		
		IRulePartValue rulePartValue = valueSet.addValue("AAA");
		assertSame(rulePartValue, valueSet.getValue("AAA"));
		assertSame(rulePartValue, valueSet.getValue("Aaa"));
		assertSame(rulePartValue, valueSet.getValue("aaa"));
	}

	@Test
	public void testToString() throws Exception {
		RulePartValueSet valueSet = createUnderTest();
		assertEquals("[-]", valueSet.toString());
		
		valueSet.addValue("AAA");
		assertEquals("[-, AAA]", valueSet.toString());
		
		valueSet.addValue("BBB");
		assertEquals("[-, AAA, BBB]", valueSet.toString());
	}
}
