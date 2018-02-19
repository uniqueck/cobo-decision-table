package com.cobo.dt.model.lfdt;

import java.util.Map;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.core.Commit;

public class ConditionOccurrence extends AbstractOccurence {

	private Condition condition;

	public ConditionOccurrence(@Attribute(name = "uId") String uid, @Element(name = "Symbol") Symbol symbol,
			@Element(name = "Title") Title title,
			@Element(name = "SourceCode", required = false) SourceCode sourceCode) {
		super(uid, symbol, title, sourceCode);
		this.condition = null;
	}

	protected void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Condition getCondition() {
		return condition;
	}

	@Commit
	public void commit(Map<String, Object> session) {
		session.put(getUId(), this);
	}
}
