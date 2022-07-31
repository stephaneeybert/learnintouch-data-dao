package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.AdminModuleDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.AdminModule;

@Repository
@Transactional
public class AdminModuleHibernateDao extends GenericHibernateDao<AdminModule, Serializable> implements AdminModuleDao {

	@Override
	public AdminModule findWithModuleAndAdmin(String module, Admin admin) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("module", module))
		.add(Restrictions.eq("admin", admin));
		return findObjectByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminModule> findWithModule(String module) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("module", module));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AdminModule> findWithAdmin(Admin admin) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("admin", admin))
		.addOrder(Order.asc("module"));
		return criteria.list();
	}

}
