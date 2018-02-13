package com.cobo.dt.model.lfet;

import java.util.List;

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

	@ElementList(required = false, inline = true, name = "SourceCode")
	private List<SourceCode> sourceCodes;

	private List<T> occurences;

	public AbstractRulePart(String uid, Title title, Text text, List<T> occurences, List<SourceCode> sourceCodes) {
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

	public List<SourceCode> getSourceCodes() {
		return sourceCodes;
	}

	public void setSourceCodes(List<SourceCode> sourceCodes) {
		this.sourceCodes = sourceCodes;
	}

	public List<T> getOccurences() {
		return occurences;
	}

	public void setOccurences(List<T> occurences) {
		this.occurences = occurences;
	}
}
