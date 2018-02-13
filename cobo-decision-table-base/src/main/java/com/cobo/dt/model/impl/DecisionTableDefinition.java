package com.cobo.dt.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;

import com.cobo.dt.model.IAction;
import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.ICondition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IDecisionTableDefinition;
import com.cobo.dt.model.IRule;

public class DecisionTableDefinition implements IDecisionTableDefinition {
	@ElementListUnion({ @ElementList(inline = true, type = ConditionDefinition.class) })
	private List<IConditionDefinition> conditions;
	@ElementListUnion({ @ElementList(inline = true, type = ActionDefinition.class) })
	private List<IActionDefinition> actions;

	public DecisionTableDefinition() {
		this.conditions = new ArrayList<IConditionDefinition>();
		this.actions = new ArrayList<IActionDefinition>();
	}

	public List<IConditionDefinition> getConditionDefinitions() {
		return conditions;
	}

	public List<IActionDefinition> getActionDefinitions() {
		return actions;
	}

	@Override
	public IRule createNewRule() {
		return new Rule(createId(), createNewConditions(), createNewActions());
	}

	@Override
	public IActionDefinition addNewActionDefinition(String initialText) {
		IActionDefinition actionDefinition = createNewActionDefinition(initialText);
		getActionDefinitions().add(actionDefinition);
		return actionDefinition;
	}

	@Override
	public IConditionDefinition addNewConditionDefinition(String initialText) {
		IConditionDefinition conditionDefinition = createNewConditionDefinition(initialText);
		getConditionDefinitions().add(conditionDefinition);
		return conditionDefinition;
	}

	protected IActionDefinition createNewActionDefinition(String initialText) {
		return new ActionDefinition(createId(), initialText);
	}

	protected IConditionDefinition createNewConditionDefinition(String initialText) {
		return new ConditionDefinition(createId(), initialText);
	}

	private String createId() {
		return UUID.randomUUID().toString();
	}

	private List<ICondition> createNewConditions() {
		List<ICondition> conditions = new ArrayList<ICondition>();

		for (IConditionDefinition conditionDefinition : getConditionDefinitions()) {
			conditions.add(new Condition(conditionDefinition));
		}

		return conditions;
	}

	private List<IAction> createNewActions() {
		List<IAction> actions = new ArrayList<IAction>();

		for (IActionDefinition actionDefinition : getActionDefinitions()) {
			actions.add(new Action(actionDefinition));
		}

		return actions;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Conditions:");
		buffer.append(System.lineSeparator());
		buffer.append(getConditionDefinitions().toString());
		buffer.append(System.lineSeparator());
		buffer.append("Actions:");
		buffer.append(System.lineSeparator());
		buffer.append(getActionDefinitions().toString());
		return buffer.toString();
	}
}
