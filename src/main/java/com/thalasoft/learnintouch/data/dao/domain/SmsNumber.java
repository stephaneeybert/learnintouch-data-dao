package com.thalasoft.learnintouch.data.dao.domain;

public class SmsNumber implements java.io.Serializable {

	private Long id;
	private int version;
	private String firstname;
	private String lastname;
	private String mobilePhone;
	private boolean subscribe;
	private boolean imported;

	public SmsNumber() {
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

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public boolean getSubscribe() {
		return this.subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	public boolean getImported() {
		return this.imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

}
