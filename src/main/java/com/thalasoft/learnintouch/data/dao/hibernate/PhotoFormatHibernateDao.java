package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PhotoFormatDao;
import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class PhotoFormatHibernateDao extends GenericHibernateDao<PhotoFormat, Serializable> implements PhotoFormatDao {

	@Override
	public Page<PhotoFormat> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<PhotoFormat> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public PhotoFormat findWithName(String name) {
		Criterion criteria = Restrictions.eq("name", name);
		return findObjectByCriteria(criteria);
	}

}
