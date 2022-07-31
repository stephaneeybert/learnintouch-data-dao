package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.SmsDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Sms;
import com.thalasoft.learnintouch.data.dao.domain.SmsCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class SmsHibernateDao extends GenericHibernateDao<Sms, Serializable> implements SmsDao {

	private static final String DB_TABLE_SMS_CATEGORY = "smsCategory";
	private static final String DB_TABLE_ADMIN = "admin";

	@Override
	public Page<Sms> findWithAdmin(Admin admin, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (admin != null) {
		    conjunction.add(Restrictions.eq("admin", admin));
		} else {
		    conjunction.add(Restrictions.isNull("admin"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("body"));
        Page<Sms> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Sms> findWithCategory(SmsCategory smsCategory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (smsCategory != null) {
		    conjunction.add(Restrictions.eq("smsCategory", smsCategory));
		} else {
		    conjunction.add(Restrictions.isNull("smsCategory"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("body"));
		Page<Sms> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Sms> findWithAdminAndCategory(Admin admin, SmsCategory smsCategory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (admin != null) {
		    conjunction.add(Restrictions.eq("admin", admin));
		} else {
		    conjunction.add(Restrictions.isNull("admin"));
		}
		if (smsCategory != null) {
		    conjunction.add(Restrictions.eq("mailCategory", smsCategory));
		} else {
		    conjunction.add(Restrictions.isNull("mailCategory"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("body"));
		Page<Sms> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Sms> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("body"));
		Page<Sms> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Sms> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion description = Restrictions.ilike("s.description", pattern);
		Criterion body = Restrictions.ilike("s.body", pattern);
		Criterion firstname = Restrictions.ilike("a.firstname", pattern);
		Criterion lastname = Restrictions.ilike("a.lastname", pattern);
		Criterion login = Restrictions.ilike("a.login", pattern);
		Criterion email = Restrictions.ilike("a.email", pattern);
		Criterion name = Restrictions.ilike("c.name", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(description).add(body).add(firstname).add(lastname).add(login).add(email).add(name);
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "s");
		criteria.createAlias(DB_TABLE_ADMIN, "a", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias(DB_TABLE_SMS_CATEGORY, "c", CriteriaSpecification.LEFT_JOIN);
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("s.body"));
		Page<Sms> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
