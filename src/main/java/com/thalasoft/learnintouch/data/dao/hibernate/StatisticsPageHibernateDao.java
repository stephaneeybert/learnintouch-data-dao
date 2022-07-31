package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.StatisticsPageDao;
import com.thalasoft.learnintouch.data.dao.domain.StatisticsPage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class StatisticsPageHibernateDao extends GenericHibernateDao<StatisticsPage, Serializable> implements StatisticsPageDao {

	@Override
	public Page<StatisticsPage> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.desc("hits"));
		Page<StatisticsPage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public long addHit(StatisticsPage statisticsPage) {
		Query query = getSession().createQuery("update StatisticsPage set hits = hits + 1 where id = :id");
		query.setLong("id", statisticsPage.getId());
		return query.executeUpdate();
	}

	@Override
	public long deleteOld(int year) {
		Query query = getSession().createQuery("delete from StatisticsPage where year < :year");
		query.setLong("year", year);
		return query.executeUpdate();
	}

	@Override
	public Page<StatisticsPage> findWithYearAndMonth(int year, int month, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("year", year));
        conjunction.add(Restrictions.eq("month", month));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("hits"));
		Page<StatisticsPage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<StatisticsPage> findWithPageAndYearAndMonth(String webPage, int year, int month, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("page", webPage));
        conjunction.add(Restrictions.eq("year", year));
        conjunction.add(Restrictions.eq("month", month));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("hits"));
		Page<StatisticsPage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
