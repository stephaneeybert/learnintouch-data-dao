package com.thalasoft.learnintouch.data.dao.domain;

public class TemplateContainer implements java.io.Serializable {

	private Long id;
	private int version;
	private int row_nb;
	private int cell_nb;
	private TemplateModel templateModel;
	private TemplatePropertySet templatePropertySet;

	public TemplateContainer() {
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

	public int getRowNb() {
		return this.row_nb;
	}

	public void setRowNb(int row_nb) {
		this.row_nb = row_nb;
	}

	public int getCellNb() {
		return this.cell_nb;
	}

	public void setCellNb(int cell_nb) {
		this.cell_nb = cell_nb;
	}

	public TemplateModel getTemplateModel() {
		return this.templateModel;
	}

	public void setTemplateModel(TemplateModel templateModel) {
		this.templateModel = templateModel;
	}

	public TemplatePropertySet getTemplatePropertySet() {
		return this.templatePropertySet;
	}

	public void setTemplatePropertySet(TemplatePropertySet templatePropertySet) {
		this.templatePropertySet = templatePropertySet;
	}

}
