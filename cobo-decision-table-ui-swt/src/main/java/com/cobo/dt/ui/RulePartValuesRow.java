package com.cobo.dt.ui;

import java.util.List;

import com.cobo.dt.model.IRulePartValue;

public class RulePartValuesRow {
	private List<IRulePartValue> values;
	
	public RulePartValuesRow(List<IRulePartValue> values) {
		this.values = values;
	}
	
	public List<IRulePartValue> getValues() {
		return values;
	}
	
	public IRulePartValue getValue(int index) {
		return getValues().get(index);
	}
	
	@Override
	public String toString() {
		return getValues().toString();
	}
}
