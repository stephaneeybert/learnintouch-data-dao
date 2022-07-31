package com.thalasoft.learnintouch.data.dao.domain;

public class ShopCategory implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private int listOrder;
	private ShopCategory parent;

	public ShopCategory() {
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

	public ShopCategory getParent() {
		return this.parent;
	}

	public void setParent(ShopCategory parent) {
		this.parent = parent;
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

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

}
