package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.StatisticsRefererDao;
import com.thalasoft.learnintouch.data.dao.domain.StatisticsReferer;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class StatisticsRefererHibernateDao extends GenericHibernateDao<StatisticsReferer, Serializable> implements StatisticsRefererDao {

	@Override
	public Page<StatisticsReferer> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<StatisticsReferer> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public StatisticsReferer findWithName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name));
		return (StatisticsReferer) criteria.uniqueResult();
	}

	@Override
	public StatisticsReferer findWithUrl(String url) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("url", url));
		return (StatisticsReferer) criteria.uniqueResult();
	}

}
