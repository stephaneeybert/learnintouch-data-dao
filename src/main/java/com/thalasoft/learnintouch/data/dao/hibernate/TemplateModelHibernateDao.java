package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.TemplateModelDao;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class TemplateModelHibernateDao extends GenericHibernateDao<TemplateModel, Serializable> implements TemplateModelDao {

	@Override
	public Page<TemplateModel> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<TemplateModel> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public TemplateModel findWithName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name));
		criteria.addOrder(Order.asc("id"));
		return findObjectByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateModel> findWithParent(TemplateModel parent) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("parent", parent));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateModel> findWithNoParent() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.isNull("parent"));
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}

}
