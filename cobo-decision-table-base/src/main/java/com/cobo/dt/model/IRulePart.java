package com.cobo.dt.model;

public interface IRulePart<DEFINITION extends IRulePartDefinition> {
	DEFINITION getDefinition();
	void setDefinition(DEFINITION definition);
	
	
	IRulePartValue getValue();
	void setValue(IRulePartValue value);
	void setValue(String value);
}
