package com.cobo.dt.model.lfdt;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "LFET", strict = false)
public class LFDecisionTable {
	@Attribute(name = "version")
	private String version;

	@Attribute(name = "language")
	private String language;

	@Attribute(name = "saveUser")
	private String saveUser;

	@Attribute(name = "saveDate")
	private String saveDate;

	@Element(name = "Title", required = false)
	private Title title;

	@Element(name = "Text", required = false)
	private Text text;

	@Path("Conditions")
	@ElementList(entry = "Condition", inline = true)
	private List<Condition> conditions;

	@Path("Actions")
	@ElementList(entry = "Action", inline = true)
	private List<Action> actions;

	@Path(value = "Rules")
	@Attribute(name = "lastId")
	private String lastRuleId;

	@Path(value = "Rules")
	@ElementList(entry = "Rule", required = false, inline = true)
	private List<Rule> rules;

	@ElementList(name = "SourceCode", required = false, inline = true)
	private List<SourceCode> sourceCodes;

	@Path(value = "UrlsOut") 
	@ElementList(entry = "Url", required = false, inline = true) 
	private List<Url> urls;
	
	@Path("Snapshots")
	@ElementList(entry = "Snapshot", required = false, inline = true)	
	private List<Snapshot> snapshots;
	
	public LFDecisionTable() {
		this(null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	public LFDecisionTable(
			@Attribute(name = "version") 
			String version,
			
			@Attribute(name = "language") 
			String language,
			
			@Attribute(name = "saveUser") 
			String saveUser,
			
			@Attribute(name = "saveDate") 
			String saveDate,
			
			@Element(name = "Title", required = false) 
			Title title,
			
			@Element(name = "Text", required = false) 
			Text text,
			
			@ElementList(name = "SourceCode", required = false, inline = true) 
			List<SourceCode> sourceCodes,
			
			@Path("Conditions") @ElementList(entry = "Condition", inline = true) 
			List<Condition> conditions,
			
			@Path("Actions") @ElementList(entry = "Action", inline = true) 
			List<Action> actions,
			
			@Path(value = "Rules")
			@Attribute(name = "lastId")
			String lastRuleId,
			
			@Path(value = "Rules")
			@ElementList(entry = "Rule", required = false, inline = true)
			List<Rule> rules,
			
			@Path(value = "UrlsOut") 
			@ElementList(entry = "Url", required = false, inline = true) 
			List<Url> urls,

			@Path("Snapshots")
			@ElementList(entry = "Snapshot", required = false, inline = true)	
			List<Snapshot> snapshots
		) {
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
		this.urls = urls;
		this.lastRuleId = lastRuleId;
		this.snapshots = snapshots;
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

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
	
	public List<Url> getUrls() {
		return urls;
	}
	
	public String getLastRuleId() {
		return lastRuleId;
	}
	
	public List<Snapshot> getSnapshots() {
		return snapshots;
	}
}
