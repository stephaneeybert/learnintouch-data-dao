package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningScoringDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningScoring;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningScoringHibernateDao extends GenericHibernateDao<ElearningScoring, Serializable> implements ElearningScoringDao {

	@Override
	public Page<ElearningScoring> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningScoring> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
