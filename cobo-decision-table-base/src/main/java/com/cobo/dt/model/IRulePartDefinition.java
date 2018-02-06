package com.cobo.dt.model;

public interface IRulePartDefinition {
	public static final String DONT_CARE_VALUE = IRulePartValue.DONT_CARE_VALUE;
	
	String getId();
	
	String getText();
	void setText(String text);
	
	IRulePartValueSet getValueSet();
	
	IDocu getDocumentation();
}
