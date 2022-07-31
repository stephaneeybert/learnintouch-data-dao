package com.thalasoft.learnintouch.data.dao.domain;

public class TemplatePage implements java.io.Serializable {

	private Long id;
	private int version;
	private String systemPage;
	private TemplateModel templateModel;

	public TemplatePage() {
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

	public String getSystemPage() {
		return this.systemPage;
	}
	
	public void setSystemPage(String systemPage) {
		this.systemPage = systemPage;
	}
	
	public TemplateModel getTemplateModel() {
		return this.templateModel;
	}

	public void setTemplateModel(TemplateModel templateModel) {
		this.templateModel = templateModel;
	}

}
