package com.thalasoft.learnintouch.data.dao.domain;

public class MailListAddress implements java.io.Serializable {

	private Long id;
	private int version;
	private MailList mailList;
	private MailAddress mailAddress;

	public MailListAddress() {
	}

	public MailListAddress(MailList mailList, MailAddress mailAddress) {
		this.mailList = mailList;
		this.mailAddress = mailAddress;
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

	public MailAddress getMailAddress() {
		return this.mailAddress;
	}

	public void setMailAddress(MailAddress mailAddress) {
		this.mailAddress = mailAddress;
	}

}
