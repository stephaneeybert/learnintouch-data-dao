package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Sms;
import com.thalasoft.learnintouch.data.dao.domain.SmsCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface SmsDao extends GenericDao<Sms, Serializable> {

	public Page<Sms> findAll(int pageNumber, int pageSize);
	
	public Page<Sms> findWithAdmin(Admin admin, int pageNumber, int pageSize);
	
	public Page<Sms> findWithCategory(SmsCategory smsCategory, int pageNumber, int pageSize);

	public Page<Sms> findWithAdminAndCategory(Admin admin, SmsCategory smsCategory, int pageNumber, int pageSize);
	
	public Page<Sms> findWithPatternLike(String pattern, int pageNumber, int pageSize);
	
}
