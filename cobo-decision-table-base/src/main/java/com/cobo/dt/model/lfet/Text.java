package com.cobo.dt.model.lfet;

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
