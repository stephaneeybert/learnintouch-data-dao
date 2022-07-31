package com.thalasoft.learnintouch.data.dao.domain;

public class SmsListUser implements java.io.Serializable {

	private Long id;
	private int version;
	private SmsList smsList;
	private UserAccount userAccount;

	public SmsListUser() {
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

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public SmsList getSmsList() {
		return this.smsList;
	}

	public void setSmsList(SmsList smsList) {
		this.smsList = smsList;
	}

}
