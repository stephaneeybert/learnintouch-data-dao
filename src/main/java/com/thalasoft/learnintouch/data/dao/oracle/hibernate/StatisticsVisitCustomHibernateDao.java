package com.thalasoft.learnintouch.data.dao.oracle.hibernate;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.hibernate.StatisticsVisitHibernateDao;

@Repository
@Transactional
public class StatisticsVisitCustomHibernateDao extends StatisticsVisitHibernateDao {

	@Override
	public long countDayVisitors(int year, int month, int day) {
		Query query = getSession().createSQLQuery("select count(DISTINCT visitor_host_address) as count from statistics_visit where to_char(visit_datetime, 'YYYY') = :year and to_char(visit_datetime, 'MM') = :month and to_char(visit_datetime, 'DD') = :day");
		query.setInteger("year", year);
		query.setInteger("month", month);
		query.setInteger("day", day);
		BigDecimal count = (BigDecimal) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countDayVisits(int year, int month, int day) {
		Query query = getSession().createSQLQuery("select count(*) as count from statistics_visit where to_char(visit_datetime, 'YYYY') = :year and to_char(visit_datetime, 'MM') = :month and to_char(visit_datetime, 'DD') = :day");
		query.setInteger("year", year);
		query.setInteger("month", month);
		query.setInteger("day", day);
		BigDecimal count = (BigDecimal) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countWeekDayVisits(int year, int day) {
	    // Oracle is following the ODBC standard
	    // which takes Sunday as the first day of the week
		int dayOfWeek = day + 1;
		int seven = 7;
		if (dayOfWeek > seven) {
			dayOfWeek = 1;
		}

		Query query = getSession().createSQLQuery("select count(*) as count from statistics_visit where to_char(visit_datetime, 'YYYY') = :year and to_char(visit_datetime, 'D') = :day");
		query.setInteger("year", year);
		query.setInteger("day", dayOfWeek);
		BigDecimal count = (BigDecimal) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countHourVisits(int year, int hour) {
		Query query = getSession().createSQLQuery("select count(*) as count from statistics_visit where to_char(visit_datetime, 'YYYY') = :year and to_char(visit_datetime, 'HH24') = :hour");
		query.setInteger("year", year);
		query.setInteger("hour", hour);
		BigDecimal count = (BigDecimal) query.list().get(0);
		return count.longValue();
	}
	
}
