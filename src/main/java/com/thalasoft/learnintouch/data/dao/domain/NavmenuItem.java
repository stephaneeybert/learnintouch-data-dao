package com.thalasoft.learnintouch.data.dao.domain;

public class NavmenuItem implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private String image;
	private String imageOver;
	private String url;
	private boolean blankTarget;
	private boolean hide;
	private int listOrder;
	private NavmenuItem parent;
	private TemplateModel templateModel;

	public NavmenuItem() {
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

	public NavmenuItem getParent() {
		return this.parent;
	}

	public void setParent(NavmenuItem parent) {
		this.parent = parent;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getHide() {
		return this.hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

}
