package com.cobo.dt.model.lfdt;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root(strict = false)
public class ActionOccurrenceLink {

	@Attribute
	private String link;

	private ActionOccurrence actionOccurrence;

	public ActionOccurrenceLink() {
	}

	@Validate
	public void validate(Map<String, Object> session) throws PersistenceException {
		if (session.isEmpty()) {
			throw new PersistenceException("Map must not be empty");
		}
	}

	@Commit
	public void commit(Map<String, Object> session) {
		Object actionOcc = session.get(link);
		setActionOccurrence((ActionOccurrence) actionOcc);
	}

	public void setActionOccurrence(ActionOccurrence actionOccurrence) {
		this.actionOccurrence = actionOccurrence;
	}
	
	public ActionOccurrence getActionOccurrence() {
		return actionOccurrence;
	}
}
