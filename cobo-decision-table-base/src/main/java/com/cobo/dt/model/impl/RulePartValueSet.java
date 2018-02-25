package com.cobo.dt.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;

import com.cobo.dt.model.IRulePartValue;
import com.cobo.dt.model.IRulePartValueSet;

public class RulePartValueSet implements IRulePartValueSet {
	@ElementListUnion({ @ElementList(inline = true, required = false, type = RulePartValue.class, empty = true) })
	private List<IRulePartValue> values;

	public RulePartValueSet() {
		this.values = new ArrayList<IRulePartValue>();
		this.addValue(IRulePartValue.DONT_CARE_VALUE);
	}

	public List<IRulePartValue> getValues() {
		return values;
	}

	@Override
	public IRulePartValue getValue(String value) {
		String value2Search = value == null ? "" : value;
		for (IRulePartValue decisionTablePartValue : getValues()) {
			if (decisionTablePartValue.getValue().toUpperCase().equals(value2Search.toUpperCase())) {
				return decisionTablePartValue;
			}
		}
		return null;
	}

	@Override
	public IRulePartValue addValue(String value) {
		if (containsValue(value)) {
			throw new RuntimeException("Value '" + value.toUpperCase() + "' already exists in value set!");
		}

		RulePartValue rulePartValue = new RulePartValue(value.toUpperCase());
		getValues().add(rulePartValue);
		return rulePartValue;
	}

	@Override
	public boolean containsValue(IRulePartValue rulePartValue) {
		return getValues().contains(rulePartValue);
	}

	@Override
	public IRulePartValue getDontCareValue() {
		return getValue(IRulePartValue.DONT_CARE_VALUE);
	}

	protected boolean containsValue(String value) {
		return containsValue(new RulePartValue(value));
	}

	@Override
	public String toString() {
		return getValues().toString();
	}

	@Override
	public void clearValues() {
		getValues().clear();
	}

}
