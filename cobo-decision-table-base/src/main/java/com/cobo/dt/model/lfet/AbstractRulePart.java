package com.cobo.dt.model.lfet;

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public abstract class AbstractRulePart<T extends AbstractOccurence> {
	@Attribute(required = true, name = "uId")
	private String uId;
	
	@Element(required = true, name = "Title")
	private Title title;

	@Element(required = false, name = "Text")
	private Text text;

	@ElementList(required = false, inline=true, name = "SourceCode")
	private ArrayList<SourceCode> sourceCodes;

	private ArrayList<T> occurences;

	public AbstractRulePart(String uid, Title title, Text text, ArrayList<SourceCode> sourceCodes, ArrayList<T> occurences) {
		this.uId = uid;
		this.title = title;
		this.text = text;
		this.sourceCodes = sourceCodes;
		this.occurences = occurences;
	}
	
	public String getUId() {
		return uId;
	}
	
	public void setUId(String uId) {
		this.uId = uId;
	}

	public Title getTitle() {
		return title;
	}
	
	public void setTitle(Title title) {
		this.title = title;
	}
	
	public Text getText() {
		return text;
	}
	
	public void setText(Text text) {
		this.text = text;
	}
	
	public ArrayList<SourceCode> getSourceCodes() {
		return sourceCodes;
	}
	
	public void setSourceCodes(ArrayList<SourceCode> sourceCodes) {
		this.sourceCodes = sourceCodes;
	}

	public ArrayList<T> getOccurences() {
		return occurences;
	}
	
	public void setOccurences(ArrayList<T> occurences) {
		this.occurences = occurences;
	}
}
