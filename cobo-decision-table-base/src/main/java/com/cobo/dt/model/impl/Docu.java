package com.cobo.dt.model.impl;

import com.cobo.dt.model.IDocu;

public class Docu implements IDocu {
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
