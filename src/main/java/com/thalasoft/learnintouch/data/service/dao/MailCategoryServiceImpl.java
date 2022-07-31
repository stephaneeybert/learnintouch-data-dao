package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.MailCategoryDao;
import com.thalasoft.learnintouch.data.dao.MailDao;
import com.thalasoft.learnintouch.data.dao.domain.Mail;
import com.thalasoft.learnintouch.data.dao.domain.MailCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.MailCategoryService;

@Transactional
public class MailCategoryServiceImpl implements MailCategoryService {

	@Autowired
	private MailDao mailDao;
	
	@Autowired
	private MailCategoryDao mailCategoryDao;

	protected void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
	}

	protected void setMailCategoryDao(MailCategoryDao mailCategoryDao) {
		this.mailCategoryDao = mailCategoryDao;
	}

	@Override
	public MailCategory save(MailCategory mailCategory) {
		return mailCategoryDao.makePersistent(mailCategory);
	}

	@Override
	public boolean isNotUsedByAnyMail(MailCategory mailCategory) {
		Page<Mail> page = mailDao.findWithCategory(mailCategory, 0, 0);
		List<Mail> mails = page.getPageItems();
		return mails.size() == 0;
	}
	
}
