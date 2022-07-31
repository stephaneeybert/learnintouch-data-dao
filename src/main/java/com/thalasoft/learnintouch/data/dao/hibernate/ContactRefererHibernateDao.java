package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ContactRefererDao;
import com.thalasoft.learnintouch.data.dao.domain.ContactReferer;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ContactRefererHibernateDao extends GenericHibernateDao<ContactReferer, Serializable> implements ContactRefererDao {

	@Override
	public Page<ContactReferer> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<ContactReferer> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactReferer> findAllOrderById() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public ContactReferer findFirst() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.addOrder(Order.asc("listOrder"));
		criteria.setFirstResult(0).setMaxResults(1);
		return (ContactReferer) criteria.uniqueResult();
	}

	@Override
	public ContactReferer findWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ContactReferer) criteria.uniqueResult();
	}

	@Override
	public ContactReferer findNextWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ContactReferer) criteria.uniqueResult();
	}

	@Override
	public ContactReferer findPreviousWithListOrder(int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ContactReferer) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates() {
		Query query = getSession().createQuery("select count(distinct cr1.id) as count from ContactReferer cr1, ContactReferer cr2 where cr1.id != cr2.id and cr1.listOrder = cr2.listOrder");
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
