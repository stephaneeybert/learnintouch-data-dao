package com.thalasoft.learnintouch.data.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.MailListUserDao;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.MailListUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.service.MailListService;

@Transactional
public class MailListServiceImpl implements MailListService {

	@Autowired
	private MailListUserDao mailListUserDao;

	protected void setMailListUserDao(MailListUserDao mailListUserDao) {
		this.mailListUserDao = mailListUserDao;
	}

	// TODO is this making sense ?
	@Override
	public void addUser(MailList mailList, UserAccount userAccount) {
		MailListUser mailListUser = mailListUserDao.findWithMailListAndUser(mailList, userAccount);
		if (mailListUser == null) {
			mailListUser = new MailListUser(mailList, userAccount);
			mailListUser = mailListUserDao.makePersistent(mailListUser);
		}
	}
	
	// TODO is this making sense ?
	@Override
	public void removeUser(MailList mailList, UserAccount userAccount) {
		MailListUser mailListUser = mailListUserDao.findWithMailListAndUser(mailList, userAccount);
		if (mailListUser != null) {
			mailListUserDao.makeTransient(mailListUser);
		}
	}

	@Override
	public MailListUser save(MailListUser mailListUser) {
		return mailListUserDao.makePersistent(mailListUser);
	}
	
}

