package com.thalasoft.learnintouch.data.dao.domain;

public class NavlinkItem implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private String image;
	private String imageOver;
	private String url;
	private boolean blankTarget;
	private String languageCode;
	private Navlink navlink;
	private TemplateModel templateModel;

	public NavlinkItem() {
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

	public Navlink getNavlink() {
		return this.navlink;
	}

	public void setNavlink(Navlink navlink) {
		this.navlink = navlink;
	}

	public TemplateModel getTemplateModel() {
		return this.templateModel;
	}

	public void setTemplateModel(TemplateModel templateModel) {
		this.templateModel = templateModel;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageOver() {
		return this.imageOver;
	}

	public void setImageOver(String imageOver) {
		this.imageOver = imageOver;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean getBlankTarget() {
		return this.blankTarget;
	}

	public void setBlankTarget(boolean blankTarget) {
		this.blankTarget = blankTarget;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String language) {
		this.languageCode = language;
	}

}
