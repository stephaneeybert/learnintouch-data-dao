package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface MailListDao extends GenericDao<MailList, Serializable> {

	public Page<MailList> findAll(int pageNumber, int pageSize);

	public Page<MailList> findAutoSubscribe(int pageNumber, int pageSize);
	
	public Page<MailList> findWithPatternLike(String pattern, int pageNumber, int pageSize);

}
