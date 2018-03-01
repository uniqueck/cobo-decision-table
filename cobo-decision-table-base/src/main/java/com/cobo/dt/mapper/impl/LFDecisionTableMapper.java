package com.cobo.dt.mapper.impl;

import java.util.List;

import com.cobo.dt.mapper.MapToDecisionTable;
import com.cobo.dt.model.IAction;
import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.ICondition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;
import com.cobo.dt.model.IRulePartValue;
import com.cobo.dt.model.impl.DecisionTable;
import com.cobo.dt.model.lfdt.Action;
import com.cobo.dt.model.lfdt.ActionLink;
import com.cobo.dt.model.lfdt.ActionOccurrenceLink;
import com.cobo.dt.model.lfdt.Condition;
import com.cobo.dt.model.lfdt.ConditionLink;
import com.cobo.dt.model.lfdt.ConditionOccurrenceLink;
import com.cobo.dt.model.lfdt.LFDecisionTable;

public class LFDecisionTableMapper implements MapToDecisionTable<LFDecisionTable> {

	public LFDecisionTableMapper() {
	}

	@Override
	public DecisionTable map(LFDecisionTable dt2Map) {

		DecisionTable decisionTable = new DecisionTable();
		decisionTable.setTitle(dt2Map.getTitle().getValue());
		decisionTable.getDocumentation().setDescription(dt2Map.getText().getValue());
		
		mapConditions(dt2Map, decisionTable);
		
		mapActions(dt2Map, decisionTable);
		
		mapRules(dt2Map, decisionTable);
		
		
		
		return decisionTable;
	}

	private void mapRules(LFDecisionTable dt2Map, DecisionTable decisionTable) {
		dt2Map.getRules().stream().forEach(lfRule -> {
			IRule newRule = decisionTable.addNewRule();
			newRule.getConditions().stream().forEach(condition -> {
				if (lfRule.getConditionLinks()!=null) {
					for (ConditionLink lfConditionLink : lfRule.getConditionLinks()) {
						if (condition.getDefinition().getText().equals(lfConditionLink.getLinkedModel().getTitle().getValue())) {
							condition.setValue(lfConditionLink.getConditionState() ? "Y" : "N");
						}
					}					
				}
				map(condition, lfRule.getConditionOccurrenceLinks());				
			});
			newRule.getActions().stream().forEach(action -> {
				if (lfRule.getActionLinks()!=null) {
					for (ActionLink lfActionnLink : lfRule.getActionLinks()) {
						if (action.getDefinition().getText().equals(lfActionnLink.getLinkedModel().getTitle().getValue())) {
							action.setValue("X");
						}
					}						
				}
				map(action, lfRule.getActionOccurrenceLinks());	
			});
			
			

		});
	}

	private void map(IAction action, List<ActionOccurrenceLink> actionOccurrenceLinks) {
		if (actionOccurrenceLinks!=null) {
			for (ActionOccurrenceLink eachOccLink : actionOccurrenceLinks) {
				Action lfAction = eachOccLink.getLinkedModel().getAction();
				if (lfAction.getTitle().getValue().equals(action.getDefinition().getText())) {
					action.setValue(eachOccLink.getLinkedModel().getSymbol().getValue());
				}
			}			
		}
	}

	protected void map(ICondition condition, List<ConditionOccurrenceLink> conditionOccurrenceLinks) {
		if (conditionOccurrenceLinks!=null) {
			for (ConditionOccurrenceLink eachOccLink : conditionOccurrenceLinks) {
				Condition lfCondition = eachOccLink.getLinkedModel().getCondition();
				if (lfCondition.getTitle().getValue().equals(condition.getDefinition().getText())) {
					condition.setValue(eachOccLink.getLinkedModel().getSymbol().getValue());
				}
			}			
		}
	}

	private void mapActions(LFDecisionTable dt2Map, DecisionTable decisionTable) {
		dt2Map.getActions().stream().forEach(t -> {
			IActionDefinition actionDefinition = decisionTable.getDefinition().addNewActionDefinition(t.getTitle().getValue());
			actionDefinition.getDocumentation().setDescription(t.getText().getValue());
			if (t.getOccurrences() != null) {
				actionDefinition.getValueSet().clearValues();
				t.getOccurrences().forEach(o -> {
					IRulePartValue addValue = actionDefinition.getValueSet().addValue(o.getSymbol().getValue());
					addValue.getDocumentation().setDescription(o.getTitle().getValue());
				});
			}
		});
	}

	private void mapConditions(LFDecisionTable dt2Map, DecisionTable decisionTable) {
		dt2Map.getConditions().stream().forEach(t -> {
			IConditionDefinition addNewConditionDefinition = decisionTable.getDefinition().addNewConditionDefinition(t.getTitle().getValue());
			addNewConditionDefinition.getDocumentation().setDescription(t.getText().getValue());
			if (t.getOccurrences() != null) {
				addNewConditionDefinition.getValueSet().clearValues();
				t.getOccurrences().forEach(o -> {
					IRulePartValue addValue = addNewConditionDefinition.getValueSet().addValue(o.getSymbol().getValue());
					addValue.getDocumentation().setDescription(o.getTitle().getValue());
				});
			}
		});
	}

}
