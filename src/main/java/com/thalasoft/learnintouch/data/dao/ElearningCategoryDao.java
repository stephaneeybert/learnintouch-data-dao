package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningCategoryDao extends GenericDao<ElearningCategory, Serializable> {

	public Page<ElearningCategory> findAll(int pageNumber, int pageSize);

}
