package com.cobo.dt.model.lfet;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Title {

	@Attribute(required = true, name = "language")
	private String language;

	public String getLanguage() {
		return language;
	}
	
}
