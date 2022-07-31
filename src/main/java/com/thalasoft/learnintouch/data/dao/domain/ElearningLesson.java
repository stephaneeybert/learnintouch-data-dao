package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class ElearningLesson implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private String instructions;
	private String image;
	private String audio;
	private String introduction;
	private LocalDateTime releaseDate;
	private boolean secured;
	private boolean publicAccess;
	private boolean garbage;
	private ElearningLevel elearningLevel;
	private ElearningLessonModel elearningLessonModel;
	private ElearningSubject elearningSubject;

	public ElearningLesson() {
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

	public ElearningLevel getElearningLevel() {
		return this.elearningLevel;
	}

	public void setElearningLevel(ElearningLevel elearningLevel) {
		this.elearningLevel = elearningLevel;
	}

	public ElearningLessonModel getElearningLessonModel() {
		return this.elearningLessonModel;
	}

	public void setElearningLessonModel(ElearningLessonModel elearningLessonModel) {
		this.elearningLessonModel = elearningLessonModel;
	}

	public ElearningSubject getElearningSubject() {
		return this.elearningSubject;
	}

	public void setElearningSubject(ElearningSubject elearningSubject) {
		this.elearningSubject = elearningSubject;
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

	public String getInstructions() {
		return this.instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAudio() {
		return this.audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public boolean getSecured() {
		return this.secured;
	}

	public void setSecured(boolean secured) {
		this.secured = secured;
	}

	public boolean getPublicAccess() {
		return this.publicAccess;
	}

	public void setPublicAccess(boolean publicAccess) {
		this.publicAccess = publicAccess;
	}

	public LocalDateTime getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean getGarbage() {
		return this.garbage;
	}

	public void setGarbage(boolean garbage) {
		this.garbage = garbage;
	}

}
