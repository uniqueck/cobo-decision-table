package com.cobo.dt.model.lfdt;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public abstract class AbstractOccurence {
	@Attribute(name = "uId")
	private String uid;

	@Element(name = "Symbol")
	private Symbol symbol;

	@Element(name = "Title")
	private Title title;
	
	@Element(name = "SourceCode", required = false)
	private SourceCode sourceCode;
	
	public AbstractOccurence(String uid, Symbol symbol, Title title, SourceCode sourceCode) {
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
