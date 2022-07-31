package com.thalasoft.learnintouch.data.dao.domain;

public class MailOutbox implements java.io.Serializable {

	private Long id;
	private int version;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private boolean sent;
	private String metaNames;

	public MailOutbox() {
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getSent() {
		return this.sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	public String getMetaNames() {
		return this.metaNames;
	}

	public void setMetaNames(String metaNames) {
		this.metaNames = metaNames;
	}

}
