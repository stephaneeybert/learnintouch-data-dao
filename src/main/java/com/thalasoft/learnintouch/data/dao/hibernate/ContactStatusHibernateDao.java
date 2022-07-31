package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ContactStatusDao;
import com.thalasoft.learnintouch.data.dao.domain.ContactStatus;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ContactStatusHibernateDao extends GenericHibernateDao<ContactStatus, Serializable> implements ContactStatusDao {

	@Override
	public Page<ContactStatus> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<ContactStatus> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactStatus> findAllOrderById() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public ContactStatus findFirst() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.addOrder(Order.asc("listOrder")).setFirstResult(0).setMaxResults(1);
		return (ContactStatus) criteria.uniqueResult();
	}

	@Override
	public ContactStatus findWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ContactStatus) criteria.uniqueResult();
	}

	@Override
	public ContactStatus findNextWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ContactStatus) criteria.uniqueResult();
	}

	@Override
	public ContactStatus findPreviousWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ContactStatus) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates() {
		Query query = getSession().createQuery("select count(distinct cs1.id) as count from ContactStatus cs1, ContactStatus cs2 where cs1.id != cs2.id and cs1.listOrder = cs2.listOrder");
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
