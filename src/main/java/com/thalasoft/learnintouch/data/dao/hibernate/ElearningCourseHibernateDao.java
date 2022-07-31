package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningCourseDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningMatter;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningCourseHibernateDao extends GenericHibernateDao<ElearningCourse, Serializable> implements ElearningCourseDao {

	private static final String DB_TABLE_ELEARNING_SESSION_COURSE = "elearningSessionCourse";

	@Override
	public Page<ElearningCourse> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningCourse> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	public ElearningCourse findWithName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name)).setMaxResults(1);
		return (ElearningCourse) criteria.uniqueResult();		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningCourse> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@Override
	public Page<ElearningCourse> findImportable(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.eq("importable", true); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningCourse> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningCourse> findWithSession(ElearningSession elearningSession, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ec");
		criteria.createAlias(DB_TABLE_ELEARNING_SESSION_COURSE, "esc", CriteriaSpecification.INNER_JOIN);
		Criterion criterion = Restrictions.eq("esc.elearningSession", elearningSession); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("ec.name"));
		Page<ElearningCourse> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningCourse> findWithMatter(ElearningMatter elearningMatter, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ec");
		Criterion  criterion = Restrictions.eq("elearningMatter", elearningMatter); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningCourse> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningCourse> findWithUser(UserAccount userAccount, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ec");
		Criterion criterion = Restrictions.eq("userAccount", userAccount); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningCourse> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningCourse> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
	    Criteria criteria = getSession().createCriteria(getPersistentClass(), "et");
		String pattern = "%" + searchPattern + "%";
		Criterion name = Restrictions.ilike("name", pattern);
		Criterion description = Restrictions.ilike("description", pattern);
		Criterion id = Restrictions.eq("id", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(name).add(description).add(id);
		criteria.add(disjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningCourse> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
