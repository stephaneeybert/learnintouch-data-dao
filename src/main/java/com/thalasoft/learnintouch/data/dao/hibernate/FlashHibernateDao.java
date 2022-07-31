package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.FlashDao;
import com.thalasoft.learnintouch.data.dao.domain.Flash;

@Repository
@Transactional
public class FlashHibernateDao extends GenericHibernateDao<Flash, Serializable> implements FlashDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Flash> findAll() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.addOrder(Order.asc("filename"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flash> findWithFilename(String filename) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("filename", filename));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Flash> findWithWddx(String wddx) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("wddx", wddx));
		return criteria.list();
	}

}
