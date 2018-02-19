package com.cobo.dt.model.lfdt;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.core.Commit;

public class ConditionOccurrence extends AbstractOccurence {
	public ConditionOccurrence(
			@Attribute(name = "uId") String uid,
			@Element(required = true, name = "Symbol") Symbol symbol, 
			@Element(name = "Title") Title title,
			@Element(required = false, name ="SourceCode") SourceCode sourceCode
		) {
		super(uid, symbol, title, sourceCode);
	}
	
	
	@Commit
	public void commit(Map session) {
		session.put(getUId(), this);
	}
}
