package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.MailListUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

public interface MailListService {

	public void addUser(MailList mailList, UserAccount userAccount);

	public void removeUser(MailList mailList, UserAccount userAccount);

	public MailListUser save(MailListUser mailListUser);
	
}
