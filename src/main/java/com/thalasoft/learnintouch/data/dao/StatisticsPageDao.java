package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.StatisticsPage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface StatisticsPageDao extends GenericDao<StatisticsPage, Serializable> {

	public Page<StatisticsPage> findAll(int pageNumber, int pageSize);

	public Page<StatisticsPage> findWithYearAndMonth(int year, int month, int pageNumber, int pageSize);
	
	public Page<StatisticsPage> findWithPageAndYearAndMonth(String page, int year, int month, int pageNumber, int pageSize);
	
	public long addHit(StatisticsPage statisticsPage);

	public long deleteOld(int year);

}
