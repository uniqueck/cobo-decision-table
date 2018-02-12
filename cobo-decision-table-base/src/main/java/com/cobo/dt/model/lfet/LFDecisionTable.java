package com.cobo.dt.model.lfet;

import java.util.ArrayList;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class LFDecisionTable {
	@Attribute(required = true, name = "version")
	private String version;
	
	@Attribute(required = true, name = "language")
	private String language;
	
	@Attribute(required = true, name = "saveUser")
	private String saveUser;
	
	@Attribute(required = true, name = "saveDate")
	private String saveDate;

	@Element(required = true, name = "Title")
	private Title title;

	@Element(required = true, name = "Text")
	private Text text;
	
	@ElementList(required = false, name = "Conditions")
	private ArrayList<Condition> conditions;

	@ElementList(required = false, name = "Actions")
	private ArrayList<Action> actions;

	@ElementList(required = false, name = "Rules")
	private ArrayList<Rule> rules;

	@ElementList(required = false, inline = true, name = "SourceCode")
	private ArrayList<SourceCode> sourceCodes;

	public LFDecisionTable() {
		this(null, null, null, null, null, null, null, null, null, null);
	}
	
	public LFDecisionTable(String version, String language, String saveUser, String saveDate, Title title, 
			Text text, ArrayList<SourceCode> sourceCodes, ArrayList<Condition> conditions, ArrayList<Action> actions, ArrayList<Rule> rules) {
		this.version = version;
		this.title = title;
		this.text = text;
		this.sourceCodes = sourceCodes;
		this.language = language;
		this.saveUser = saveUser;
		this.saveDate = saveDate;
		this.conditions = conditions;
		this.actions = actions;
		this.rules = rules;
	}
	
	public String getVersion() {
		return version;
	}

	public String getLanguage() {
		return language;
	}
	
	public String getSaveUser() {
		return saveUser;
	}
	
	public String getSaveDate() {
		return saveDate;
	}

	public Title getTitle() {
		return title;
	}

	public Text getText() {
		return text;
	}
		
	public ArrayList<SourceCode> getSourceCodes() {
		return sourceCodes;
	}
	
	public ArrayList<Condition> getConditions() {
		return (ArrayList<Condition>)conditions;
	}

	public ArrayList<Action> getActions() {
		return (ArrayList<Action>)actions;
	}

	public ArrayList<Rule> getRules() {
		return (ArrayList<Rule>)rules;
	}

	public boolean isStateMachine() {
		return false;
	}
}
