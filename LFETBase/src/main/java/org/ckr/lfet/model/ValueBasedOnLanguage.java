package org.ckr.lfet.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class ValueBasedOnLanguage {

	public ValueBasedOnLanguage(@Attribute(required = true, name = "language") String language) {
		this.language = language;
	}
	
	private String language;

	@Attribute(required = true, name = "language")
	public String getLanguage() {
		return language;
	}

	@Attribute(required = true, name = "value")
	private String value;

	public String getValue() {
		return value;
	}

}
