package com.cobo.dt.model.lfdt;

import org.simpleframework.xml.Root;

@Root(strict = false, name="Symbol")
public class Symbol extends AbstractValueBasedOnLanguage {
	public Symbol(String language, String value) {
		super(language, value);
	}
	
	public Symbol() {
		this(null,null);
	}
}

