package com.thalasoft.learnintouch.data.service.dao;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.MailDao;
import com.thalasoft.learnintouch.data.service.MailService;

@Transactional
public class MailServiceImpl implements MailService {

	@Autowired
	private MailDao mailDao;

	protected void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
	}

	@Override
	public long deleteByDate(LocalDateTime sinceDate) {
		// Only the date is considered
		// The time must must be ignored
		// TODO extract the date
		return mailDao.deleteByDate(sinceDate);
	}

}
