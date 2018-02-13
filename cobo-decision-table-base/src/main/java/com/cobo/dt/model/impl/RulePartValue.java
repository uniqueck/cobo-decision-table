package com.cobo.dt.model.impl;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;

import com.cobo.dt.model.IDocu;
import com.cobo.dt.model.IRulePartValue;

public class RulePartValue implements IRulePartValue {
	@Attribute
	private String value;
	@ElementUnion({@Element(required = false, data = true, name = "documentation", type = Docu.class)})
	private IDocu documentation;
	
	public RulePartValue(String value) {
		this.value = value;
		this.documentation = new Docu();
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public IDocu getDocumentation() {
		return documentation;
	}
	
	public void setDocumentation(IDocu documentation) {
		this.documentation = documentation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.toUpperCase().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RulePartValue other = (RulePartValue) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.toUpperCase().equals(other.value.toUpperCase()))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}
