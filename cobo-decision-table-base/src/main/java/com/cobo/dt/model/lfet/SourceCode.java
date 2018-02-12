package com.cobo.dt.model.lfet;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(strict = false, name="SourceCode")
public class SourceCode extends AbstractValue {
	@Attribute(required = true, name = "codeLanguage")	
	private String codeLanguage;

	@Attribute(required = true, name = "sourceCodeType")	
	private String sourceCodeType;

	public SourceCode() {
		this(null, null, null);
	}
	
	public SourceCode(String codeLanguage, String sourceCodeType, String value) {
		super(value);
		this.codeLanguage = codeLanguage;
		this.sourceCodeType = sourceCodeType;
	}
	
	public String getCodeLanguage() {
		return codeLanguage;
	}
	
	public String getSourceCodeType() {
		return sourceCodeType;
	}
}
