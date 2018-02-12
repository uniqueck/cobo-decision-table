package com.cobo.dt.model.lfet;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Action extends AbstractRulePart<ActionOccurence> {
	public Action(String uid, Title title, Text text, ArrayList<SourceCode> sourceCodes, ArrayList<ActionOccurence> occurences) {
		super(uid, title, text, sourceCodes, occurences);
	}

	@ElementList(name = "ActionOccurrences", required = false, inline = false, entry = "ActionOccurrence")
	public ArrayList<ActionOccurence> getOccurences() {
		return (ArrayList<ActionOccurence>)super.getOccurences();
	}
}
