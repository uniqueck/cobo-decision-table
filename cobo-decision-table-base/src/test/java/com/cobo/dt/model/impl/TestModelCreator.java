package com.cobo.dt.model.impl;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IRule;

public class TestModelCreator {
	public DecisionTable createDecisionTable_With3ConditionsAnd4Actions() {
		DecisionTable decisionTable = new DecisionTable();
		
		// Create Conditions and Actions
		decisionTable.getDefinition().addNewConditionDefinition("Lieferfähig?");
		decisionTable.getDefinition().addNewConditionDefinition("Angaben vollständig?");
		decisionTable.getDefinition().addNewConditionDefinition("Bonität in Ordnung?");

		decisionTable.getDefinition().addNewActionDefinition("Lieferung mit Rechnung");
		decisionTable.getDefinition().addNewActionDefinition("Lieferung als Nachnahme");
		decisionTable.getDefinition().addNewActionDefinition("Angaben vervollständigen");
		decisionTable.getDefinition().addNewActionDefinition("Mitteilen: nicht lieferbar");
		
		
		// Create Rules 1-8
		IRule rule1 = decisionTable.addNewRule();
		rule1.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule1.getConditions().get(1).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule1.getConditions().get(2).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule1.getActions().get(0).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);
		rule1.getActions().get(1).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule1.getActions().get(2).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule1.getActions().get(3).setValue(IActionDefinition.DONT_CARE_VALUE);
		
		IRule rule2 = decisionTable.addNewRule();
		rule2.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule2.getConditions().get(1).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule2.getConditions().get(2).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule2.getActions().get(0).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule2.getActions().get(1).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);
		rule2.getActions().get(2).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule2.getActions().get(3).setValue(IActionDefinition.DONT_CARE_VALUE);
		
		IRule rule3 = decisionTable.addNewRule();
		rule3.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule3.getConditions().get(1).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule3.getConditions().get(2).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule3.getActions().get(0).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule3.getActions().get(1).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule3.getActions().get(2).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);
		rule3.getActions().get(3).setValue(IActionDefinition.DONT_CARE_VALUE);

		IRule rule4 = decisionTable.addNewRule();
		rule4.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule4.getConditions().get(1).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule4.getConditions().get(2).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule4.getActions().get(0).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule4.getActions().get(1).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule4.getActions().get(2).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);
		rule4.getActions().get(3).setValue(IActionDefinition.DONT_CARE_VALUE);

		IRule rule5 = decisionTable.addNewRule();
		rule5.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule5.getConditions().get(1).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule5.getConditions().get(2).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule5.getActions().get(0).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule5.getActions().get(1).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule5.getActions().get(2).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule5.getActions().get(3).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);

		IRule rule6 = decisionTable.addNewRule();
		rule6.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule6.getConditions().get(1).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule6.getConditions().get(2).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule6.getActions().get(0).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule6.getActions().get(1).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule6.getActions().get(2).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule6.getActions().get(3).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);

		IRule rule7 = decisionTable.addNewRule();
		rule7.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule7.getConditions().get(1).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule7.getConditions().get(2).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule7.getActions().get(0).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule7.getActions().get(1).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule7.getActions().get(2).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule7.getActions().get(3).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);

		IRule rule8 = decisionTable.addNewRule();
		rule8.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule8.getConditions().get(1).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule8.getConditions().get(2).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule8.getActions().get(0).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule8.getActions().get(1).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule8.getActions().get(2).setValue(IActionDefinition.DONT_CARE_VALUE);
		rule8.getActions().get(3).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);

		return decisionTable;
	}
}
