package com.thalasoft.learnintouch.data.dao.domain;

public class NewsEditor implements java.io.Serializable {

	private Long id;
	private int version;
	private Admin admin;

	public NewsEditor() {
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

}
