package com.cobo.dt.model;

import org.simpleframework.xml.Element;

import com.cobo.dt.model.impl.RulePartValue;

public abstract class AbstractRulePart<T extends IRulePartDefinition> implements IRulePart {
	
	private T definition;
	private IRulePartValue value;
	
	public AbstractRulePart(T definition) {
		this.definition = definition;
		this.value = new RulePartValue(IRulePartValue.DONT_CARE_VALUE);
	}
	
	@Override
	public T getDefinition() {
		return definition;
	}
	
	
	@Element(type = RulePartValue.class, name = "rulePartValue")
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
	
	@Element(type = RulePartValue.class, name = "rulePartValue")
	@Override
	public void setValue(IRulePartValue rulePartValue) {
		setValue(rulePartValue.getValue());
	}
}
