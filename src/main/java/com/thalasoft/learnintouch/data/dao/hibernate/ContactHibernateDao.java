package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ContactDao;
import com.thalasoft.learnintouch.data.dao.domain.Contact;
import com.thalasoft.learnintouch.data.dao.domain.ContactReferer;
import com.thalasoft.learnintouch.data.dao.domain.ContactStatus;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ContactHibernateDao extends GenericHibernateDao<Contact, Serializable> implements ContactDao {

	@Override
	public Page<Contact> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.desc("contactDatetime"));
		Page<Contact> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Contact> findInGarbage(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("garbage", true));
        OrderList orderList = new OrderList().add(Order.desc("contactDatetime"));
		Page<Contact> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Contact> findNotInGarbage(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("garbage", false));
        OrderList orderList = new OrderList().add(Order.desc("contactDatetime"));
		Page<Contact> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Contact> findNotInGarbageByStatus(ContactStatus contactStatus, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.and(Restrictions.eq("garbage", false), Restrictions.eq("contactStatus", contactStatus));
		criteria.add(criterion);		
        OrderList orderList = new OrderList().add(Order.desc("contactDatetime"));
		Page<Contact> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Contact> findWithStatus(ContactStatus contactStatus, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.eq("contactStatus", contactStatus); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.desc("contactDatetime"));
		Page<Contact> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Contact> findWithReferer(ContactReferer contactReferer, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.eq("contactReferer", contactReferer);
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.desc("contactDatetime"));
		Page<Contact> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public long deleteByDate(LocalDateTime sinceDate) {
		Query query = getSession().createQuery("delete from Contact where contactDatetime is not null and contactDatetime >= ?");
		query.setDate(0, sinceDate.toDateTime().toDate());
		return query.executeUpdate();
	}

}
