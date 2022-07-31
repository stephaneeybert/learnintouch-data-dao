package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.StatisticsVisitDao;
import com.thalasoft.learnintouch.data.dao.domain.StatisticsVisit;

@Repository
@Transactional
public class StatisticsVisitHibernateDao extends GenericHibernateDao<StatisticsVisit, Serializable> implements StatisticsVisitDao {

	@Override
	public long deleteOld(int year) {
		Query query = getSession().createQuery("delete from StatisticsVisit where YEAR(visitDatetime) < ?");
		query.setInteger(0, year);
		return query.executeUpdate();
	}
	
	@Override
	public long countOldVisits(int year) {
		Query query = getSession().createQuery("select count(*) as count from StatisticsVisit where YEAR(visitDatetime) < ?");
		query.setInteger(0, year);
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findCountsByReferer(int year, int month) {
		Query query = getSession().createQuery("select count(*) as count, visitorReferer from StatisticsVisit where YEAR(visitDatetime) = ? and MONTH(visitDatetime) = ? group by visitorReferer order by visitorReferer");
		query.setInteger(0, year);
		query.setInteger(1, month);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findCountsByBrowser() {
		Query query = getSession().createQuery("select count(*) as count, visitorBrowser from StatisticsVisit group by visitorBrowser order by visitorBrowser");
		return query.list();
	}

	@Override
	public StatisticsVisit findHostLastVisit(String visitorHostAddress) {
		Query query = getSession().createQuery("SELECT id, MAX(visitDatetime) as visitDatetime, visitorHostAddress, visitorBrowser, visitorReferer FROM StatisticsVisit WHERE visitorHostAddress = '?' GROUP BY visitorHostAddress");
		query.setString(0, visitorHostAddress);
		return (StatisticsVisit) query.uniqueResult();
	}

	@Override
	public long countMonthVisitors(int year, int month) {
		Query query = getSession().createQuery("select COUNT(DISTINCT visitorHostAddress) as count from StatisticsVisit where YEAR(visitDatetime) = ? and MONTH(visitDatetime) = ?");
		query.setInteger(0, year);
		query.setInteger(1, month);
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countMonthVisits(int year, int month) {
		Query query = getSession().createQuery("select COUNT(*) as count from StatisticsVisit where YEAR(visitDatetime) = ? and MONTH(visitDatetime) = ?");
		query.setInteger(0, year);
		query.setInteger(1, month);
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countDayVisitors(int year, int month, int day) {
		Query query = getSession().createQuery("select COUNT(DISTINCT visitorHostAddress) as count from StatisticsVisit where YEAR(visitDatetime) = ? and MONTH(visitDatetime) = ? and DAYOFMONTH(visitDatetime) = ?");
		query.setInteger(0, year);
		query.setInteger(1, month);
		query.setInteger(2, day);
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countDayVisits(int year, int month, int day) {
		Query query = getSession().createQuery("select COUNT(*) as count from StatisticsVisit where YEAR(visitDatetime) = ? and MONTH(visitDatetime) = ? and DAYOFMONTH(visitDatetime) = ?");
		query.setInteger(0, year);
		query.setInteger(1, month);
		query.setInteger(2, day);
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countWeekDayVisits(int year, int day) {
	    // MySQL is following the ODBC standard
	    // which takes Sunday as the first day of the week
		int dayOfWeek = day + 1;
		int seven = 7;
		if (dayOfWeek > seven) {
			dayOfWeek = 1;
		}

		Query query = getSession().createQuery("select COUNT(*) as count from StatisticsVisit where YEAR(visitDatetime) = ? and DAYOFWEEK(visitDatetime) = ?");
		query.setInteger(0, year);
		query.setInteger(1, dayOfWeek);
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countHourVisits(int year, int hour) {
		Query query = getSession().createQuery("select COUNT(*) as count from StatisticsVisit where YEAR(visitDatetime) = ? and HOUR(visitDatetime) = ?");
		query.setInteger(0, year);
		query.setInteger(1, hour);
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countVisitorsSince(LocalDateTime visitDatetime) {
		Query query = getSession().createQuery("select COUNT(DISTINCT visitorHostAddress) as count from StatisticsVisit where visitDatetime >= ?");
		query.setDate(0, visitDatetime.toDateTime().toDate());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long countVisitsSince(LocalDateTime visitDatetime) {
		Query query = getSession().createQuery("select COUNT(*) as count from StatisticsVisit where visitDatetime >= ?");
		query.setDate(0, visitDatetime.toDateTime().toDate());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
