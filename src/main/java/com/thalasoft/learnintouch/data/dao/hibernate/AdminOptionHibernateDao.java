package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.AdminOptionDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.AdminOption;

@Repository
@Transactional
public class AdminOptionHibernateDao extends GenericHibernateDao<AdminOption, Serializable> implements AdminOptionDao {

	@Override
	public AdminOption findWithNameAndAdmin(String option, Admin admin) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", option))
		.add(Restrictions.eq("admin", admin));
		return findObjectByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminOption> findWithName(String option) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", option));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminOption> findWithAdmin(Admin admin) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("admin", admin))
		.addOrder(Order.asc("name"));
		return criteria.list();
	}

}
