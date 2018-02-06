package com.cobo.dt.model.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DocuTest {
	private Docu createUnderTest() {
		return new Docu();
	}
	
	@Test
	public void testDocu() throws Exception {
		Docu docu = createUnderTest();
		assertEquals("", docu.getDescription());
	}

	@Test
	public void testToString() throws Exception {
		Docu docu = createUnderTest();
		assertEquals("", docu.toString());
		
		docu.setDescription("description");
		assertEquals("description", docu.toString());
	}
}
