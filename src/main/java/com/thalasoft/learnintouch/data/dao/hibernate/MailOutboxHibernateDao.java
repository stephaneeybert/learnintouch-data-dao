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

import com.thalasoft.learnintouch.data.dao.MailOutboxDao;
import com.thalasoft.learnintouch.data.dao.domain.MailOutbox;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class MailOutboxHibernateDao extends GenericHibernateDao<MailOutbox, Serializable> implements MailOutboxDao {

	@Override
	public Page<MailOutbox> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailOutbox> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<MailOutbox> findSent(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("sent", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailOutbox> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<MailOutbox> findUnsent(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("sent", false));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailOutbox> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<MailOutbox> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
        Conjunction conjunction = Restrictions.conjunction();
		String pattern = "%" + searchPattern + "%";
		Criterion firstname = Restrictions.ilike("firstname", pattern);
		Criterion lastname = Restrictions.ilike("lastname", pattern);
		Criterion email = Restrictions.ilike("email", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(firstname).add(lastname).add(email);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailOutbox> page = getPage(pageNumber, pageSize, criteria, orderList);
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
	public long deleteAll() {
		Query query = getSession().createQuery("delete from MailOutbox");
		return query.executeUpdate();
	}

}
