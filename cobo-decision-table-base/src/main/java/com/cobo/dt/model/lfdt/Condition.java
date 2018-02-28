package com.cobo.dt.model.lfdt;

import java.util.List;
import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;

@Root
public class Condition extends AbstractRulePart<ConditionOccurrence> {
	public Condition(
			@Attribute(name = "uId") 
			String uid,
			
			@Element(name = "Title") 
			Title title,
			
			@Element(name = "Text", required = false) 
			Text text,
			
			@ElementList(entry = "SourceCode", required = false, inline = true) 
			List<SourceCode> sourceCodes,

			@Path(value = "ConditionOccurrences") 
			@ElementList(entry = "ConditionOccurrence", required = false, inline = true) 
			List<ConditionOccurrence> occurences,
			
			@Path(value = "UrlsOut") 
			@ElementList(entry = "Url", required = false, inline = true)
			List<Url> urls
		) {
		super(uid, title, text, sourceCodes, occurences, urls);
	}

	@Override
	@Path(value = "ConditionOccurrences")
	@ElementList(entry = "ConditionOccurrence", required = false, inline = true)
	public List<ConditionOccurrence> getOccurrences() {
		return super.getOccurrences();
	}

	@Commit
	public void commit(Map<String, Object> session) {
		session.put(getUId(), this);
		if (getOccurrences() != null) {
			for (ConditionOccurrence eachOcc : getOccurrences()) {
				eachOcc.setCondition(this);
			}
		}
	}
}
