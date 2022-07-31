package com.thalasoft.learnintouch.data.dao.domain;

public class Sms implements java.io.Serializable {

	private Long id;
	private int version;
	private String body;
	private String description;
	private Admin admin;
	private SmsCategory smsCategory;

	public Sms() {
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

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public SmsCategory getSmsCategory() {
		return this.smsCategory;
	}

	public void setSmsCategory(SmsCategory smsCategory) {
		this.smsCategory = smsCategory;
	}

}
