package com.cobo.dt.model.lfet;

public class ActionOccurence extends AbstractOccurence {
	public ActionOccurence() {
		super(null, null, null, null);
	}
	
	public ActionOccurence(String uid, Symbol symbol, Title title, SourceCode sourceCode) {
		super(uid, symbol, title, sourceCode);
	}
}
