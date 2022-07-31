package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningQuestionResult implements java.io.Serializable {

	private Long id;
	private int version;
	private ElearningQuestion elearningQuestion;
	private ElearningResult elearningResult;
	private ElearningAnswer elearningAnswer;
	private String elearningAnswerText;
	private int elearningAnswerOrder;

	public ElearningQuestionResult() {
	}

	public ElearningQuestionResult(ElearningQuestion elearningQuestion, ElearningResult elearningResult, String elearningAnswerText) {
		this.elearningQuestion = elearningQuestion;
		this.elearningResult = elearningResult;
		this.elearningAnswerText = elearningAnswerText;
	}

	public ElearningQuestionResult(ElearningQuestion elearningQuestion, ElearningResult elearningResult, ElearningAnswer elearningAnswer, String elearningAnswerText) {
		this.elearningQuestion = elearningQuestion;
		this.elearningResult = elearningResult;
		this.elearningAnswer = elearningAnswer;
		this.elearningAnswerText = elearningAnswerText;
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

	public ElearningResult getElearningResult() {
		return this.elearningResult;
	}

	public void setElearningResult(ElearningResult elearningResult) {
		this.elearningResult = elearningResult;
	}

	public ElearningAnswer getElearningAnswer() {
		return this.elearningAnswer;
	}

	public void setElearningAnswer(ElearningAnswer elearningAnswer) {
		this.elearningAnswer = elearningAnswer;
	}

	public String getElearningAnswerText() {
		return this.elearningAnswerText;
	}

	public void setElearningAnswerText(String elearningAnswerText) {
		this.elearningAnswerText = elearningAnswerText;
	}

	public int getElearningAnswerOrder() {
		return elearningAnswerOrder;
	}

	public void setElearningAnswerOrder(int elearningAnswerOrder) {
		this.elearningAnswerOrder = elearningAnswerOrder;
	}

}
