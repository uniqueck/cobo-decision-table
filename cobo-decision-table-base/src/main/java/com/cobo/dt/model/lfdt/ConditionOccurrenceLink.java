package com.cobo.dt.model.lfdt;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root(strict = false)
public class ConditionOccurrenceLink {
	@Attribute
	private String link;

	private ConditionOccurrence conditionOccurrence;

	public ConditionOccurrenceLink() {
	}

	public ConditionOccurrence getConditionOccurrence() {
		return conditionOccurrence;
	}
	
	public void setConditionOccurrence(ConditionOccurrence conditionOccurence) {
		this.conditionOccurrence = conditionOccurence;
	}
	
	protected String getLink() {
		return link;
	}

	@Validate
	public void validate(Map<String, Object> session) throws PersistenceException {
		if (session.isEmpty()) {
			throw new PersistenceException("Map must not be empty");
		}
	}

	@Commit
	public void commit(Map<String, Object> session) {
		Object condition = session.get(getLink());
		setConditionOccurrence((ConditionOccurrence) condition);
	}
}
