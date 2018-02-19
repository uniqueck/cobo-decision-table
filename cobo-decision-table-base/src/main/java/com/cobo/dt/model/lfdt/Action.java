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
public class Action extends AbstractRulePart<ActionOccurrence> {
	public Action(
			@Attribute(name = "uId") 
			String uid, 
			
			@Element(name = "Title") 
			Title title,
			
			@Element(name = "Text", required = false) 
			Text text,
			
			@ElementList(entry = "SourceCode", required = false, inline = true) 
			List<SourceCode> sourceCodes,
			
			@Path(value = "ActionOccurrences") 
			@ElementList(entry = "ActionOccurrence", required = false, inline = true) 
			List<ActionOccurrence> occurences
		) {
		super(uid, title, text, sourceCodes, occurences);
	}

	@Override
	@Path("ActionOccurrences")
	@ElementList(entry = "ActionOccurrence", required = false, inline = true)
	public List<ActionOccurrence> getOccurences() {
		return super.getOccurences();
	}
	
	@Commit
	public void commit(Map session) {
		session.put(getUId(), this);
	}
}
