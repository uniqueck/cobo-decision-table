package org.ckr.lfet.model;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class LFET {


	@Attribute(required = true, name = "version")
	private String version;
	
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
	
	
	
	
}
