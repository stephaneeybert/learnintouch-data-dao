package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.MailOutbox;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface MailOutboxDao extends GenericDao<MailOutbox, Serializable> {

	public Page<MailOutbox> findAll(int pageNumber, int pageSize);
	
	public Page<MailOutbox> findSent(int pageNumber, int pageSize);
	
	public Page<MailOutbox> findUnsent(int pageNumber, int pageSize);

	public Page<MailOutbox> findWithPatternLike(String pattern, int pageNumber, int pageSize);
	
	public long countUnsent();
	
	public long deleteAll();
	
}
