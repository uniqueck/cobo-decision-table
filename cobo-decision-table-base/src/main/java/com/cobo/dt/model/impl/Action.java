package com.cobo.dt.model.impl;

import org.simpleframework.xml.Transient;

import com.cobo.dt.model.AbstractRulePart;
import com.cobo.dt.model.IAction;
import com.cobo.dt.model.IActionDefinition;

public class Action extends AbstractRulePart<IActionDefinition> implements IAction {

	public Action(@Transient IActionDefinition actionDefinition) {
		super(actionDefinition);
	}

	public Action() {
		super(null);
	}

}
