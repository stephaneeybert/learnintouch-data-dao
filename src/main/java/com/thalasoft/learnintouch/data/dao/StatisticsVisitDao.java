package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.StatisticsVisit;

public interface StatisticsVisitDao extends GenericDao<StatisticsVisit, Serializable> {

	public long countOldVisits(int year);
	
	public List<Object[]> findCountsByReferer(int year, int month);
	
	public List<Object[]> findCountsByBrowser();
	
	public long deleteOld(int year);
	
	public StatisticsVisit findHostLastVisit(String visitorHostAddress);
	
	public long countMonthVisitors(int year, int month);
	
	public long countMonthVisits(int year, int month);
	
	public long countDayVisitors(int year, int month, int day);
	
	public long countDayVisits(int year, int month, int day);
	
	public long countWeekDayVisits(int year, int day);
	
	public long countHourVisits(int year, int hour);
	
	public long countVisitorsSince(LocalDateTime visitDatetime);

	public long countVisitsSince(LocalDateTime visitDatetime);
	
}
