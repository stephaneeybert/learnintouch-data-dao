package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.NewsPublication;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface NewsPublicationDao extends GenericDao<NewsPublication, Serializable> {

	public Page<NewsPublication> findAll(int pageNumber, int pageSize);
	
	public Page<NewsPublication> findWithPatternLike(String pattern, int pageNumber, int pageSize);
	
	public NewsPublication findWithName(String name);
	
}
