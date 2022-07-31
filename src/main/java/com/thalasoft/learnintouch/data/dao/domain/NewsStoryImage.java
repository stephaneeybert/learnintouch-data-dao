package com.thalasoft.learnintouch.data.dao.domain;

public class NewsStoryImage implements java.io.Serializable {

	private Long id;
	private int version;
	private String image;
	private String description;
	private int listOrder;
	private NewsStory newsStory;

	public NewsStoryImage() {
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

	public NewsStory getNewsStory() {
		return this.newsStory;
	}

	public void setNewsStory(NewsStory newsStory) {
		this.newsStory = newsStory;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
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
