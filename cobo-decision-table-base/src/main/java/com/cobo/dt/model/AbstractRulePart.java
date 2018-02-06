package com.cobo.dt.model;

import com.cobo.dt.model.impl.RulePartValue;

public abstract class AbstractRulePart implements IRulePart {
	private IRulePartDefinition definition;
	private IRulePartValue value;
	
	public AbstractRulePart(IRulePartDefinition definition) {
		this.definition = definition;
		this.value = new RulePartValue(IRulePartValue.DONT_CARE_VALUE);
	}
	
	@Override
	public IRulePartDefinition getDefinition() {
		return definition;
	}
	
	@Override
	public IRulePartValue getValue() {
		return value;
	}
	
	@Override
	public void setValue(String value) {
		IRulePartValue rulePartValue = getDefinition().getValueSet().getValue(value);
		if (rulePartValue==null) {
			throw new RuntimeException("Value '" + value + "' not contained in value set!");			
		}
		this.value = rulePartValue;
	}
	
	@Override
	public void setValue(IRulePartValue rulePartValue) {
		setValue(rulePartValue.getValue());
	}
}
