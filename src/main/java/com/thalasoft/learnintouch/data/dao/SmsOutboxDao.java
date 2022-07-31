package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.SmsOutbox;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface SmsOutboxDao extends GenericDao<SmsOutbox, Serializable> {

	public Page<SmsOutbox> findSent(int pageNumber, int pageSize);
	
	public Page<SmsOutbox> findUnsent(int pageNumber, int pageSize);
	
	public Page<SmsOutbox> findWithPatternLike(String pattern, int pageNumber, int pageSize);
	
	public long countUnsent();
	
	public long deleteAll();
	
}
