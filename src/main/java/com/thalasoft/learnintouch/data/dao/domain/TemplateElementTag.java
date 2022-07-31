package com.thalasoft.learnintouch.data.dao.domain;

public class TemplateElementTag implements java.io.Serializable {

	private Long id;
	private int version;
	private String domTagId;
	private TemplateElement templateElement;
	private TemplatePropertySet templatePropertySet;

	public TemplateElementTag() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDomTagId() {
		return this.domTagId;
	}
	
	public void setDomTagId(String domTagId) {
		this.domTagId = domTagId;
	}
	
	public TemplateElement getTemplateElement() {
		return this.templateElement;
	}
	
	public void setTemplateElement(TemplateElement templateElement) {
		this.templateElement = templateElement;
	}
	
	public TemplatePropertySet getTemplatePropertySet() {
		return this.templatePropertySet;
	}

	public void setTemplatePropertySet(TemplatePropertySet templatePropertySet) {
		this.templatePropertySet = templatePropertySet;
	}

}
