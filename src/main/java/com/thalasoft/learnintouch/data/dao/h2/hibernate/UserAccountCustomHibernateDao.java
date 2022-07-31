package com.thalasoft.learnintouch.data.dao.h2.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.UserAccountDao;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.UserAccountHibernateDao;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class UserAccountCustomHibernateDao extends UserAccountHibernateDao implements UserAccountDao {

	@Override
	public Page<UserAccount> findWithCreationDateTime(LocalDateTime fromDateTime, LocalDateTime toDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.sqlRestriction("formatdatetime(creation_datetime, 'yyyy/MM/dd') between formatdatetime('" + fromDateTime + "', 'yyyy/MM/dd') and formatdatetime('" + toDateTime + "', 'yyyy/MM/dd')"));
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
