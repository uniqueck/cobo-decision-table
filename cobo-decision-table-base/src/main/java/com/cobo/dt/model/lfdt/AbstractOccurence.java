package com.cobo.dt.model.lfdt;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public abstract class AbstractOccurence {
	@Attribute(required = true, name = "uId")
	private String uid;

	@Element(required = true, name = "Symbol")
	private Symbol symbol;

	@Element(required = true, name = "Title")
	private Title title;
	
	@Element(name = "SourceCode", required = false)
	private SourceCode sourceCode;
	
	public AbstractOccurence(@Attribute(required = true, name = "uId") String uid, 
			@Element(required = true, name = "Symbol") Symbol symbol, 
			@Element(required = true, name = "Title") Title title, 
			@Element(name = "SourceCode", required = false) SourceCode sourceCode) {
		this.uid = uid;
		this.symbol = symbol;
		this.title = title;
		this.sourceCode = sourceCode;
	}
	
	public String getUId() {
		return uid;
	}
	
	public AbstractValueBasedOnLanguage getSymbol() {
		return symbol;
	}
	
	public AbstractValueBasedOnLanguage getTitle() {
		return title;
	}

	public SourceCode getSourceCode() {
		return sourceCode;
	}
}
