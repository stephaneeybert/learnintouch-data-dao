package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.SmsNumber;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface SmsNumberDao extends GenericDao<SmsNumber, Serializable> {

	public Page<SmsNumber> findAll(int pageNumber, int pageSize);
	
	public Page<SmsNumber> findImported(int pageNumber, int pageSize);
	
	public SmsNumber findWithMobilePhone(String mobilePhone);
	
	public Page<SmsNumber> findWithPatternLike(String pattern, int pageNumber, int pageSize);
	
	public Page<SmsNumber> findSubscribers(int pageNumber, int pageSize);
	
	public Page<SmsNumber> findNonSubscribers(int pageNumber, int pageSize);
	
	public long resetImport();
	
	public long deleteImported();
	
	public long countImported();
	
}
