package com.cobo.dt.model.impl;

import com.cobo.dt.model.AbstractRulePart;
import com.cobo.dt.model.IAction;
import com.cobo.dt.model.IActionDefinition;

public class Action extends AbstractRulePart implements IAction {
	public Action(IActionDefinition actionDefinition) {
		super(actionDefinition);
	}
}
