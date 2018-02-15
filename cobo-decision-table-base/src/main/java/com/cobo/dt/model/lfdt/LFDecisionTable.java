package com.cobo.dt.model.lfdt;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(strict = false, name = "LFET")
public class LFDecisionTable {
	@Attribute(required = true, name = "version")
	private String version;

	@Attribute(required = true, name = "language")
	private String language;

	@Attribute(required = true, name = "saveUser")
	private String saveUser;

	@Attribute(required = true, name = "saveDate")
	private String saveDate;

	@Element(required = false, name = "Title")
	private Title title;

	@Element(required = false, name = "Text")
	private Text text;

	@Path("Conditions")
	@ElementList(entry = "Condition", inline = true)
	private List<Condition> conditions;

	@Path("Actions")
	@ElementList(entry = "Action", inline = true)
	private List<Action> actions;

	@ElementList(required = false, name = "Rules")
	private List<Rule> rules;

	@ElementList(required = false, inline = true, name = "SourceCode")
	private List<SourceCode> sourceCodes;

	public LFDecisionTable() {
		this(null, null, null, null, null, null, null, null, null, null);
	}

	public LFDecisionTable(
			@Attribute(required = true, name = "version") String version,
			@Attribute(required = true, name = "language") String language,
			@Attribute(required = true, name = "saveUser") String saveUser,
			@Attribute(required = true, name = "saveDate") String saveDate,
			@Element(required = false, name = "Title") Title title, 
			@Element(required = false, name = "Text") Text text,
			@ElementList(inline = true, required = false, name = "SourceCode") List<SourceCode> sourceCodes,
			@Path("Conditions") @ElementList(entry = "Condition", inline = true) List<Condition> conditions,
			@Path("Actions") @ElementList(entry = "Action", inline = true) List<Action> actions, 
			List<Rule> rules) {
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

	public List<SourceCode> getSourceCodes() {
		return sourceCodes;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public List<Action> getActions() {
		return actions;
	}

	public List<Rule> getRules() {
		return rules;
	}

	public boolean isStateMachine() {
		return false;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
}
