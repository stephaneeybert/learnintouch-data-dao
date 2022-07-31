package com.thalasoft.learnintouch.data.dao.domain;

public class Webpage implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private String content;
	private boolean hide;
	private boolean garbage;
	private int listOrder;
	private boolean secured;
	private Webpage parent;
	private Admin admin;

	public Webpage() {
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getHide() {
		return this.hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public boolean getGarbage() {
		return this.garbage;
	}

	public void setGarbage(boolean garbage) {
		this.garbage = garbage;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public boolean isSecured() {
		return secured;
	}

	public void setSecured(boolean secured) {
		this.secured = secured;
	}

	public Webpage getParent() {
		return parent;
	}

	public void setParent(Webpage parent) {
		this.parent = parent;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
