package com.cobo.dt.model;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Transient;
import org.simpleframework.xml.core.Commit;

import com.cobo.dt.model.impl.RulePartValue;

public abstract class AbstractRulePart<T extends IRulePartDefinition> implements IRulePart<T> {

	private String refValue;
	private String refId;

	private T definition;
	private IRulePartValue value;

	public AbstractRulePart(T definition) {
		this.definition = definition;
		this.value = new RulePartValue(IRulePartValue.DONT_CARE_VALUE);
	}

	@Transient
	@Override
	public T getDefinition() {
		return definition;
	}

	@Transient
	@Override
	public void setDefinition(T definition) {
		this.definition = definition;
	}

	@Override
	public IRulePartValue getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		IRulePartValue rulePartValue = getDefinition().getValueSet().getValue(value);
		if (rulePartValue == null) {
			throw new RuntimeException("Value '" + value + "' not contained in value set!");
		}
		this.value = rulePartValue;
	}

	@Override
	public void setValue(IRulePartValue rulePartValue) {
		setValue(rulePartValue.getValue());
	}

	@Attribute(required = true, name = "refValue")
	public String getRefValue() {
		return getValue().getValue();
	}

	@Attribute(required = true, name = "refId")
	public String getRefId() {
		return getDefinition().getId();
	}

	@Attribute(required = true, name = "refValue")
	public void setRefValue(String refValue) {
		this.refValue = refValue;
	}

	@Attribute(required = true, name = "refId")
	public void setRefId(String refId) {
		this.refId = refId;
	}

	@SuppressWarnings("unchecked")
	@Commit
	public void commit(Map<String, Object> session) {
		setDefinition((T) session.get(refId));
		setValue(refValue);
	}
}
