package com.thalasoft.learnintouch.data.dao.domain;

public class MailListUser implements java.io.Serializable {

	private Long id;
	private int version;
	private UserAccount userAccount;
	private MailList mailList;

	public MailListUser() {
	}

	public MailListUser(MailList mailList, UserAccount userAccount) {
		this.userAccount = userAccount;
		this.mailList = mailList;
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
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public MailList getMailList() {
		return this.mailList;
	}

	public void setMailList(MailList mailList) {
		this.mailList = mailList;
	}

}
