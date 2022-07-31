package com.thalasoft.learnintouch.data.dao.domain;

public class FormItemValue implements java.io.Serializable {

	private Long id;
	private int version;
	private String value;
	private String text;
	private FormItem formItem;

	public FormItemValue() {
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
