package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningSessionCourse implements java.io.Serializable {

	private Long id;
	private int version;
	private ElearningSession elearningSession;
	private ElearningCourse elearningCourse;

	public ElearningSessionCourse() {
	}

	public ElearningSessionCourse(ElearningSession elearningSession, ElearningCourse elearningCourse) {
		this.elearningSession = elearningSession;
		this.elearningCourse = elearningCourse;
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

	public ElearningSession getElearningSession() {
		return this.elearningSession;
	}

	public void setElearningSession(ElearningSession elearningSession) {
		this.elearningSession = elearningSession;
	}

	public ElearningCourse getElearningCourse() {
		return this.elearningCourse;
	}

	public void setElearningCourse(ElearningCourse elearningCourse) {
		this.elearningCourse = elearningCourse;
	}

}
