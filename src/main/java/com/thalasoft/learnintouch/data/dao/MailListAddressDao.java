package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.MailAddress;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.MailListAddress;

public interface MailListAddressDao extends GenericDao<MailListAddress, Serializable> {

	public long deleteByMailList(MailList mailList);

	public List<MailListAddress> findWithMailList(MailList mailList);

    public List<MailListAddress> findWithMailListAndSubscriberLikePattern(MailList mailList, String searchPattern);

	public List<MailListAddress> findWithMailAddress(MailAddress mailAddress);

	public MailListAddress findWithMailListAndMailAddress(MailList mailList, MailAddress mailAddress);

}
