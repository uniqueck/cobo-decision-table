package com.cobo.dt.model.lfdt;

import java.util.List;
import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;

@Root(strict = false)
public class Action extends AbstractRulePart<ActionOccurrence> {
	public Action(@Attribute(name = "uId") String uid, @Element(required = true, name = "Title") Title title,
			@Element(required = false, name = "Text") Text text,
			@ElementList(required = false, entry = "SourceCode", inline = true) List<SourceCode> sourceCodes,
			@Path(value = "ActionOccurrences") @ElementList(required = false, inline = true, entry = "ActionOccurrence") List<ActionOccurrence> occurences) {
		super(uid, title, text, occurences, sourceCodes);
	}

	@Override
	@Path("ActionOccurrences")
	@ElementList(required = false, inline = true, entry = "ActionOccurrence")
	public List<ActionOccurrence> getOccurences() {
		return super.getOccurences();
	}

	@Commit
	public void commit(Map session) {
		session.put(getUId(), this);
	}
}
