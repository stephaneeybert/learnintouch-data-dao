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

import com.thalasoft.learnintouch.data.dao.MailHistoryDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.MailHistory;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class MailHistoryHibernateDao extends GenericHibernateDao<MailHistory, Serializable> implements MailHistoryDao {

	private static final String DB_TABLE_ADMIN = "admin";

	@Override
	public Page<MailHistory> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("subject"));
		Page<MailHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<MailHistory> findWithAdmin(Admin admin, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (admin != null) {
		    conjunction.add(Restrictions.eq("admin", admin));
		} else {
		    conjunction.add(Restrictions.isNull("admin"));
		}
		criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("subject"));
		Page<MailHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<MailHistory> findWithMailList(MailList mailList, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (mailList != null) {
		    conjunction.add(Restrictions.eq("mailList", mailList));
		} else {
		    conjunction.add(Restrictions.isNull("mailList"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("subject"));
		Page<MailHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<MailHistory> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion description = Restrictions.ilike("m.description", pattern);
		Criterion subject = Restrictions.ilike("m.subject", pattern);
		Criterion body = Restrictions.ilike("m.body", pattern);
		Criterion firstname = Restrictions.ilike("a.firstname", pattern);
		Criterion lastname = Restrictions.ilike("a.lastname", pattern);
		Criterion login = Restrictions.ilike("a.login", pattern);
		Criterion email = Restrictions.ilike("a.email", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(description).add(subject).add(body).add(firstname).add(lastname).add(login).add(email);
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "m");
		criteria.createAlias(DB_TABLE_ADMIN, "a", CriteriaSpecification.LEFT_JOIN);
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("subject"));
		Page<MailHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public long deleteAll() {
		Query query = getSession().createQuery("delete from MailHistory");
		return query.executeUpdate();
	}

}
