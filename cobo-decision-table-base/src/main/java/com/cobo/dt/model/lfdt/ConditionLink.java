package com.cobo.dt.model.lfdt;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root(strict = false)
public class ConditionLink {

	@Attribute
	private String link;
	
	private Condition condition;
	
	@Attribute
	private boolean conditionState;

	public ConditionLink() {
	}

	@Validate
	public void validate(Map<String,Object> session) throws PersistenceException {
		if (session.isEmpty()) {
			throw new PersistenceException("Map must not be empty");
		}
	}

	@Commit
	public void commit(Map<String, Object> session) {
		Object condition = session.get(link);
		setCondition((Condition) condition);
	}

	protected void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	public Condition getCondition() {
		return condition;
	}
	
	public boolean isConditionState() {
		return conditionState;
	}
	
}
