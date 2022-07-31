package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningSolution implements java.io.Serializable {

	private Long id;
	private int version;
	private ElearningQuestion elearningQuestion;
	private ElearningAnswer elearningAnswer;

	public ElearningSolution() {
	}

	public ElearningSolution(ElearningQuestion elearningQuestion, ElearningAnswer elearningAnswer) {
		this.elearningQuestion = elearningQuestion;
		this.elearningAnswer = elearningAnswer;
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

	public ElearningAnswer getElearningAnswer() {
		return this.elearningAnswer;
	}

	public void setElearningAnswer(ElearningAnswer elearningAnswer) {
		this.elearningAnswer = elearningAnswer;
	}

}
