package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ElearningSubject;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningSubjectDao extends GenericDao<ElearningSubject, Serializable> {

	public Page<ElearningSubject> findAll(int pageNumber, int pageSize);

}
