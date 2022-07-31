package com.thalasoft.learnintouch.data.dao.domain;

import org.joda.time.LocalDateTime;

public class ElearningAssignment implements java.io.Serializable {

	private Long id;
	private int version;
	private ElearningSubscription elearningSubscription;
	private ElearningExercise elearningExercise;
	private ElearningResult elearningResult;
	private boolean onlyOnce;
	private LocalDateTime openingDate;
	private LocalDateTime closingDate;

	public ElearningAssignment() {
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
		return elearningSubscription;
	}

	public void setElearningSubscription(ElearningSubscription elearningSubscription) {
		this.elearningSubscription = elearningSubscription;
	}

	public ElearningExercise getElearningExercise() {
		return elearningExercise;
	}

	public void setElearningExercise(ElearningExercise elearningExercise) {
		this.elearningExercise = elearningExercise;
	}

	public ElearningResult getElearningResult() {
		return elearningResult;
	}

	public void setElearningResult(ElearningResult elearningResult) {
		this.elearningResult = elearningResult;
	}

	public boolean isOnlyOnce() {
		return onlyOnce;
	}

	public void setOnlyOnce(boolean onlyOnce) {
		this.onlyOnce = onlyOnce;
	}

	public LocalDateTime getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDateTime openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDateTime getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDateTime closingDate) {
		this.closingDate = closingDate;
	}

}
