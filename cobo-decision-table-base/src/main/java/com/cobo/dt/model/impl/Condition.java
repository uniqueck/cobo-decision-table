package com.cobo.dt.model.impl;

import com.cobo.dt.model.AbstractRulePart;
import com.cobo.dt.model.ICondition;
import com.cobo.dt.model.IConditionDefinition;

public class Condition extends AbstractRulePart implements ICondition {
	public Condition(IConditionDefinition conditionDefinition) {
		super(conditionDefinition);
	}
}
