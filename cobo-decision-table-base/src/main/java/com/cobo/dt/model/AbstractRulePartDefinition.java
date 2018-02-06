package com.cobo.dt.model;

import com.cobo.dt.model.impl.Docu;
import com.cobo.dt.model.impl.RulePartValueSet;

public abstract class AbstractRulePartDefinition implements IRulePartDefinition {	
	private String id;
	private String text;
	
	private IRulePartValueSet valueSet;
	
	private IDocu documentation;
	
	public AbstractRulePartDefinition(String id, String initialText) {
		this.id = id;
		this.text = initialText;
		this.valueSet = new RulePartValueSet();
		this.initValueSet();
		this.documentation = new Docu();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	@Override
	public void setText(String text) {
		this.text = text;
	}
	
	public IRulePartValueSet getValueSet() {
		return valueSet;
	}
	
	public IDocu getDocumentation() {
		return documentation;
	}
	
	protected abstract void initValueSet();
	
	@Override
	public String toString() {
		return "ID=" + getId() + "; Text=" + getText() + "; Possible Values=" + getValueSet().toString() + System.lineSeparator() + "Documentation=" + getDocumentation().toString();
	}
}
