package org.ckr.lfet.model;

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

	public ValueBasedOnLanguage getTitle() {
		return title;
	}
	
	@Element(required = false, name = "Symbol")
	private ValueBasedOnLanguage symbol;
	
	public ValueBasedOnLanguage getSymbol() {
		return symbol;
	}
	
	@ElementList(name = "ActionOccurrences", required = false, inline = false, entry = "ActionOccurrence")
	private List<Occurences> occurences;
	
	public List<Occurences> getOccurences() {
		return occurences;
	}

}
