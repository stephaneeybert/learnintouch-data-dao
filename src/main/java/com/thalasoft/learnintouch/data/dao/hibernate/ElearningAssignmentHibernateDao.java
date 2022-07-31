package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningAssignmentDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningAssignment;
import com.thalasoft.learnintouch.data.dao.domain.ElearningClass;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningResult;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubscription;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningAssignmentHibernateDao extends GenericHibernateDao<ElearningAssignment, Serializable> implements ElearningAssignmentDao {

	private static final String DB_TABLE_ELEARNING_SUBSCRIPTION = "elearningSubscription";
	private static final String DB_TABLE_ELEARNING_EXERCISE = "elearningExercise";
	private static final String DB_TABLE_ELEARNING_RESULT = "elearningResult";
	private static final String DB_TABLE_USER_ACCOUNT = "userAccount";

	@Override
	public Page<ElearningAssignment> findWithExercise(ElearningExercise elearningExercise, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "es", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
		Criterion criterion = Restrictions.eq("ea.elearningExercise", elearningExercise); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("u.firstname")).add(Order.asc("u.lastname")).add(Order.asc("u.email"));
		Page<ElearningAssignment> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningAssignment> findWithResult(ElearningResult elearningResult, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		Criterion criterion = Restrictions.eq("ea.elearningResult", elearningResult); 
		criteria.add(criterion);
		Page<ElearningAssignment> page = getPage(pageNumber, pageSize, criteria);
		return page;
	}
	
	@Override
	public Page<ElearningAssignment> findWithSubscription(ElearningSubscription elearningSubscription, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		criteria.createAlias(DB_TABLE_ELEARNING_EXERCISE, "ee", CriteriaSpecification.INNER_JOIN);
		Criterion criterion = Restrictions.eq("ea.elearningSubscription", elearningSubscription); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("ee.name"));
		Page<ElearningAssignment> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public ElearningAssignment findWithSubscriptionAndExercise(ElearningSubscription elearningSubscription, ElearningExercise elearningExercise, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		criteria.add(Restrictions.eq("ea.elearningSubscription", elearningSubscription));
		criteria.add(Restrictions.eq("ea.elearningExercise", elearningExercise));
		return (ElearningAssignment) criteria.uniqueResult();
	}

	@Override
	public Page<ElearningAssignment> findWithSubscriptionAndOpened(ElearningSubscription elearningSubscription, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		criteria.createAlias(DB_TABLE_ELEARNING_RESULT, "er", CriteriaSpecification.INNER_JOIN);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("ea.elearningSubscription", elearningSubscription)) 
		.add(Restrictions.or(Restrictions.eq("ea.onlyOnce", false), Restrictions.eq("ea.done", false)))
		.add(Restrictions.or(Restrictions.isNull("ea.openingDate"), Restrictions.sqlRestriction("DATE(ea.openingDate) <= DATE('" + systemDateTime + "')"))) 
		.add(Restrictions.or(Restrictions.isNull("ea.closingDate"), Restrictions.sqlRestriction("DATE(ea.closingDate) >= DATE('" + systemDateTime + "')"))); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.desc("er.exerciseDateTime"));
		Page<ElearningAssignment> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningAssignment> findWithSubscriptionAndClosed(ElearningSubscription elearningSubscription, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		criteria.createAlias(DB_TABLE_ELEARNING_RESULT, "er", CriteriaSpecification.INNER_JOIN);
		Conjunction conjunction0 = Restrictions.conjunction();
		conjunction0.add(Restrictions.eq("ea.onlyOnce", true)).add(Restrictions.eq("ea.done", true));
		Conjunction conjunction1 = Restrictions.conjunction();
		conjunction1.add(Restrictions.isNotNull("ea.closingDate")).add(Restrictions.sqlRestriction("DATE(ea.closingDate) < DATE('" + systemDateTime + "')"));
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("ea.elearningSubscription", elearningSubscription))
		.add(Restrictions.or(conjunction0, conjunction1));
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.desc("er.exerciseDateTime"));
		Page<ElearningAssignment> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningAssignment> findWithSubscriptionAndDeferred(ElearningSubscription elearningSubscription, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		criteria.createAlias(DB_TABLE_ELEARNING_RESULT, "er", CriteriaSpecification.INNER_JOIN);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("ea.elearningSubscription", elearningSubscription))
		.add(Restrictions.or(Restrictions.eq("ea.onlyOnce", false), Restrictions.eq("ea.done", false)))
		.add(Restrictions.isNotNull("ea.openingDate"))
		.add(Restrictions.sqlRestriction("DATE(ea.openingDate) > DATE('" + systemDateTime + "')"));
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.desc("er.exerciseDateTime"));
		Page<ElearningAssignment> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}	

	@Override
	public Page<ElearningAssignment> findWithClassAndOpened(ElearningClass elearningClass, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		criteria.createAlias(DB_TABLE_ELEARNING_RESULT, "er", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "es", CriteriaSpecification.INNER_JOIN);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("es.elearningClass", elearningClass))
		.add(Restrictions.or(Restrictions.eq("ea.onlyOnce", false), Restrictions.eq("ea.done", false)))
		.add(Restrictions.or(Restrictions.isNull("ea.openingDate"), Restrictions.sqlRestriction("DATE(ea.openingDate) <= DATE('" + systemDateTime + "')"))) 
		.add(Restrictions.or(Restrictions.isNull("ea.closingDate"), Restrictions.sqlRestriction("DATE(ea.closingDate) >= DATE('" + systemDateTime + "')")));
        criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.desc("er.exerciseDateTime"));
		Page<ElearningAssignment> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}	
	
	@Override
	public Page<ElearningAssignment> findWithClassAndClosed(ElearningClass elearningClass, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		criteria.createAlias(DB_TABLE_ELEARNING_RESULT, "er", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "es", CriteriaSpecification.INNER_JOIN);
		Conjunction conjunction0 = Restrictions.conjunction();
		conjunction0.add(Restrictions.eq("ea.onlyOnce", true)).add(Restrictions.eq("ea.done", true));
		Conjunction conjunction1 = Restrictions.conjunction();
		conjunction1.add(Restrictions.isNotNull("ea.closingDate")).add(Restrictions.sqlRestriction("DATE(ea.closingDate) < DATE('" + systemDateTime + "')"));
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("es.elearningClass", elearningClass))
		.add(Restrictions.or(conjunction0, conjunction1)); 
        criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.desc("er.exerciseDateTime"));
		Page<ElearningAssignment> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningAssignment> findWithClassAndDeferred(ElearningClass elearningClass, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ea");
		criteria.createAlias(DB_TABLE_ELEARNING_RESULT, "er", CriteriaSpecification.INNER_JOIN);
		criteria.createAlias(DB_TABLE_ELEARNING_SUBSCRIPTION, "es", CriteriaSpecification.INNER_JOIN);
		Criterion criterion = Restrictions.conjunction().add(Restrictions.eq("es.elearningClass", elearningClass))
		.add(Restrictions.or(Restrictions.eq("ea.onlyOnce", false), Restrictions.eq("ea.done", false)))
		.add(Restrictions.isNotNull("ea.openingDate"))
		.add(Restrictions.sqlRestriction("DATE(ea.openingDate) > DATE('" + systemDateTime + "')")); 
        criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.desc("er.exerciseDateTime"));
		Page<ElearningAssignment> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}	

}
