package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class ElearningSubscription implements java.io.Serializable {

	private Long id;
	private int version;
	private UserAccount userAccount;
	private ElearningCourse elearningCourse;
	private ElearningSession elearningSession;
	private ElearningTeacher elearningTeacher;
	private ElearningClass elearningClass;
	private LocalDateTime subscriptionDate;
	private LocalDateTime subscriptionClose;
	private boolean watchLive;
	private ElearningExercise lastExercise;
    private ElearningExercisePage lastExercisePage;
    private LocalDateTime lastActive;
    private String whiteboard;

	public ElearningSubscription() {
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

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public ElearningCourse getElearningCourse() {
		return this.elearningCourse;
	}

	public void setElearningCourse(ElearningCourse elearningCourse) {
		this.elearningCourse = elearningCourse;
	}

	public ElearningSession getElearningSession() {
		return this.elearningSession;
	}

	public void setElearningSession(ElearningSession elearningSession) {
		this.elearningSession = elearningSession;
	}

	public ElearningTeacher getElearningTeacher() {
		return this.elearningTeacher;
	}

	public void setElearningTeacher(ElearningTeacher elearningTeacher) {
		this.elearningTeacher = elearningTeacher;
	}

	public ElearningClass getElearningClass() {
		return this.elearningClass;
	}

	public void setElearningClass(ElearningClass elearningClass) {
		this.elearningClass = elearningClass;
	}

	public LocalDateTime getSubscriptionDate() {
		return this.subscriptionDate;
	}

	public void setSubscriptionDate(LocalDateTime subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public LocalDateTime getSubscriptionClose() {
		return subscriptionClose;
	}

	public void setSubscriptionClose(LocalDateTime subscriptionClose) {
		this.subscriptionClose = subscriptionClose;
	}

	public boolean getWatchLive() {
		return watchLive;
	}

	public void setWatchLive(boolean watchLive) {
		this.watchLive = watchLive;
	}

    public ElearningExercise getLastExercise() {
        return lastExercise;
    }

    public void setLastExercise(ElearningExercise lastExercise) {
        this.lastExercise = lastExercise;
    }

    public ElearningExercisePage getLastExercisePage() {
        return lastExercisePage;
    }

    public void setLastExercisePage(ElearningExercisePage lastExercisePage) {
        this.lastExercisePage = lastExercisePage;
    }

    public LocalDateTime getLastActive() {
        return lastActive;
    }

    public void setLastActive(LocalDateTime lastActive) {
        this.lastActive = lastActive;
    }

    public String getWhiteboard() {
        return whiteboard;
    }

    public void setWhiteboard(String whiteboard) {
        this.whiteboard = whiteboard;
    }

}
