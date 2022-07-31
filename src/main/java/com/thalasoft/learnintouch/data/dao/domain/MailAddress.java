package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class MailAddress implements java.io.Serializable {

	private Long id;
	private int version;
	private String firstname;
	private String lastname;
	private String email;
	private String textComment;
	private String country;
	private boolean subscribe;
	private boolean imported;
	private LocalDateTime creationDatetime;

	public MailAddress() {
		this.creationDatetime = new LocalDateTime();
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTextComment() {
		return this.textComment;
	}

	public void setTextComment(String textComment) {
		this.textComment = textComment;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public LocalDateTime getCreationDatetime() {
		return this.creationDatetime;
	}

	public void setCreationDatetime(LocalDateTime creationDatetime) {
		this.creationDatetime = creationDatetime;
	}

}
