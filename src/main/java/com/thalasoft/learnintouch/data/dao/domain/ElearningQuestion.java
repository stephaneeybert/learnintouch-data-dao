package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningQuestion implements java.io.Serializable {

	private Long id;
	private int version;
	private String question;
	private String explanation;
	private String image;
	private String audio;
	private String hint;
	private int points;
	private int listOrder;
	private ElearningExercisePage elearningExercisePage;

	public ElearningQuestion() {
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

	public ElearningExercisePage getElearningExercisePage() {
		return this.elearningExercisePage;
	}

	public void setElearningExercisePage(ElearningExercisePage elearningExercisePage) {
		this.elearningExercisePage = elearningExercisePage;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getExplanation() {
		return this.explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
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

	public String getHint() {
		return this.hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

}
