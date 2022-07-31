package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.SmsListDao;
import com.thalasoft.learnintouch.data.dao.domain.SmsList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class SmsListHibernateDao extends GenericHibernateDao<SmsList, Serializable> implements SmsListDao {

	@Override
	public Page<SmsList> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<SmsList> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<SmsList> findAutoSubscribe(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("autoSubscribe", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
        Page<SmsList> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<SmsList> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "s");
        Conjunction conjunction = Restrictions.conjunction();
        Criterion name = Restrictions.ilike("s.name", pattern);
        conjunction.add(name);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<SmsList> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
