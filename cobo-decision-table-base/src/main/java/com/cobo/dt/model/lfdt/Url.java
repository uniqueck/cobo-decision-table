package com.cobo.dt.model.lfdt;

import org.simpleframework.xml.Attribute;

public class Url {
	@Attribute
	private String title;
	
	@Attribute
	private String url;
	
	@Attribute(required = false)
	private Boolean executable;
	
	public Url(String title, String url) {
		this(title, url, null);
	}
	
	public Url(String title, String url, Boolean executable) {
		this.title = title;
		this.url = url;
		this.executable = executable;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getUrl() {
		return url;
	}
	
	public boolean isExecutable() {
		return executable==null ? false : executable;
	}
}
