package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningAnswer implements java.io.Serializable {

	private Long id;
	private int version;
	private String answer;
	private String explanation;
	private String image;
	private String audio;
	private int listOrder;
	private ElearningQuestion elearningQuestion;

	public ElearningAnswer() {
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

	public ElearningQuestion getElearningQuestion() {
		return this.elearningQuestion;
	}

	public void setElearningQuestion(ElearningQuestion elearningQuestion) {
		this.elearningQuestion = elearningQuestion;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

}
