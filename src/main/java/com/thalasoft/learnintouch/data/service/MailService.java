package com.thalasoft.learnintouch.data.service;

import org.joda.time.LocalDateTime;

public interface MailService {

	public long deleteByDate(LocalDateTime sinceDate);
	
}
