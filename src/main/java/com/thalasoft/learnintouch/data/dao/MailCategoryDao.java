package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.MailCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface MailCategoryDao extends GenericDao<MailCategory, Serializable> {
	
	public Page<MailCategory> findAll(int pageNumber, int pageSize);
	
}
