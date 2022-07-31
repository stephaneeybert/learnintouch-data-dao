package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.StatisticsReferer;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface StatisticsRefererDao extends GenericDao<StatisticsReferer, Serializable> {

	public Page<StatisticsReferer> findAll(int pageNumber, int pageSize);
	
	public StatisticsReferer findWithName(String name);
	
	public StatisticsReferer findWithUrl(String url);
	
}
