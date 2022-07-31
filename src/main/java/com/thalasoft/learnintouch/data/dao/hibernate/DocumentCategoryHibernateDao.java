package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.DocumentCategoryDao;
import com.thalasoft.learnintouch.data.dao.domain.DocumentCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class DocumentCategoryHibernateDao extends GenericHibernateDao<DocumentCategory, Serializable> implements DocumentCategoryDao {

	@Override
	public Page<DocumentCategory> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<DocumentCategory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentCategory> findAllOrderById() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public DocumentCategory findWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (DocumentCategory) criteria.uniqueResult();
	}

	@Override
	public DocumentCategory findNextWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (DocumentCategory) criteria.uniqueResult();
	}

	@Override
	public DocumentCategory findPreviousWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (DocumentCategory) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates() {
		Query query = getSession().createQuery("select count(distinct dc1.id) as count from DocumentCategory dc1, DocumentCategory dc2 where dc1.id != dc2.id and dc1.listOrder = dc2.listOrder");
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
