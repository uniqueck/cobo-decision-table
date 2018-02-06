package com.cobo.dt.model.impl;

import com.cobo.dt.model.AbstractRulePartDefinition;
import com.cobo.dt.model.IActionDefinition;

public class ActionDefinition extends AbstractRulePartDefinition implements IActionDefinition {
	public ActionDefinition(String id, String initialText) {
		super(id, initialText);
	}
	
	@Override
	protected void initValueSet() {
		getValueSet().addValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);
	}
}
