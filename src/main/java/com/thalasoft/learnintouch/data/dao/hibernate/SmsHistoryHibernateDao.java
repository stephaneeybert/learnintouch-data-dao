package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.SmsHistoryDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Sms;
import com.thalasoft.learnintouch.data.dao.domain.SmsHistory;
import com.thalasoft.learnintouch.data.dao.domain.SmsList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class SmsHistoryHibernateDao extends GenericHibernateDao<SmsHistory, Serializable> implements SmsHistoryDao {

	private static final String DB_TABLE_ADMIN = "admin";

	@Override
	public Page<SmsHistory> findWithAdmin(Admin admin, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (admin != null) {
		    conjunction.add(Restrictions.eq("admin", admin));
		} else {
		    conjunction.add(Restrictions.isNull("admin"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("sendDatetime"));
		Page<SmsHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<SmsHistory> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.desc("sendDatetime"));
		Page<SmsHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<SmsHistory> findWithSmsList(SmsList smsList, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (smsList != null) {
		    conjunction.add(Restrictions.eq("smsList", smsList));
		} else {
		    conjunction.add(Restrictions.isNull("smsList"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("sendDatetime"));
		Page<SmsHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<SmsHistory> findWithSms(Sms sms, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (sms != null) {
		    conjunction.add(Restrictions.eq("sms", sms));
		} else {
		    conjunction.add(Restrictions.isNull("sms"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("sendDatetime"));
		Page<SmsHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<SmsHistory> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion mobilePhone = Restrictions.ilike("s.mobilePhone", pattern);
		Criterion firstname = Restrictions.ilike("a.firstname", pattern);
		Criterion lastname = Restrictions.ilike("a.lastname", pattern);
		Criterion login = Restrictions.ilike("a.login", pattern);
		Criterion email = Restrictions.ilike("a.email", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(mobilePhone).add(firstname).add(lastname).add(login).add(email);
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "s");
		criteria.createAlias(DB_TABLE_ADMIN, "a", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("sendDatetime"));
		Page<SmsHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public long deleteAll() {
		Query query = getSession().createQuery("delete from SmsHistory");
		return query.executeUpdate();
	}

}
