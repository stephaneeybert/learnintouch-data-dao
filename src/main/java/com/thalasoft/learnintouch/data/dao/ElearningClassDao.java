package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ElearningClass;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningClassDao extends GenericDao<ElearningClass, Serializable> {

	public Page<ElearningClass> findAll(int pageNumber, int pageSize);

	public Page<ElearningClass> findWithSession(ElearningSession elearningSession, int pageNumber, int pageSize);

	public Page<ElearningClass> findWithPatternLike(String searchPattern, int pageNumber, int pageSize);
	
}
