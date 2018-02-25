package com.cobo.dt.model.impl;

import org.simpleframework.xml.Transient;

import com.cobo.dt.model.AbstractRulePart;
import com.cobo.dt.model.ICondition;
import com.cobo.dt.model.IConditionDefinition;

public class Condition extends AbstractRulePart<IConditionDefinition> implements ICondition {

	public Condition() {
		super(null);
	}

	public Condition(@Transient IConditionDefinition conditionDefinition) {
		super(conditionDefinition);
	}

}
