package com.cobo.dt.model.lfdt;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Rule {


	@ElementList(inline = true, required = false, entry = "ConditionLink")
	private List<ConditionLink> conditionLinks;

	@ElementList(inline = true, required = false, entry = "ActionLink")
	private List<ActionLink> actionLinks;

	
	@ElementList(inline = true, required = false, entry = "ConditionOccurrenceLink")
	private List<ConditionOccurrenceLink> conditionOccurrenceLinks;
	
	@ElementList(inline = true, required = false, entry = "ActionOccurrenceLink")
	private List<ActionOccurrenceLink> actionnOccurrenceLinks;


	public Rule() {
		this.actionnOccurrenceLinks = new ArrayList<>();
		this.actionLinks = new  ArrayList<>();
		this.conditionOccurrenceLinks = new ArrayList<>();
		this.conditionLinks = new ArrayList<>();
	}

	
	public List<ConditionLink> getConditionLinks() {
		return conditionLinks;
	}
	
	public List<ConditionOccurrenceLink> getConditionOccurrenceLinks() {
		return conditionOccurrenceLinks;
	}
	
	public List<ActionLink> getActionLinks() {
		return actionLinks;
	}
	
	public List<ActionOccurrenceLink> getActionnOccurrenceLinks() {
		return actionnOccurrenceLinks;
	}

}
