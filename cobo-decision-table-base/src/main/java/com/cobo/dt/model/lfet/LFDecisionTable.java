package com.cobo.dt.model.lfet;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class LFDecisionTable {

	@Attribute(required = true, name = "version")
	private String version;
	
	@Attribute(required = true, name = "value")
	@Path(value = "Title")
	private String title;

	@Attribute(required = true, name = "value")
	@Path(value = "Text")
	private String text;
	
	public String getVersion() {
		return version;
	}

	@Attribute(required = true, name = "language")
	private String language;

	public String getLanguage() {
		return language;
	}

	@Attribute(required = true, name = "saveUser")
	private String saveUser;

	@Attribute(required = true, name = "saveDate")
	private String saveDate;

	public String getSaveDate() {
		return saveDate;
	}

	public String getSaveUser() {
		return saveUser;
	}

	@ElementList(name = "Conditions")
	private List<Condition> conditions;

	public List<Condition> getConditions() {
		return conditions;
	}

	@ElementList(name = "Actions")
	private List<Action> actions;

	@ElementList(name = "Rules", required = true)
	private List<Rule> rules;

	public List<Action> getActions() {
		return actions;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public boolean isStateMachine() {
		return false;
	}
	
	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

}
