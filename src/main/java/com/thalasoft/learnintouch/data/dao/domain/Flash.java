package com.thalasoft.learnintouch.data.dao.domain;

public class Flash implements java.io.Serializable {

	private Long id;
	private int version;
	private String filename;
	private String width;
	private String height;
	private String bgcolor;
	private String wddx;

	public Flash() {
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

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getWidth() {
		return this.width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getBgcolor() {
		return this.bgcolor;
	}

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getWddx() {
		return this.wddx;
	}

	public void setWddx(String wddx) {
		this.wddx = wddx;
	}

}
