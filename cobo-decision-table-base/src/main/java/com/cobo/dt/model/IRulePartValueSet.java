package com.cobo.dt.model;

import java.util.List;

public interface IRulePartValueSet {
	List<IRulePartValue> getValues();
	IRulePartValue getValue(String value);
	IRulePartValue getDontCareValue();
	IRulePartValue addValue(String value);
	boolean containsValue(IRulePartValue rulePartValue);
	void clearValues();
}
