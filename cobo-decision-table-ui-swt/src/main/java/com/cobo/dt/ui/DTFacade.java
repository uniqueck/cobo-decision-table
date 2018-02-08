package com.cobo.dt.ui;

import java.util.ArrayList;
import java.util.List;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;
import com.cobo.dt.model.IRulePartValue;
import com.cobo.dt.model.impl.DecisionTable;
import com.gorob.client.logging.AbstractLogging;
import com.gorob.gui.model.AbstractFacade;

public class DTFacade extends AbstractFacade<DTDialogParameter> {
	private DecisionTable decisionTable;
	private IConditionDefinition selectedConditionDefinition;
	private IActionDefinition selectedActionDefinition;
	private RulePartValuesRow selectedConditionValueRow;
	private RulePartValuesRow selectedActionValueRow;
	private RuleNamesHeaderRow selectedRuleNameHeaderRow;
	
	public DTFacade(DTDialogParameter dialogParameter, AbstractLogging log) {
		super(dialogParameter, log);
		this.decisionTable = new DecisionTable();
		this.decisionTable.initDefault();
		this.selectedConditionDefinition = null;
		this.selectedActionDefinition = null;
		this.selectedConditionValueRow = null;
		this.selectedActionValueRow = null;
		this.selectedRuleNameHeaderRow = null;
	}
	
	public DecisionTable getDecisionTable() {
		return decisionTable;
	}
	
	public List<IConditionDefinition> getConditionDefinitions() {
		return getDecisionTable().getDefinition().getConditionDefinitions();
	}
	
	public IConditionDefinition getSelectedConditionDefinition() {
		return selectedConditionDefinition;
	}
	
	public void setSelectedConditionDefinition(IConditionDefinition selectedConditionDefinition) {
		this.selectedConditionDefinition = selectedConditionDefinition;
	}
	
	public List<IActionDefinition> getActionDefinitions() {
		return getDecisionTable().getDefinition().getActionDefinitions();
	}
	
	public IActionDefinition getSelectedActionDefinition() {
		return selectedActionDefinition;
	}

	public void setSelectedActionDefinition(IActionDefinition selectedActionDefinition) {
		this.selectedActionDefinition = selectedActionDefinition;
	}
	
	public List<RulePartValuesRow> getConditionValueRows() {
		List<RulePartValuesRow> rows = new ArrayList<RulePartValuesRow>();
		
		for (IConditionDefinition conditionDefinition : getDecisionTable().getDefinition().getConditionDefinitions()) {
			List<IRulePartValue> values = new ArrayList<IRulePartValue>();
			
			for (IRule rule : getDecisionTable().getRules()) {
				values.add(rule.getCondition(conditionDefinition).getValue());
			}
			
			rows.add(new RulePartValuesRow(values));
		}
		
		return rows;
	}
	
	public List<RulePartValuesRow> getActionValueRows() {
		List<RulePartValuesRow> rows = new ArrayList<RulePartValuesRow>();
		
		for (IActionDefinition actionDefinition : getDecisionTable().getDefinition().getActionDefinitions()) {
			List<IRulePartValue> values = new ArrayList<IRulePartValue>();
			
			for (IRule rule : getDecisionTable().getRules()) {
				values.add(rule.getAction(actionDefinition).getValue());
			}
			
			rows.add(new RulePartValuesRow(values));
		}
		
		return rows;
	}
	
	public List<RuleNamesHeaderRow> getRuleNameHeaderRows() {
		List<RuleNamesHeaderRow> rows = new ArrayList<RuleNamesHeaderRow>();
		rows.add(new RuleNamesHeaderRow(getRuleNames()));
		return rows;
	}
	
	private List<String> getRuleNames() {
		List<String> ruleNames = new ArrayList<String>();
		
		for (int i=1; i<=getDecisionTable().getRules().size(); i++) {
			ruleNames.add("R" + i);
		}
		
		return ruleNames;
	}
	
	public RulePartValuesRow getSelectedConditionValueRow() {
		return selectedConditionValueRow;
	}
	
	public void setSelectedConditionValueRow(RulePartValuesRow selectedConditionValueRow) {
		this.selectedConditionValueRow = selectedConditionValueRow;
	}
	
	public RulePartValuesRow getSelectedActionValueRow() {
		return selectedActionValueRow;
	}
	
	public void setSelectedActionValueRow(RulePartValuesRow selectedActionValueRow) {
		this.selectedActionValueRow = selectedActionValueRow;
	}
	
	public RuleNamesHeaderRow getSelectedRuleNameHeaderRow() {
		return selectedRuleNameHeaderRow;
	}
	
	public void setSelectedRuleNameHeaderRow(RuleNamesHeaderRow selectedRuleNameHeaderRow) {
		this.selectedRuleNameHeaderRow = selectedRuleNameHeaderRow;
	}
}
