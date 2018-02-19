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
public class Condition extends AbstractRulePart<ConditionOccurrence> {
	public Condition(@Attribute(name = "uId") String uid,

			@Element(name = "Title") Title title,

			@Element(name = "Text", required = false) Text text,

			@ElementList(entry = "SourceCode", required = false, inline = true) List<SourceCode> sourceCodes,

			@Path(value = "ConditionOccurrences") @ElementList(entry = "ConditionOccurrence", required = false, inline = true) List<ConditionOccurrence> occurences) {
		super(uid, title, text, sourceCodes, occurences);
	}

	@Override
	@Path(value = "ConditionOccurrences")
	@ElementList(entry = "ConditionOccurrence", required = false, inline = true)
	public List<ConditionOccurrence> getOccurences() {
		return super.getOccurences();
	}

	@Commit
	public void commit(Map<String, Object> session) {
		session.put(getUId(), this);
		if (getOccurences() != null) {
			for (ConditionOccurrence eachOcc : getOccurences()) {
				eachOcc.setCondition(this);
			}
		}
	}
}
