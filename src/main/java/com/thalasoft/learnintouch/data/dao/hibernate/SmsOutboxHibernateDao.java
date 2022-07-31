package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.SmsOutboxDao;
import com.thalasoft.learnintouch.data.dao.domain.SmsOutbox;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class SmsOutboxHibernateDao extends GenericHibernateDao<SmsOutbox, Serializable> implements SmsOutboxDao {

	@Override
	public Page<SmsOutbox> findSent(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("sent", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("mobilePhone"));
		Page<SmsOutbox> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<SmsOutbox> findUnsent(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("sent", false));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("mobilePhone"));
		Page<SmsOutbox> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public long countUnsent() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("sent", false));
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.list().get(0)).longValue();
	}

	@Override
	public Page<SmsOutbox> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion firstname = Restrictions.ilike("firstname", pattern);
		Criterion lastname = Restrictions.ilike("lastname", pattern);
		Criterion mobilePhone = Restrictions.ilike("mobilePhone", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(firstname).add(lastname).add(mobilePhone);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("mobilePhone"));
		Page<SmsOutbox> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public long deleteAll() {
		Query query = getSession().createQuery("delete from SmsOutbox");
		return query.executeUpdate();
	}

}
