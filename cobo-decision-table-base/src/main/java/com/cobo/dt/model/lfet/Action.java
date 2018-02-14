package com.cobo.dt.model.lfet;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class Action extends AbstractRulePart<ActionOccurence> {
	public Action(
			@Attribute(name = "uId") 
			String uid, 
			@Element(required = true, name = "Title") 
			Title title,
			@Element(required = false, name = "Text") 
			Text text,
			@ElementList(required = false, entry = "SourceCode", inline = true) 
			List<SourceCode> sourceCodes,
			@Path(value = "ActionOccurrences") @ElementList(required = false, inline = true, entry = "ActionOccurrence") 
			List<ActionOccurence> occurences
		) {
		super(uid, title, text, occurences, sourceCodes);
	}

	@Override
	@Path("ActionOccurrences")
	@ElementList(required = false, inline = true, entry = "ActionOccurrence")
	public List<ActionOccurence> getOccurences() {
		return super.getOccurences();
	}
}
