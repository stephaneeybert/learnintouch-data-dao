package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NewsEditorDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.NewsEditor;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class NewsEditorHibernateDao extends GenericHibernateDao<NewsEditor, Serializable> implements NewsEditorDao {

	private static final String DB_TABLE_ADMIN = "admin";

	@Override
	public NewsEditor findWithAdmin(Admin admin) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (admin != null) {
			criteria.add(Restrictions.eq("admin", admin));
		} else {
			criteria.add(Restrictions.isNull("admin"));
		}
		criteria.setMaxResults(1);
		return (NewsEditor) criteria.uniqueResult();
	}

	@Override
	public Page<NewsEditor> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "ne");
		criteria.createAlias(DB_TABLE_ADMIN, "a", CriteriaSpecification.INNER_JOIN).createAlias("smsCategory", "c", CriteriaSpecification.LEFT_JOIN);
        OrderList orderList = new OrderList().add(Order.asc("a.firstname")).add(Order.asc("a.lastname"));
		Page<NewsEditor> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
