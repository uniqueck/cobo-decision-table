package com.cobo.dt.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AbstractRulePartDefinitionTest {
	private AbstractRulePartDefinition createUnderTest(String id, String text) {
		return new AbstractRulePartDefinition(id, text) {
			@Override
			protected void initValueSet() {
				getValueSet().addValue("value1");
				getValueSet().addValue("value2");
			}
		};
	}
	
	@Test
	public void testAbstractRulePartDefinition() throws Exception {
		AbstractRulePartDefinition tablePart = createUnderTest("id", "text");
		assertEquals("id", tablePart.getId());
		assertEquals("text", tablePart.getText());
		assertNotNull(tablePart.getDocumentation());
		assertEquals(3, tablePart.getValueSet().getValues().size());
		assertEquals(IRulePartValue.DONT_CARE_VALUE, tablePart.getValueSet().getValues().get(0).getValue());
		assertEquals("VALUE1", tablePart.getValueSet().getValues().get(1).getValue());
		assertEquals("VALUE2", tablePart.getValueSet().getValues().get(2).getValue());
		assertEquals("", tablePart.getDocumentation().getDescription());
	}

	@Test
	public void testToString() throws Exception {
		AbstractRulePartDefinition tablePart = createUnderTest("id", "text");
		assertEquals("ID=id; Text=text; Possible Values=[-, VALUE1, VALUE2]" + System.lineSeparator() + "Documentation=", tablePart.toString());
	
		tablePart.setText("text2");
		tablePart.getDocumentation().setDescription("description");
		assertEquals("ID=id; Text=text2; Possible Values=[-, VALUE1, VALUE2]" + System.lineSeparator() + "Documentation=description", tablePart.toString());
	}
}
