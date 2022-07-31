package com.thalasoft.learnintouch.data.dao.domain;

public class WebpageNavmenu implements java.io.Serializable {

	private Long id;
	private int version;
	private WebpageNavmenu parent;

	public WebpageNavmenu() {
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

	public WebpageNavmenu getParent() {
		return parent;
	}

	public void setParent(WebpageNavmenu parent) {
		this.parent = parent;
	}

}
