package com.cobo.dt.model;

public interface IRulePart {
	IRulePartDefinition getDefinition();
	
	IRulePartValue getValue();
	void setValue(IRulePartValue value);
	void setValue(String value);
}
