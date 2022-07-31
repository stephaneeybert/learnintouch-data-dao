package com.thalasoft.learnintouch.data.dao.domain;

public class ElearningExercisePage implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private String instructions;
	private String text;
	private String image;
	private String audio;
	private String video;
	private String videoUrl;
	private String questionType;
	private String hintPlacement;
	private boolean hideText;
	private int listOrder;
	private ElearningExercise elearningExercise;

	public ElearningExercisePage() {
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

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getHideText() {
		return this.hideText;
	}

	public void setHideText(boolean hideText) {
		this.hideText = hideText;
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

	public String getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getHintPlacement() {
		return this.hintPlacement;
	}

	public void setHintPlacement(String hintPlacement) {
		this.hintPlacement = hintPlacement;
	}

	public int getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

}
