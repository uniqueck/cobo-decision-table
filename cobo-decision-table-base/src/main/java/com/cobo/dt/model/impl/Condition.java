package com.cobo.dt.model.impl;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;

import com.cobo.dt.model.AbstractRulePart;
import com.cobo.dt.model.ICondition;
import com.cobo.dt.model.IConditionDefinition;

public class Condition extends AbstractRulePart<IConditionDefinition> implements ICondition {
	public Condition(@ElementUnion({@Element(name = "definition", type = ConditionDefinition.class)}) IConditionDefinition conditionDefinition) {
		super(conditionDefinition);
	}

	@ElementUnion({@Element(name = "definition", type = ConditionDefinition.class)})
	@Override
	public IConditionDefinition getDefinition() {
		return super.getDefinition();
	}
	
	
}
