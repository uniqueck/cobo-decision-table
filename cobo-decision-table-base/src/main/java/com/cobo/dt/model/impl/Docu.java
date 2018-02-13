package com.cobo.dt.model.impl;

import org.simpleframework.xml.Element;

import com.cobo.dt.model.IDocu;

public class Docu implements IDocu {
	@Element(required = false, data = true, name = "description")
	private String description;

	public Docu() {
		this.description = "";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return getDescription();
	}
}
