package com.thalasoft.learnintouch.data.dao.domain;

public class TemplatePageTag implements java.io.Serializable {

	private Long id;
	private int version;
	private String domTagId;
	private TemplatePropertySet templatePropertySet;
	private TemplatePage templatePage;

	public TemplatePageTag() {
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

	public TemplatePropertySet getTemplatePropertySet() {
		return this.templatePropertySet;
	}

	public void setTemplatePropertySet(TemplatePropertySet templatePropertySet) {
		this.templatePropertySet = templatePropertySet;
	}

	public TemplatePage getTemplatePage() {
		return this.templatePage;
	}

	public void setTemplatePage(TemplatePage templatePage) {
		this.templatePage = templatePage;
	}

	public String getDomTagId() {
		return this.domTagId;
	}

	public void setDomTagId(String domTagId) {
		this.domTagId = domTagId;
	}

}
