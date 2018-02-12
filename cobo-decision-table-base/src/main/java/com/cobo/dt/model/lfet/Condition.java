package com.cobo.dt.model.lfet;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Condition extends AbstractRulePart<ConditionOccurence> {
	public Condition(String uid, Title title, Text text, ArrayList<SourceCode> sourceCodes, ArrayList<ConditionOccurence> occurences) {
		super(uid, title, text, sourceCodes, occurences);
	}
	
	@ElementList(name = "ConditionOccurrences", required = false, inline = false, entry = "ConditionOccurrence")
	@Override
	public ArrayList<ConditionOccurence> getOccurences() {
		return (ArrayList<ConditionOccurence>)super.getOccurences();
	}
	
}
