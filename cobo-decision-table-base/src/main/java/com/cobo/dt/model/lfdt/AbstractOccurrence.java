package com.cobo.dt.model.lfdt;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root
public abstract class AbstractOccurrence {
	@Attribute(name = "uId")
	private String uid;

	@Element(name = "Symbol")
	private Symbol symbol;

	@Element(name = "Title")
	private Title title;
	
	@Element(name = "Text", required = false)
	private Text text;
		
	@ElementList(name = "SourceCode", required = false, inline = true)
	private List<SourceCode> sourceCodes;
	
	@Path(value = "UrlsOut") 
	@ElementList(entry = "Url", required = false, inline = true) 
	private List<Url> urls;

	public AbstractOccurrence(String uid, Symbol symbol, Title title, Text text, List<SourceCode> sourceCodes, List<Url> urls) {
		this.uid = uid;
		this.symbol = symbol;
		this.title = title;
		this.text = text;
		this.sourceCodes = sourceCodes;
		this.urls = urls;
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
	
	public Text getText() {
		return text;
	}

	public List<SourceCode> getSourceCodes() {
		return sourceCodes;
	}
	
	public List<Url> getUrls() {
		return urls;
	}
}
