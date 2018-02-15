package com.cobo.dt.model.lfdt;

import org.simpleframework.xml.Attribute;

public abstract class AbstractValue {
	@Attribute(required = true, name = "value")
	private String value;

	public AbstractValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
