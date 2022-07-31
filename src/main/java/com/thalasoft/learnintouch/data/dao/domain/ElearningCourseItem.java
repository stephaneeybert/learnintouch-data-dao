package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningCourseItem implements java.io.Serializable {

	private Long id;
	private int version;
	private int listOrder;
	private ElearningLesson elearningLesson;
	private ElearningExercise elearningExercise;
	private ElearningCourse elearningCourse;

	public ElearningCourseItem() {
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

	public ElearningExercise getElearningExercise() {
		return this.elearningExercise;
	}

	public void setElearningExercise(ElearningExercise elearningExercise) {
		this.elearningExercise = elearningExercise;
	}

	public ElearningCourse getElearningCourse() {
		return this.elearningCourse;
	}

	public void setElearningCourse(ElearningCourse elearningCourse) {
		this.elearningCourse = elearningCourse;
	}

	public ElearningLesson getElearningLesson() {
		return this.elearningLesson;
	}

	public void setElearningLesson(ElearningLesson elearningLesson) {
		this.elearningLesson = elearningLesson;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

}
