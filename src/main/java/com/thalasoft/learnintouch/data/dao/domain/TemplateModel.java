package com.thalasoft.learnintouch.data.dao.domain;

public class TemplateModel implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private String modelType;
	private TemplatePropertySet templatePropertySet;
	private TemplatePropertySet innerTemplatePropertySet;

	public TemplateModel() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModelType() {
		return this.modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public TemplatePropertySet getTemplatePropertySet() {
		return this.templatePropertySet;
	}

	public void setTemplatePropertySet(TemplatePropertySet templatePropertySet) {
		this.templatePropertySet = templatePropertySet;
	}

	public TemplatePropertySet getInnerTemplatePropertySet() {
		return this.innerTemplatePropertySet;
	}

	public void setInnerTemplatePropertySet(TemplatePropertySet innerTemplatePropertySet) {
		this.innerTemplatePropertySet = innerTemplatePropertySet;
	}

}
