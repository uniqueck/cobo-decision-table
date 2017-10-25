package org.ckr.lfet.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class SourceCode extends ValueBasedOnLanguage {

	public SourceCode(@Attribute(required = true, name="sourceCode") String language) {
		super(language);
	}
	
	@Attribute(required = true, name = "codeLanguage")
	@Override
	public String getLanguage() {
		return super.getLanguage();
	}

	
	
	
}
