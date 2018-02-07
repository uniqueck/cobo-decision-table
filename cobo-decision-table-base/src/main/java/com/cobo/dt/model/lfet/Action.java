package com.cobo.dt.model.lfet;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Action {
	
	@Attribute(required = true, name = "uId")
	private String uId;
	
	public String getuId() {
		return uId;
	}
	
	@Element(required = true, name = "Title")
	private ValueBasedOnLanguage title;
	
	@Element(required = false, name = "Text")
	private ValueBasedOnLanguage text;

	public ValueBasedOnLanguage getTitle() {
		return title;
	}
	
	
	@ElementList(name = "ActionOccurrences", required = false, inline = false, entry = "ActionOccurrence")
	private List<Occurences> occurences;
	
	public List<Occurences> getOccurences() {
		return occurences;
	}
	
	public ValueBasedOnLanguage getText() {
		return text;
	}
	

}
