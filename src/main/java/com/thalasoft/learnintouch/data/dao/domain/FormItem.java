package com.thalasoft.learnintouch.data.dao.domain;

public class FormItem implements java.io.Serializable {

	private Long id;
	private int version;
	private String type;
	private String name;
	private String text;
	private String help;
	private String defaultValue;
	private String itemSize;
	private String maxlength;
	private boolean inMailAddress;
	private int listOrder;
	private MailList mailList;
	private Form form;

	public FormItem() {
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

	public MailList getMailList() {
		return this.mailList;
	}

	public void setMailList(MailList mailList) {
		this.mailList = mailList;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHelp() {
		return this.help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getItemSize() {
		return this.itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public String getMaxlength() {
		return this.maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public boolean getInMailAddress() {
		return this.inMailAddress;
	}

	public void setInMailAddress(boolean inMailAddress) {
		this.inMailAddress = inMailAddress;
	}

}
