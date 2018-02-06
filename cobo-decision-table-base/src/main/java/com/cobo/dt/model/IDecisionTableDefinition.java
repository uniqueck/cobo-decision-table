package com.cobo.dt.model;

import java.util.List;

public interface IDecisionTableDefinition {
	List<IConditionDefinition> getConditionDefinitions();
	List<IActionDefinition> getActionDefinitions();
	IRule createNewRule();
	IActionDefinition addNewActionDefinition(String initialText);
	IConditionDefinition addNewConditionDefinition(String initialText);
}
