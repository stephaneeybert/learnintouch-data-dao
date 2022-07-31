package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class SmsHistory implements java.io.Serializable {

	private Long id;
	private int version;
	private String mobilePhone;
	private int nbRecipients;
	private LocalDateTime sendDatetime;
	private Sms sms;
	private Admin admin;
	private SmsList smsList;

	public SmsHistory() {
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

	public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public SmsList getSmsList() {
		return this.smsList;
	}

	public void setSmsList(SmsList smsList) {
		this.smsList = smsList;
	}

	public Sms getSms() {
		return this.sms;
	}

	public void setSms(Sms sms) {
		this.sms = sms;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public LocalDateTime getSendDatetime() {
		return this.sendDatetime;
	}

	public void setSendDatetime(LocalDateTime sendDatetime) {
		this.sendDatetime = sendDatetime;
	}

	public int getNbRecipients() {
		return this.nbRecipients;
	}

	public void setNbRecipients(int nbRecipients) {
		this.nbRecipients = nbRecipients;
	}

}
