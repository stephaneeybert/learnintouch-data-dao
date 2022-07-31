package com.thalasoft.learnintouch.data.dao.domain;

public class FormValid implements java.io.Serializable {

	private Long id;
	private int version;
	private String type;
	private String message;
	private String boundary;
	private FormItem formItem;

	public FormValid() {
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

	public FormItem getFormItem() {
		return this.formItem;
	}

	public void setFormItem(FormItem formItem) {
		this.formItem = formItem;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBoundary() {
		return this.boundary;
	}

	public void setBoundary(String boundary) {
		this.boundary = boundary;
	}

}
