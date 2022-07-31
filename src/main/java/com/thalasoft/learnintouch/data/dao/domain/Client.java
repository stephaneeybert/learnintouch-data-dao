package com.thalasoft.learnintouch.data.dao.domain;

public class Client implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private String image;
	private String url;
	private int listOrder;

	public Client() {
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

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

    public int getListOrder() {
      return listOrder;
    }

    public void setListOrder(int listOrder) {
      this.listOrder = listOrder;
    }

}
