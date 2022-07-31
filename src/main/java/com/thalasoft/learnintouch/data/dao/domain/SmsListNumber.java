package com.thalasoft.learnintouch.data.dao.domain;

public class SmsListNumber implements java.io.Serializable {

	private Long id;
	private int version;
	private SmsList smsList;
	private SmsNumber smsNumber;

	public SmsListNumber() {
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

	public SmsNumber getSmsNumber() {
		return this.smsNumber;
	}

	public void setSmsNumber(SmsNumber smsNumber) {
		this.smsNumber = smsNumber;
	}

	public SmsList getSmsList() {
		return this.smsList;
	}

	public void setSmsList(SmsList smsList) {
		this.smsList = smsList;
	}

}
