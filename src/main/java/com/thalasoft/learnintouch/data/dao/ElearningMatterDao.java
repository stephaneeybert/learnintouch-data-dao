package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ElearningMatter;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningMatterDao extends GenericDao<ElearningMatter, Serializable> {

	public Page<ElearningMatter> findAll(int pageNumber, int pageSize);

}
