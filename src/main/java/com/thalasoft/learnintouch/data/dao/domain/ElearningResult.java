package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class ElearningResult implements java.io.Serializable {

	private Long id;
	private int version;
	private ElearningSubscription elearningSubscription;
	private ElearningExercise elearningExercise;
	private LocalDateTime exerciseDatetime;
	private int exerciseElapsedTime;
	private String firstname;
	private String lastname;
	private String message;
	private String textComment;
	private boolean hideComment;
	private String email;
	private int nbReadingQuestions;
	private int nbCorrectReadingAnswers;
    private int nbIncorrectReadingAnswers;
	private int nbReadingPoints;
	private int nbWritingQuestions;
	private int nbCorrectWritingAnswers;
    private int nbIncorrectWritingAnswers;
	private int nbWritingPoints;
	private int nbListeningQuestions;
    private int nbCorrectListeningAnswers;
	private int nbIncorrectListeningAnswers;
	private int nbListeningPoints;
	private int nbNotAnswered;
	private int nbIncorrectAnswers;
	
	public ElearningResult() {
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

	public ElearningSubscription getElearningSubscription() {
		return this.elearningSubscription;
	}

	public void setElearningSubscription(
			ElearningSubscription elearningSubscription) {
		this.elearningSubscription = elearningSubscription;
	}

	public ElearningExercise getElearningExercise() {
		return this.elearningExercise;
	}

    public void setElearningExercise(ElearningExercise elearningExercise) {
		this.elearningExercise = elearningExercise;
	}

	public LocalDateTime getExerciseDatetime() {
		return this.exerciseDatetime;
	}

	public void setExerciseDatetime(LocalDateTime exerciseDatetime) {
		this.exerciseDatetime = exerciseDatetime;
	}

	public int getExerciseElapsedTime() {
		return this.exerciseElapsedTime;
	}

	public void setExerciseElapsedTime(int exerciseElapsedTime) {
		this.exerciseElapsedTime = exerciseElapsedTime;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTextComment() {
		return this.textComment;
	}

	public void setTextComment(String textComment) {
		this.textComment = textComment;
	}

	public boolean getHideComment() {
		return this.hideComment;
	}

	public void setHideComment(boolean hideComment) {
		this.hideComment = hideComment;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNbReadingQuestions() {
		return this.nbReadingQuestions;
	}

	public void setNbReadingQuestions(int nbReadingQuestions) {
		this.nbReadingQuestions = nbReadingQuestions;
	}

	public int getNbCorrectReadingAnswers() {
		return this.nbCorrectReadingAnswers;
	}

	public void setNbCorrectReadingAnswers(int nbCorrectReadingAnswers) {
		this.nbCorrectReadingAnswers = nbCorrectReadingAnswers;
	}

    public int getNbIncorrectReadingAnswers() {
        return nbIncorrectReadingAnswers;
    }

    public void setNbIncorrectReadingAnswers(int nbIncorrectReadingAnswers) {
        this.nbIncorrectReadingAnswers = nbIncorrectReadingAnswers;
    }

	public int getNbReadingPoints() {
		return this.nbReadingPoints;
	}

	public void setNbReadingPoints(int nbReadingPoints) {
		this.nbReadingPoints = nbReadingPoints;
	}

	public int getNbWritingQuestions() {
		return this.nbWritingQuestions;
	}

	public void setNbWritingQuestions(int nbWritingQuestions) {
		this.nbWritingQuestions = nbWritingQuestions;
	}

	public int getNbCorrectWritingAnswers() {
		return this.nbCorrectWritingAnswers;
	}

	public void setNbCorrectWritingAnswers(int nbCorrectWritingAnswers) {
		this.nbCorrectWritingAnswers = nbCorrectWritingAnswers;
	}

    public int getNbIncorrectWritingAnswers() {
        return nbIncorrectWritingAnswers;
    }

    public void setNbIncorrectWritingAnswers(int nbIncorrectWritingAnswers) {
        this.nbIncorrectWritingAnswers = nbIncorrectWritingAnswers;
    }

	public int getNbWritingPoints() {
		return this.nbWritingPoints;
	}

	public void setNbWritingPoints(int nbWritingPoints) {
		this.nbWritingPoints = nbWritingPoints;
	}

	public int getNbListeningQuestions() {
		return this.nbListeningQuestions;
	}

	public void setNbListeningQuestions(int nbListeningQuestions) {
		this.nbListeningQuestions = nbListeningQuestions;
	}

	public int getNbCorrectListeningAnswers() {
		return this.nbCorrectListeningAnswers;
	}

	public void setNbCorrectListeningAnswers(int nbCorrectListeningAnswers) {
		this.nbCorrectListeningAnswers = nbCorrectListeningAnswers;
	}

    public int getNbIncorrectListeningAnswers() {
        return nbIncorrectListeningAnswers;
    }

    public void setNbIncorrectListeningAnswers(int nbIncorrectListeningAnswers) {
        this.nbIncorrectListeningAnswers = nbIncorrectListeningAnswers;
    }

	public int getNbListeningPoints() {
		return this.nbListeningPoints;
	}

	public void setNbListeningPoints(int nbListeningPoints) {
		this.nbListeningPoints = nbListeningPoints;
	}

	public int getNbNotAnswered() {
		return this.nbNotAnswered;
	}

	public void setNbNotAnswered(int nbNotAnswered) {
		this.nbNotAnswered = nbNotAnswered;
	}

	public int getNbIncorrectAnswers() {
		return nbIncorrectAnswers;
	}

	public void setNbIncorrectAnswers(int nbIncorrectAnswers) {
		this.nbIncorrectAnswers = nbIncorrectAnswers;
	}

}
