package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningScoringRange implements java.io.Serializable {

	private Long id;
	private int version;
	private int upperRange;
	private String score;
	private String advice;
	private String proposal;
	private String linkText;
	private String linkUrl;
	private ElearningScoring elearningScoring;

	public ElearningScoringRange() {
	}

	public ElearningScoringRange(ElearningScoring elearningScoring, int upperRange, String score, String advice, String proposal, String linkText, String linkUrl) {
		this.elearningScoring = elearningScoring;
		this.upperRange = upperRange;
		this.score = score;
		this.advice = advice;
		this.proposal = proposal;
		this.linkText = linkText;
		this.linkUrl = linkUrl;
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

	public ElearningScoring getElearningScoring() {
		return this.elearningScoring;
	}

	public void setElearningScoring(ElearningScoring elearningScoring) {
		this.elearningScoring = elearningScoring;
	}

	public int getUpperRange() {
		return this.upperRange;
	}

	public void setUpperRange(int upperRange) {
		this.upperRange = upperRange;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getProposal() {
		return this.proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public String getLinkText() {
		return this.linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

}
