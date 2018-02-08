package com.cobo.dt.ui;

import java.util.List;

public class RuleNamesHeaderRow {
	private List<String> ruleNames;
	
	public RuleNamesHeaderRow(List<String> ruleNames) {
		this.ruleNames = ruleNames;
	}
	
	public List<String> getRuleNames() {
		return ruleNames;
	}
	
	public String getRuleName(int index) {
		return getRuleNames().get(index);
	}
}
