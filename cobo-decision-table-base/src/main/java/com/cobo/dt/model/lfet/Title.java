package com.cobo.dt.model.lfet;

import org.simpleframework.xml.Root;

@Root(strict = false, name="Title")
public class Title extends AbstractValueBasedOnLanguage {
	public Title(String language, String value) {
		super(language, value);
	}
	
	public Title() {
		this(null, null);
	}
}
