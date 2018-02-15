package com.cobo.dt.model.lfdt;

import org.simpleframework.xml.Root;

@Root(strict = false, name="Text")
public class Text extends AbstractValueBasedOnLanguage {
	public Text(String language, String value) {
		super(language, value);
	}
	
	public Text() {
		this(null, null);
	}
}
