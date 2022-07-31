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

import com.thalasoft.learnintouch.data.dao.domain.MailAddress;
import com.thalasoft.learnintouch.data.dao.hibernate.MailAddressHibernateDao;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class MailAddressCustomHibernateDao extends MailAddressHibernateDao {

	@Override
	public Page<MailAddress> findWithCreationDateTimeBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-dd");
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.sqlRestriction("to_char(creation_datetime, 'YYYY-MM-DD') >= '" + dateTimeFormatter.print(fromDateTime) + "' and to_char(creation_datetime, 'YYYY-MM-DD') <= '" + dateTimeFormatter.print(toDateTime) + "'"));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
