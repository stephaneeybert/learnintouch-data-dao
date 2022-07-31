package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.AdminDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class AdminHibernateDao extends GenericHibernateDao<Admin, Serializable> implements AdminDao {

	@Override
	public Page<Admin> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname"));
		Page<Admin> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Admin findWithLogin(String login) {
		Criterion criterion = Restrictions.eq("login", login);
		return findObjectByCriteria(criterion);
	}

	@Override
	public Admin findWithLoginAndPassword(String login, String password) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("login", login)).add(Restrictions.eq("password", password));
		return findObjectByCriteria(criteria);
	}

	@Override
	public Admin findWithEmail(String email) {
		Criterion criterion = Restrictions.eq("email", email);
		return findObjectByCriteria(criterion);
	}

	@Override
	public Page<Admin> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion firstname = Restrictions.ilike("firstname", pattern);
		Criterion lastname = Restrictions.ilike("lastname", pattern);
		Criterion login = Restrictions.ilike("login", pattern);
		Criterion email = Restrictions.ilike("email", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(firstname);
		disjunction.add(lastname);
		disjunction.add(login);
		disjunction.add(email);
		criteria.add(disjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<Admin> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Admin> findAllNonSuperAdminPlusSpecifiedOne(String login, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.or(Restrictions.eq("superAdmin", false), Restrictions.eq("login", login)));
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<Admin> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
