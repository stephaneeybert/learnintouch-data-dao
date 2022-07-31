package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ContentImportHistoryDao;
import com.thalasoft.learnintouch.data.dao.domain.ContentImportHistory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ContentImportHistoryHibernateDao extends GenericHibernateDao<ContentImportHistory, Serializable> implements ContentImportHistoryDao {

	@Override
	public Page<ContentImportHistory> findWithDomainName(String domainName, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.eq("domainName", domainName); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("domainName")).add(Order.desc("importDatetime"));
		Page<ContentImportHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ContentImportHistory> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion domainName = Restrictions.ilike("domainName", pattern);
		Criterion course = Restrictions.ilike("course", pattern);
		Criterion lesson = Restrictions.ilike("lesson", pattern);
		Criterion exercise = Restrictions.ilike("exercise", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(domainName).add(course).add(lesson).add(exercise);
		Criterion criterion = Restrictions.and(domainName, disjunction);
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("domainName")).add(Order.desc("importDatetime"));
		Page<ContentImportHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ContentImportHistory> findWithCourseContent(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Criterion criterion = Restrictions.and(Restrictions.isNotNull("course"), Restrictions.isNotNull("course"));
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("domainName")).add(Order.desc("importDatetime"));
		Page<ContentImportHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ContentImportHistory> findWithLessonContent(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Criterion criterion = Restrictions.and(Restrictions.isNotNull("lesson"), Restrictions.isNotNull("lesson")); 
        criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("domainName")).add(Order.desc("importDatetime"));
		Page<ContentImportHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ContentImportHistory> findWithExerciseContent(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.and(Restrictions.isNotNull("exercise"), Restrictions.isNotNull("exercise"));
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("domainName")).add(Order.desc("importDatetime"));
		Page<ContentImportHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ContentImportHistory> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("domainName")).add(Order.desc("importDatetime"));
		Page<ContentImportHistory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
