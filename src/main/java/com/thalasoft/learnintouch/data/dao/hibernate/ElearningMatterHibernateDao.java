package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningMatterDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningMatter;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningMatterHibernateDao extends GenericHibernateDao<ElearningMatter, Serializable> implements ElearningMatterDao {

	@Override
	public Page<ElearningMatter> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ElearningMatter> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
