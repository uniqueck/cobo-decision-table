package com.cobo.dt.model.impl;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.core.Commit;

import com.cobo.dt.model.AbstractRulePartDefinition;
import com.cobo.dt.model.IActionDefinition;

public class ActionDefinition extends AbstractRulePartDefinition implements IActionDefinition {
	public ActionDefinition(@Attribute(name = "id")String id, @Attribute(name = "title")String initialText) {
		super(id, initialText);
	}
	
	@Override
	protected void initValueSet() {
		getValueSet().addValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);
	}
	
	@Commit
	public void commit(Map<String, Object> session) {
		session.put(getId(), this);
	}
}
