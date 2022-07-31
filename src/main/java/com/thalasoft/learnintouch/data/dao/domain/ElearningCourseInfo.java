package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningCourseInfo implements java.io.Serializable {

	private Long id;
	private int version;
	private String headline;
	private String information;
	private int listOrder;
	private ElearningCourse elearningCourse;

	public ElearningCourseInfo() {
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

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getListOrder() {
		return listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public ElearningCourse getElearningCourse() {
		return elearningCourse;
	}

	public void setElearningCourse(ElearningCourse elearningCourse) {
		this.elearningCourse = elearningCourse;
	}

}
