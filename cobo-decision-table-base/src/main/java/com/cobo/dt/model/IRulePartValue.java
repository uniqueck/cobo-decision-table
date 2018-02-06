package com.cobo.dt.model;

public interface IRulePartValue {
	static final String DONT_CARE_VALUE = "-";

	String getValue();
	void setValue(String value);
	
	IDocu getDocumentation();
	void setDocumentation(IDocu documentation);
}
