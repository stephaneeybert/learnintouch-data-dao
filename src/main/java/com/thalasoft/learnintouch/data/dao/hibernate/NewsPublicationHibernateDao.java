package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NewsPublicationDao;
import com.thalasoft.learnintouch.data.dao.domain.NewsPublication;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class NewsPublicationHibernateDao extends GenericHibernateDao<NewsPublication, Serializable> implements NewsPublicationDao {

	@Override
	public Page<NewsPublication> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<NewsPublication> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPublication> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion name = Restrictions.ilike("name", pattern);
		Criterion description = Restrictions.ilike("description", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(name).add(description);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<NewsPublication> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public NewsPublication findWithName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name));
		criteria.setMaxResults(1);
		return (NewsPublication) criteria.uniqueResult();
	}

}
