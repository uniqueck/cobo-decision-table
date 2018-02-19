package com.cobo.dt.model.lfdt;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.core.Commit;

public class ActionOccurrence extends AbstractOccurence {
	public ActionOccurrence(
			@Attribute(name = "uId") String uid,
			@Element(name = "Symbol") Symbol symbol, 
			@Element(name = "Title") Title title,
			@Element(name ="SourceCode", required = false) SourceCode sourceCode
			) {
		super(uid, symbol, title, sourceCode);
	}

	@Commit
	public void commit(Map session) {
		session.put(getUId(), this);
	}
}
