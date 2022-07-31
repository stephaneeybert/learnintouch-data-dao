package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.SmsListNumberDao;
import com.thalasoft.learnintouch.data.dao.domain.SmsList;
import com.thalasoft.learnintouch.data.dao.domain.SmsListNumber;
import com.thalasoft.learnintouch.data.dao.domain.SmsNumber;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class SmsListNumberHibernateDao extends GenericHibernateDao<SmsListNumber, Serializable> implements SmsListNumberDao {

	private static final String DB_TABLE_SMS_NUMBER = "smsNumber";

	@Override
	public Page<SmsListNumber> findWithSmsList(SmsList smsList, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "sln");
		criteria.createAlias(DB_TABLE_SMS_NUMBER, "sn", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("sln.mailList", smsList));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("sn.firstname")).add(Order.asc("sn.lastname")).add(Order.asc("sn.mobilePhone"));
		Page<SmsListNumber> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<SmsListNumber> findWithSmsNumber(SmsNumber smsNumber, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("smsNumber", smsNumber));
        OrderList orderList = new OrderList().add(Order.asc("sn.firstname")).add(Order.asc("sn.lastname")).add(Order.asc("sn.mobilePhone"));
		Page<SmsListNumber> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public SmsListNumber findWithSmsListAndSmsNumber(SmsList smsList, SmsNumber smsNumber) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("smsList", smsList));
		criteria.add(Restrictions.eq("smsNumber", smsNumber));
		return (SmsListNumber) criteria.uniqueResult();
	}
	
	@Override
	public long deleteBySmsList(SmsList smsList) {
		Query query = getSession().createQuery("delete from SmsListNumber where smsList.id = ?");
		query.setLong(0, smsList.getId());
		return query.executeUpdate();
	}

}
