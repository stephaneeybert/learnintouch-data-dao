package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.NewsHeading;
import com.thalasoft.learnintouch.data.dao.domain.NewsPublication;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface NewsHeadingDao extends GenericDao<NewsHeading, Serializable> {

	public Page<NewsHeading> findAll(int pageNumber, int pageSize);
	
	public NewsHeading findWithListOrder(NewsPublication newsPublication, int listOrder);
	
	public NewsHeading findNextWithListOrder(NewsPublication newsPublication, int listOrder);
	
	public NewsHeading findPreviousWithListOrder(NewsPublication newsPublication, int listOrder);
	
	public long countListOrderDuplicates(NewsPublication newsPublication);
	
	public Page<NewsHeading> findWithNewsPublication(NewsPublication newsPublication, int pageNumber, int pageSize);
	
	public List<NewsHeading> findWithNewsPublicationOrderById(NewsPublication newsPublication);
	
	public List<NewsHeading> findWithImage(String image);
	
}
