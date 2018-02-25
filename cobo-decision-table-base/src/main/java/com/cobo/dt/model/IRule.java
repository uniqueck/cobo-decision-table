package com.cobo.dt.model;

import java.util.List;

public interface IRule {
	String getId();
	List<ICondition> getConditions();
	List<IAction> getActions();
	ICondition getCondition(IConditionDefinition conditionDefinition);
	IAction getAction(IActionDefinition actionDefinition);
}
