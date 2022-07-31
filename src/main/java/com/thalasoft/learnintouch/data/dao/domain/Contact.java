package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class Contact implements java.io.Serializable {

	private Long id;
	private int version;
	private String firstname;
	private String lastname;
	private String email;
	private String organisation;
	private String telephone;
	private String subject;
	private String message;
	private LocalDateTime contactDatetime;
	private boolean garbage;
	private ContactReferer contactReferer;
	private ContactStatus contactStatus;

	public Contact() {
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

	public ContactStatus getContactStatus() {
		return this.contactStatus;
	}

	public void setContactStatus(ContactStatus contactStatus) {
		this.contactStatus = contactStatus;
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

	public String getOrganisation() {
		return this.organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getContactDatetime() {
		return this.contactDatetime;
	}

	public void setContactDatetime(LocalDateTime contactDatetime) {
		this.contactDatetime = contactDatetime;
	}

	public ContactReferer getContactReferer() {
		return this.contactReferer;
	}

	public void setContactReferer(ContactReferer contactReferer) {
		this.contactReferer = contactReferer;
	}

	public boolean getGarbage() {
		return this.garbage;
	}

	public void setGarbage(boolean garbage) {
		this.garbage = garbage;
	}

}
