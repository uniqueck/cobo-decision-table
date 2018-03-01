package com.cobo.dt.model.lfdt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringWriter;

import org.junit.Test;
import org.simpleframework.xml.core.AttributeException;
import org.simpleframework.xml.core.Persister;

public class UrlTest {
	private Url createUnderTest() {
		return createUnderTest("title", "file://path", true);
	}
	
	private Url createUnderTest(String title, String url, Boolean executable) {
		return new Url(title, url, executable);
	}
	
	private String persist(Url url) throws Exception {
		Persister xmlPersister = new Persister();
		StringWriter out = new StringWriter();
		xmlPersister.write(url, out);
		return out.toString();		
	}
	
	private Url convertToModel(String xml) throws Exception {
		return new Persister().read(Url.class, xml);
	}
	
	private String createExpectedXml() {
		return "<url title='title' url='file://path' executable='true'/>";
	}

	private String createExpectedXml2() {
		return "<url title='title' url='file://path'/>";
	}

	private String createExpectedXml3() {
		return "<url title='title' url='file://path' executable='false'/>";
	}

	@Test
	public void testUrl_threeParams() throws Exception {
		Url url = createUnderTest("title", "http://url", true);
		assertEquals("title", url.getTitle());
		assertEquals("http://url", url.getUrl());
		assertTrue(url.isExecutable());
		
		url = createUnderTest("title", "http://url", false);
		assertEquals("title", url.getTitle());
		assertEquals("http://url", url.getUrl());
		assertFalse(url.isExecutable());

		url = createUnderTest("title", "http://url", null);
		assertEquals("title", url.getTitle());
		assertEquals("http://url", url.getUrl());
		assertFalse(url.isExecutable());
	}
	
	@Test
	public void testPersistUrl_TitleUrlAndExecutableGiven_noError() throws Exception {
		Url url = createUnderTest();
		String xml = persist(url);
		assertEquals(createExpectedXml().replaceAll("'", "\""), xml);
	}
	
	@Test
	public void testPersistUrl_TitleNotGiven_error() throws Exception {
		Url url = createUnderTest(null, "http://url", true);
		
		try {
			persist(url);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("title is null"));
		}
	}

	@Test
	public void testPersistUrl_UrlNotGiven_error() throws Exception {
		Url url = createUnderTest("title", null, true);
		
		try {
			persist(url);
			fail("Exception expected!");
		} catch (AttributeException ex) {
			assertTrue(ex.getMessage().contains("url is null"));
		}
	}
	
	@Test
	public void testPersistUrl_ExecutableNotGiven_noErrorBecauseOptional() throws Exception {
		Url url = createUnderTest("title", "file://path", null);
		String xml = persist(url);
		assertEquals(createExpectedXml2().replaceAll("'", "\""), xml);
	}
	
	@Test
	public void testPersistUrl_ExecutableValueFalseGiven_noError() throws Exception {
		Url url = createUnderTest("title", "file://path", false);
		String xml = persist(url);
		assertEquals(createExpectedXml3().replaceAll("'", "\""), xml);
	}
	
	@Test
	public void testRead_urlTagWithTwoAttributes() throws Exception {
		String xml = createExpectedXml2().replaceAll("'", "\"");
		Url url = convertToModel(xml);
		assertEquals("title", url.getTitle());
		assertEquals("file://path", url.getUrl());
		assertFalse(url.isExecutable());
	}
}
