package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.SmsList;
import com.thalasoft.learnintouch.data.dao.domain.SmsListUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

public interface SmsListUserDao extends GenericDao<SmsListUser, Serializable> {

	public long deleteBySmsList(SmsList smsList);

	public List<SmsListUser> findWithSmsList(SmsList smsList);

	public List<SmsListUser> findWithUser(UserAccount userAccount);

	public SmsListUser findWithSmsListAndUser(SmsList smsList, UserAccount userAccount);

}
