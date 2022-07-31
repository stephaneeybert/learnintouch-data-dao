package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningLessonParagraph implements java.io.Serializable {

	private Long id;
	private int version;
	private String headline;
	private String body;
	private String image;
	private String audio;
	private String video;
	private String videoUrl;
	private int listOrder;
	private ElearningLessonHeading elearningLessonHeading;
	private ElearningLesson elearningLesson;
	private ElearningExercise elearningExercise;
	private String exerciseTitle;

	public ElearningLessonParagraph() {
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

	public ElearningLessonHeading getElearningLessonHeading() {
		return this.elearningLessonHeading;
	}

	public void setElearningLessonHeading(ElearningLessonHeading elearningLessonHeading) {
		this.elearningLessonHeading = elearningLessonHeading;
	}

	public ElearningExercise getElearningExercise() {
		return this.elearningExercise;
	}

	public void setElearningExercise(ElearningExercise elearningExercise) {
		this.elearningExercise = elearningExercise;
	}

	public ElearningLesson getElearningLesson() {
		return this.elearningLesson;
	}

	public void setElearningLesson(ElearningLesson elearningLesson) {
		this.elearningLesson = elearningLesson;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public String getExerciseTitle() {
		return this.exerciseTitle;
	}

	public void setExerciseTitle(String exerciseTitle) {
		this.exerciseTitle = exerciseTitle;
	}

}
