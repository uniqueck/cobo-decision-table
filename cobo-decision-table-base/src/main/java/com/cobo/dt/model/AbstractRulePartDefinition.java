package com.cobo.dt.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;

import com.cobo.dt.model.impl.Docu;
import com.cobo.dt.model.impl.RulePartValueSet;

public abstract class AbstractRulePartDefinition implements IRulePartDefinition {
	@Attribute
	private String id;
	@Attribute(name = "title")
	private String text;

	@ElementUnion({@Element(required = false, type = RulePartValueSet.class)})
	private IRulePartValueSet valueSet;
	@ElementUnion({@Element(required = false, type = Docu.class, name = "documentation")})
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
		return "ID=" + getId() + "; Text=" + getText() + "; Possible Values=" + getValueSet().toString()
				+ System.lineSeparator() + "Documentation=" + getDocumentation().toString();
	}
}
