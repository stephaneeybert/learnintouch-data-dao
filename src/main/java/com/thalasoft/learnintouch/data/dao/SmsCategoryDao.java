package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.SmsCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface SmsCategoryDao extends GenericDao<SmsCategory, Serializable> {

	public Page<SmsCategory> findAll(int pageNumber, int pageSize);
	
}
