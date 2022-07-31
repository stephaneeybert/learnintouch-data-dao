package com.thalasoft.learnintouch.data.dao.domain;

public class Navbar implements java.io.Serializable {

	private Long id;
	private int version;
	private boolean hide;

	public Navbar() {
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

	public boolean getHide() {
		return this.hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
}
