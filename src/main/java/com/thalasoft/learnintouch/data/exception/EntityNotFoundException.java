package com.thalasoft.learnintouch.data.exception;

@SuppressWarnings("serial")
public class EntityNotFoundException extends EnrichableException {

	public EntityNotFoundException() {
		super("The entity was not found.");
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

}