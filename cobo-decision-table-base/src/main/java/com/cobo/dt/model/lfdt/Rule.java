package com.cobo.dt.model.lfdt;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Rule {

	public Rule() {

	}

	@ElementList(inline = true, required = false, entry = "ConditionLink")
	private List<ConditionLink> conditionLinks;

	@ElementList(inline = true, required = false, entry = "ActionLink")
	private List<ActionLink> actionLinks;

	
	@ElementList(inline = true, required = false, entry = "ConditionOccurrenceLink")
	private List<ConditionOccurrenceLink> conditionOccurrenceLinks;
	
	@ElementList(inline = true, required = false, entry = "ActionOccurrenceLink")
	private List<ActionOccurrenceLink> actionnOccurrenceLinks;

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
