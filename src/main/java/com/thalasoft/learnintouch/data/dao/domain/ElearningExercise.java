package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class ElearningExercise implements java.io.Serializable {

	private Long id;
	private int version;
	private String name;
	private String description;
	private String instructions;
	private String introduction;
	private boolean hideIntroduction;
	private String image;
	private String audio;
	private int maxDuration;
	private LocalDateTime releaseDate;
	private String webpageId;
	private boolean secured;
	private boolean publicAccess;
	private boolean skipExerciseIntroduction;
	private boolean socialConnect;
	private boolean hideSolutions;
	private boolean hideProgressionBar;
	private boolean hidePageTabs;
	private boolean disableNextPageTabs;
	private int numberPageTabs;
	private boolean hideKeyboard;
	private boolean contactPage;
	private boolean locked;
	private boolean garbage;
	private ElearningLevel elearningLevel;
	private ElearningScoring elearningScoring;
	private ElearningSubject elearningSubject;
	private ElearningCategory elearningCategory;

	public ElearningExercise() {
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

	public ElearningScoring getElearningScoring() {
		return this.elearningScoring;
	}

	public void setElearningScoring(ElearningScoring elearningScoring) {
		this.elearningScoring = elearningScoring;
	}

	public ElearningSubject getElearningSubject() {
		return this.elearningSubject;
	}

	public void setElearningSubject(ElearningSubject elearningSubject) {
		this.elearningSubject = elearningSubject;
	}

	public ElearningCategory getElearningCategory() {
		return this.elearningCategory;
	}

	public void setElearningCategory(ElearningCategory elearningCategory) {
		this.elearningCategory = elearningCategory;
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

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public boolean getHideIntroduction() {
		return this.hideIntroduction;
	}

	public void setHideIntroduction(boolean hideIntroduction) {
		this.hideIntroduction = hideIntroduction;
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

	public boolean getPublicAccess() {
		return this.publicAccess;
	}

	public void setPublicAccess(boolean publicAccess) {
		this.publicAccess = publicAccess;
	}

	public int getMaxDuration() {
		return this.maxDuration;
	}

	public void setMaxDuration(int maxDuration) {
		this.maxDuration = maxDuration;
	}

	public LocalDateTime getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean getSecured() {
		return this.secured;
	}

	public void setSecured(boolean secured) {
		this.secured = secured;
	}

	public boolean getSkipExerciseIntroduction() {
		return this.skipExerciseIntroduction;
	}

	public void setSkipExerciseIntroduction(boolean skipExerciseIntroduction) {
		this.skipExerciseIntroduction = skipExerciseIntroduction;
	}

	public boolean getSocialConnect() {
		return this.socialConnect;
	}

	public void setSocialConnect(boolean socialConnect) {
		this.socialConnect = socialConnect;
	}

	public boolean getHideSolutions() {
		return this.hideSolutions;
	}

	public void setHideSolutions(boolean hideSolutions) {
		this.hideSolutions = hideSolutions;
	}

	public boolean getHideProgressionBar() {
		return this.hideProgressionBar;
	}

	public void setHideProgressionBar(boolean hideProgressionBar) {
		this.hideProgressionBar = hideProgressionBar;
	}

	public boolean getHidePageTabs() {
		return this.hidePageTabs;
	}

	public void setHidePageTabs(boolean hidePageTabs) {
		this.hidePageTabs = hidePageTabs;
	}

	public boolean getDisableNextPageTabs() {
		return this.disableNextPageTabs;
	}

	public void setDisableNextPageTabs(boolean disableNextPageTabs) {
		this.disableNextPageTabs = disableNextPageTabs;
	}

	public int getNumberPageTabs() {
		return this.numberPageTabs;
	}

	public void setNumberPageTabs(int numberPageTabs) {
		this.numberPageTabs = numberPageTabs;
	}

	public boolean getHideKeyboard() {
		return this.hideKeyboard;
	}

	public boolean isContactPage() {
		return contactPage;
	}

	public void setContactPage(boolean contactPage) {
		this.contactPage = contactPage;
	}

	public void setHideKeyboard(boolean hideKeyboard) {
		this.hideKeyboard = hideKeyboard;
	}

	public String getWebpageId() {
		return this.webpageId;
	}

	public void setWebpageId(String webpageId) {
		this.webpageId = webpageId;
	}

	public boolean getGarbage() {
		return this.garbage;
	}

	public void setGarbage(boolean garbage) {
		this.garbage = garbage;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}
