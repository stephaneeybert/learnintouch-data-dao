package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.SmsList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface SmsListDao extends GenericDao<SmsList, Serializable> {

	public Page<SmsList> findAll(int pageNumber, int pageSize);
	
	public Page<SmsList> findAutoSubscribe(int pageNumber, int pageSize);
	
	public Page<SmsList> findWithPatternLike(String pattern, int pageNumber, int pageSize);
	
}
