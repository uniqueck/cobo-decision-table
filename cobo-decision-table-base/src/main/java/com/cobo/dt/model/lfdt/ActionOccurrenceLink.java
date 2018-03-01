package com.cobo.dt.model.lfdt;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;
import org.simpleframework.xml.core.PersistenceException;
import org.simpleframework.xml.core.Validate;

@Root(strict = false)
public class ActionOccurrenceLink {
	@Attribute(name = "link")
	private String link;

	private ActionOccurrence actionOccurrence;

	public ActionOccurrenceLink() {
		this.link = null;
		this.actionOccurrence = null;
	}

	public ActionOccurrence getActionOccurrence() {
		return actionOccurrence;
	}
	
	public void setActionOccurrence(ActionOccurrence actionOccurrence) {
		this.actionOccurrence = actionOccurrence;
	}

	protected String getLink() {
		return link;
	}
	
	protected void setLink(String link) {
		this.link = link;
	}
	
	@Validate
	public void validate(Map<String, Object> session) throws PersistenceException {
		if (session.isEmpty()) {
			throw new PersistenceException("Map must not be empty");
		}
	}

	@Commit
	public void commit(Map<String, Object> session) {
		Object actionOcc = session.get(getLink());
		setActionOccurrence((ActionOccurrence) actionOcc);
	}
}
