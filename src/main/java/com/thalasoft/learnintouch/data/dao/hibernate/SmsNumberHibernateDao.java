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

import com.thalasoft.learnintouch.data.dao.SmsNumberDao;
import com.thalasoft.learnintouch.data.dao.domain.SmsNumber;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class SmsNumberHibernateDao extends GenericHibernateDao<SmsNumber, Serializable> implements SmsNumberDao {

	@Override
	public Page<SmsNumber> findImported(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("imported", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("mobilePhone"));
		Page<SmsNumber> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<SmsNumber> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("mobilePhone"));
		Page<SmsNumber> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public SmsNumber findWithMobilePhone(String mobilePhone) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("mobilePhone", mobilePhone));
		return (SmsNumber) criteria.uniqueResult();
	}

	@Override
	public Page<SmsNumber> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion mobilePhone = Restrictions.ilike("mobilePhone", pattern);
		Criterion firstname = Restrictions.ilike("firstname", pattern);
		Criterion lastname = Restrictions.ilike("lastname", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(mobilePhone).add(firstname).add(lastname);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("mobilePhone"));
		Page<SmsNumber> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<SmsNumber> findSubscribers(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("subscribe", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("mobilePhone"));
		Page<SmsNumber> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<SmsNumber> findNonSubscribers(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.ne("subscribe", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("mobilePhone"));
		Page<SmsNumber> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public long resetImport() {
		Query query = getSession().createQuery("update SmsNumber set imported = false");
		return query.executeUpdate();
	}
	
	@Override
	public long deleteImported() {
		Query query = getSession().createQuery("delete from SmsNumber where imported = true");
		return query.executeUpdate();
	}
	
	@Override
	public long countImported() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.setProjection(Projections.rowCount());
		criteria.add(Restrictions.eq("imported", true));
		return ((Long) criteria.uniqueResult()).longValue();
	}

}
