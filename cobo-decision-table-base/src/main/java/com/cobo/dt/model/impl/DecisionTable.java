package com.cobo.dt.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Default;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Root;

import com.cobo.dt.model.IActionDefinition;
import com.cobo.dt.model.IConditionDefinition;
import com.cobo.dt.model.IDecisionTableDefinition;
import com.cobo.dt.model.IDocu;
import com.cobo.dt.model.IRule;
import com.cobo.dt.model.IRulePartValue;

@Root
@Default
public class DecisionTable {
	@ElementUnion({ @Element(name = "decisionTableDefinition", type = DecisionTableDefinition.class) })
	private IDecisionTableDefinition definition;
	@ElementListUnion({@ElementList(inline = true, type = Rule.class)})
	private List<IRule> rules;
	@ElementUnion({@Element(required = false, type = Docu.class, name = "documentation")})
	private IDocu documentation;

	@Attribute(required = false, name = "title")
	private String title;

	public DecisionTable() {
		this.definition = new DecisionTableDefinition();
		this.rules = new ArrayList<IRule>();
		this.documentation = new Docu();
		this.title = "";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public IDecisionTableDefinition getDefinition() {
		return definition;
	}

	public List<IRule> getRules() {
		return rules;
	}

	public IDocu getDocumentation() {
		return documentation;
	}

	public void initDefault() {
		getDefinition().addNewConditionDefinition("New Condition");
		getDefinition().addNewActionDefinition("New Action 1");
		getDefinition().addNewActionDefinition("New Action 2");

		IRule rule1 = addNewRule();
		rule1.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_YES);
		rule1.getActions().get(0).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);
		rule1.getActions().get(1).setValue(IRulePartValue.DONT_CARE_VALUE);

		IRule rule2 = addNewRule();
		rule2.getConditions().get(0).setValue(IConditionDefinition.DEFAULT_VALUE_CONDITION_NO);
		rule2.getActions().get(0).setValue(IRulePartValue.DONT_CARE_VALUE);
		rule2.getActions().get(1).setValue(IActionDefinition.DEFAULT_VALUE_ACTION_SET);
	}

	public IRule addNewRule() {
		return addNewRule(getRules().size());
	}

	public IRule addNewRule(int index) {
		IRule rule = getDefinition().createNewRule();
		getRules().add(index, rule);
		return rule;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();

		int firstColumnWidth = 30;
		int ruleColumnWidth = 3;

		for (int i = 0; i < getDefinition().getConditionDefinitions().size(); i++) {
			buffer.append(paddingRight(getDefinition().getConditionDefinitions().get(i).getText(), firstColumnWidth));

			for (int j = 0; j < getRules().size(); j++) {
				buffer.append(
						paddingRight(getRules().get(j).getConditions().get(i).getValue().getValue(), ruleColumnWidth));
			}

			buffer.append(System.lineSeparator());
		}

		buffer.append(System.lineSeparator());

		for (int i = 0; i < getDefinition().getActionDefinitions().size(); i++) {
			buffer.append(paddingRight(getDefinition().getActionDefinitions().get(i).getText(), firstColumnWidth));

			for (int j = 0; j < getRules().size(); j++) {
				buffer.append(
						paddingRight(getRules().get(j).getActions().get(i).getValue().getValue(), ruleColumnWidth));
			}

			buffer.append(System.lineSeparator());
		}

		return buffer.toString();
	}

	protected String paddingRight(String text, int endSize) {
		text = text == null ? "" : text;
		text = text + replicate(" ", endSize);
		return text.substring(0, endSize);
	}

	protected String replicate(String text2Replicate, int count) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < count; i++) {
			buffer.append(text2Replicate);
		}
		return buffer.toString();
	}
}
