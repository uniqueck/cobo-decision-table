package com.cobo.dt.model.lfet;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Occurences {
	
	@Element(required = true, name = "Symbol")
	private ValueBasedOnLanguage symbol;
	
	@Element(required = true, name = "Title")
	private ValueBasedOnLanguage title;

	@Attribute(required = true, name = "uId")
	private String uid;
	
	public ValueBasedOnLanguage getSymbol() {
		return symbol;
	}
	
	public ValueBasedOnLanguage getTitle() {
		return title;
	}

	public String getuId() {
		return uid;
	}
	@Element(name = "SourceCode", required = false)
	private SourceCode sourceCode;
	
	public SourceCode getSourceCode() {
		return sourceCode;
	}
	

}
