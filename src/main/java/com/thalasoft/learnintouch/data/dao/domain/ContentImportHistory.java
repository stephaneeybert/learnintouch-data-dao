package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class ContentImportHistory implements java.io.Serializable {

	private Long id;
	private int version;
	private String domainName;
	private String course;
	private String lesson;
	private String exercise;
	private LocalDateTime importDatetime;

	public ContentImportHistory() {
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

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getLesson() {
		return this.lesson;
	}

	public void setLesson(String lesson) {
		this.lesson = lesson;
	}

	public String getExercise() {
		return this.exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public LocalDateTime getImportDatetime() {
		return this.importDatetime;
	}

	public void setImportDatetime(LocalDateTime importDatetime) {
		this.importDatetime = importDatetime;
	}

}
