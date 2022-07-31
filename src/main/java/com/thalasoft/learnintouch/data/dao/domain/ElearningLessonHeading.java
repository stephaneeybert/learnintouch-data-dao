package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningLessonHeading implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String content;
	private String image;
	private int listOrder;
	private ElearningLessonModel elearningLessonModel;

	public ElearningLessonHeading() {
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

	public ElearningLessonModel getElearningLessonModel() {
		return this.elearningLessonModel;
	}

	public void setElearningLessonModel(ElearningLessonModel elearningLessonModel) {
		this.elearningLessonModel = elearningLessonModel;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
