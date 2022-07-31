package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.GuestbookEntryDao;
import com.thalasoft.learnintouch.data.dao.domain.GuestbookEntry;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class GuestbookEntryHibernateDao extends GenericHibernateDao<GuestbookEntry, Serializable> implements GuestbookEntryDao {

	@Override
	public Page<GuestbookEntry> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.desc("publicationDatetime"));
		Page<GuestbookEntry> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<GuestbookEntry> findWithUser(UserAccount userAccount, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.or(Restrictions.eq("userAccount", userAccount), Restrictions.isNull("userAccount")));
        OrderList orderList = new OrderList().add(Order.desc("publicationDatetime"));
		Page<GuestbookEntry> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
}
