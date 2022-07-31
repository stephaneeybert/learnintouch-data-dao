package com.thalasoft.learnintouch.data.dao.domain;

public class TemplateElementLanguage implements java.io.Serializable {

	private Long id;
	private int version;
	private String languageCode;
	private int objectId;
	private TemplateElement templateElement;

	public TemplateElementLanguage() {
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

	protected String getLanguageCode() {
		return languageCode;
	}

	protected void setLanguageCode(String language) {
		this.languageCode = language;
	}

	public int getObjectId() {
		return this.objectId;
	}
	
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	
	public TemplateElement getTemplateElement() {
		return this.templateElement;
	}

	public void setTemplateElement(TemplateElement templateElement) {
		this.templateElement = templateElement;
	}

}
