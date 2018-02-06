package com.cobo.dt.model.impl;

import java.util.List;

import com.cobo.dt.model.IAction;
import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.ICondition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;

public class Rule implements IRule {
	private String id;
	private List<ICondition> conditions;
	private List<IAction> actions;
		
	public Rule(String id, List<ICondition> conditions, List<IAction> actions) {
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
