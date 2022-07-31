package com.thalasoft.learnintouch.data.dao.domain;

public class NavmenuLanguage implements java.io.Serializable {

	private Long id;
	private int version;
	private String languageCode;
	private Navmenu navmenu;
	private NavmenuItem navmenuItem;

	public NavmenuLanguage() {
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

	public Navmenu getNavmenu() {
		return this.navmenu;
	}

	public void setNavmenu(Navmenu navmenu) {
		this.navmenu = navmenu;
	}

	public NavmenuItem getNavmenuItem() {
		return this.navmenuItem;
	}

	public void setNavmenuItem(NavmenuItem navmenuItem) {
		this.navmenuItem = navmenuItem;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String language) {
		this.languageCode = language;
	}

}
