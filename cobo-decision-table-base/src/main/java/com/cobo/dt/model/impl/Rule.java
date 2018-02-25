package com.cobo.dt.model.impl;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;

import com.cobo.dt.model.IAction;
import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.ICondition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;

public class Rule implements IRule {
	@Attribute(required = true, name = "id")
	private String id;
	@ElementListUnion({ @ElementList(inline = true, type = Condition.class) })
	private List<ICondition> conditions;
	@ElementListUnion({ @ElementList(inline = true, type = Action.class) })
	private List<IAction> actions;

	public Rule(@Attribute(name = "id") String id,
			@ElementListUnion({ @ElementList(inline = true, type = Condition.class) }) List<ICondition> conditions,
			@ElementListUnion({ @ElementList(inline = true, type = Action.class) }) List<IAction> actions) {
		this.id = id;
		this.conditions = conditions;
		this.actions = actions;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public List<ICondition> getConditions() {
		return conditions;
	}

	@Override
	public List<IAction> getActions() {
		return actions;
	}

	@Override
	public ICondition getCondition(IConditionDefinition conditionDefinition) {
		for (ICondition condition : getConditions()) {
			if (condition.getDefinition().getId().equals(conditionDefinition.getId())) {
				return condition;
			}
		}
		return null;
	}

	@Override
	public IAction getAction(IActionDefinition actionDefinition) {
		for (IAction action : getActions()) {
			if (action.getDefinition().getId().equals(actionDefinition.getId())) {
				return action;
			}
		}
		return null;
	}

}
