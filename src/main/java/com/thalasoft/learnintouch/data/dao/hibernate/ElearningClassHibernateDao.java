package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningClassDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningClass;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningClassHibernateDao extends GenericHibernateDao<ElearningClass, Serializable> implements ElearningClassDao {

	private static final String DB_TABLE_ELEARNING_SESSION_CLASS = "elearningSessionClass";

	@Override
	public Page<ElearningClass> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningClass> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningClass> findWithSession(ElearningSession elearningSession, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ec");
		criteria.createAlias(DB_TABLE_ELEARNING_SESSION_CLASS, "esc", CriteriaSpecification.INNER_JOIN);
		Criterion criterion = Restrictions.eq("esc.elearningSession", elearningSession);
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("ec.name"));
		Page<ElearningClass> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningClass> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion name = Restrictions.ilike("name", pattern);
		Criterion description = Restrictions.ilike("description", pattern);
		Disjunction disjunction = Restrictions.disjunction();
	    disjunction.add(name);
	    disjunction.add(description);
		criteria.add(disjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningClass> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
