package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Sms;
import com.thalasoft.learnintouch.data.dao.domain.SmsHistory;
import com.thalasoft.learnintouch.data.dao.domain.SmsList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface SmsHistoryDao extends GenericDao<SmsHistory, Serializable> {

	public long deleteAll();
	
	public Page<SmsHistory> findWithAdmin(Admin admin, int pageNumber, int pageSize);
	
	public Page<SmsHistory> findAll(int pageNumber, int pageSize);
	
	public Page<SmsHistory> findWithSmsList(SmsList smsList, int pageNumber, int pageSize);

	public Page<SmsHistory> findWithSms(Sms sms, int pageNumber, int pageSize);

	public Page<SmsHistory> findWithPatternLike(String pattern, int pageNumber, int pageSize);

}
