package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.MailListDao;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class MailListHibernateDao extends GenericHibernateDao<MailList, Serializable> implements MailListDao {

	@Override
	public Page<MailList> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<MailList> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<MailList> findAutoSubscribe(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("autoSubscribe", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<MailList> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<MailList> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
        Conjunction conjunction = Restrictions.conjunction();
		String pattern = "%" + searchPattern + "%";
		Criterion name = Restrictions.ilike("m.name", pattern);
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "m");
		conjunction.add(name);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("m.name"));
		Page<MailList> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
