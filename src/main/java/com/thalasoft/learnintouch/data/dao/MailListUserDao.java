package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.MailListUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

public interface MailListUserDao extends GenericDao<MailListUser, Serializable> {

	public long  deleteByMailList(MailList mailList);
	public List<MailListUser> findWithMailList(MailList mailList);
    public List<MailListUser> findWithMailListAndSubscriberLikePattern(MailList mailList, String searchPattern);
	public List<MailListUser> findWithUser(UserAccount userAccount);
	public MailListUser findWithMailListAndUser(MailList mailList, UserAccount userAccount);

}
