package com.cobo.dt.model.lfet;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class ConditionOccurence extends AbstractOccurence {
	public ConditionOccurence() {
		super(null, null, null, null);
	}

	public ConditionOccurence(@Attribute(name = "uId") String uid,
			@Element(required = true, name = "Symbol") Symbol symbol, @Element(name = "Title") Title title,
			@Element(required = false, name ="SourceCode") SourceCode sourceCode) {
		super(uid, symbol, title, sourceCode);
	}
}
