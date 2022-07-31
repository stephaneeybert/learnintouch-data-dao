package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ElearningLevel;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningLevelDao extends GenericDao<ElearningLevel, Serializable> {

	public Page<ElearningLevel> findAll(int pageNumber, int pageSize);

}
