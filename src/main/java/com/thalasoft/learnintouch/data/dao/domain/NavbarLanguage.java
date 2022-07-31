package com.thalasoft.learnintouch.data.dao.domain;

public class NavbarLanguage implements java.io.Serializable {

	private Long id;
	private int version;
	private String languageCode;
	private Navbar navbar;

	public NavbarLanguage() {
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

	public Navbar getNavbar() {
		return this.navbar;
	}

	public void setNavbar(Navbar navbar) {
		this.navbar = navbar;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
}
