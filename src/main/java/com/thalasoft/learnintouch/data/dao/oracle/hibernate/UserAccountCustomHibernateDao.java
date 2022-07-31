package com.thalasoft.learnintouch.data.dao.oracle.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.UserAccountHibernateDao;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class UserAccountCustomHibernateDao extends UserAccountHibernateDao {

	@Override
	public Page<UserAccount> findWithCreationDateTime(LocalDateTime fromDateTime, LocalDateTime toDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-dd");
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.sqlRestriction("to_char(creation_datetime, 'YYYY-MM-DD') between '" + dateTimeFormatter.print(fromDateTime) + "' and '" + dateTimeFormatter.print(toDateTime) + "'"));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<UserAccount> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
