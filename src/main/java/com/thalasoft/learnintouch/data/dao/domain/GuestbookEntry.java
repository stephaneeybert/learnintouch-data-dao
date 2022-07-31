package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class GuestbookEntry implements java.io.Serializable {

	private Long id;
	private int version;
	private String body;
	private String email;
	private String firstname;
	private String lastname;
	private UserAccount userAccount;
	private LocalDateTime publicationDatetime;

	public GuestbookEntry() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public UserAccount getUserAccount() {
		return this.userAccount;
	}
	
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	public LocalDateTime getPublicationDatetime() {
		return this.publicationDatetime;
	}
	
	public void setPublicationDatetime(LocalDateTime publicationDatetime) {
		this.publicationDatetime = publicationDatetime;
	}

}

