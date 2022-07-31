package com.thalasoft.learnintouch.data.dao.domain;

public class TemplateElement implements java.io.Serializable {

	private Long id;
	private int version;
	private String elementType;
	private int listOrder;
	private boolean hide;
	private int objectId;
	private TemplateContainer templateContainer;

	public TemplateElement() {
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

	public String getElementType() {
		return this.elementType;
	}
	
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	
	public int getListOrder() {
		return this.listOrder;
	}
	
	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}
	
	public boolean getHide() {
		return this.hide;
	}
	
	public void setHide(boolean hide) {
		this.hide = hide;
	}
	
	public int getObjectId() {
		return this.objectId;
	}
	
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	
	public TemplateContainer getTemplateContainer() {
		return this.templateContainer;
	}

	public void setTemplateContainer(TemplateContainer templateContainer) {
		this.templateContainer = templateContainer;
	}

}
