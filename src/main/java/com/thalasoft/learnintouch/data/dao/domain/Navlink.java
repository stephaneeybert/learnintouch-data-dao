package com.thalasoft.learnintouch.data.dao.domain;

public class Navlink implements java.io.Serializable {

	private Long id;
	private int version;

	public Navlink() {
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
	
}
