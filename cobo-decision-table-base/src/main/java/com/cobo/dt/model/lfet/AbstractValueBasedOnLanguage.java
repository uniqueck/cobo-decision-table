package com.cobo.dt.model.lfet;

import org.simpleframework.xml.Attribute;

public abstract class AbstractValueBasedOnLanguage extends AbstractValue {
	@Attribute(required = true, name = "language")
	private String language;

	public AbstractValueBasedOnLanguage(String language, String value) {
		super(value);
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}
}

