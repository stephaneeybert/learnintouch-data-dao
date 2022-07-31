package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningResultRangeDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningResultRange;

@Repository
@Transactional
public class ElearningResultRangeHibernateDao extends GenericHibernateDao<ElearningResultRange, Serializable> implements ElearningResultRangeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ElearningResultRange> findAll() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.addOrder(Order.asc("max"));
		return criteria.list();
	}

}
