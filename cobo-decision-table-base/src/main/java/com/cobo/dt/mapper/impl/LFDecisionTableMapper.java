package com.cobo.dt.mapper.impl;

import com.cobo.dt.mapper.MapToDecisionTable;
import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;
import com.cobo.dt.model.IRulePartValue;
import com.cobo.dt.model.impl.DecisionTable;
import com.cobo.dt.model.lfdt.ActionLink;
import com.cobo.dt.model.lfdt.ConditionLink;
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
				for (ConditionLink lfConditionLink : lfRule.getConditionLinks()) {
					if (condition.getDefinition().getText().equals(lfConditionLink.getCondition().getTitle().getValue())) {
						condition.setValue(lfConditionLink.isConditionState() ? "Y" : "N");
					}
				}				
			});
			newRule.getActions().stream().forEach(action -> {
				for (ActionLink lfActionnLink : lfRule.getActionLinks()) {
					if (action.getDefinition().getText().equals(lfActionnLink.getAction().getTitle().getValue())) {
						action.setValue("X");
					}
				}				
			});
			
			

		});
	}

	private void mapActions(LFDecisionTable dt2Map, DecisionTable decisionTable) {
		dt2Map.getActions().stream().forEach(t -> {
			IActionDefinition actionDefinition = decisionTable.getDefinition().addNewActionDefinition(t.getTitle().getValue());
			actionDefinition.getDocumentation().setDescription(t.getText().getValue());
			if (t.getOccurences() != null) {
				actionDefinition.getValueSet().clearValues();
				t.getOccurences().forEach(o -> {
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
			if (t.getOccurences() != null) {
				addNewConditionDefinition.getValueSet().clearValues();
				t.getOccurences().forEach(o -> {
					IRulePartValue addValue = addNewConditionDefinition.getValueSet().addValue(o.getSymbol().getValue());
					addValue.getDocumentation().setDescription(o.getTitle().getValue());
				});
			}
		});
	}

}
