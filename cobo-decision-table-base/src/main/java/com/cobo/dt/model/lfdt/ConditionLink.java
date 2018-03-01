package com.cobo.dt.model.lfdt;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root(strict = false)
public class ConditionLink {
	@Attribute(name = "link")
	private String link;
	
	private Condition condition;
	
	@Attribute(name = "conditionState")
	private boolean conditionState;

	public ConditionLink() {
		this.link = null;
		this.condition = null;
		this.conditionState = false;
	}

	public Condition getCondition() {
		return condition;
	}

	protected void setCondition(Condition condition) {
		this.condition = condition;
	}
		
	public boolean getConditionState() {
		return conditionState;
	}
	
	protected String getLink() {
		return link;
	}
	
	protected void setLink(String link) {
		this.link = link;
	}

	@Validate
	public void validate(Map<String,Object> session) throws PersistenceException {
		if (session.isEmpty()) {
			throw new PersistenceException("Map must not be empty");
		}
	}

	@Commit
	public void commit(Map<String, Object> session) {
		Object condition = session.get(getLink());
		setCondition((Condition) condition);
	}
}
