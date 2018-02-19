package com.cobo.dt.model.impl;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;

import com.cobo.dt.model.AbstractRulePart;
import com.cobo.dt.model.IAction;
import com.cobo.dt.model.IActionDefinition;

public class Action extends AbstractRulePart<IActionDefinition> implements IAction {
	public Action(@ElementUnion({@Element(name ="definition", type = ActionDefinition.class)}) IActionDefinition actionDefinition) {
		super(actionDefinition);
	}
	
	@ElementUnion({@Element(name ="definition", type = ActionDefinition.class)})
	@Override
	public IActionDefinition getDefinition() {
		return super.getDefinition();
	}
	
	
}
