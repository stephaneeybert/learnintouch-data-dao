package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ElearningSessionDao;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ElearningSessionHibernateDao extends GenericHibernateDao<ElearningSession, Serializable> implements ElearningSessionDao {

	@Override
	public Page<ElearningSession> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.desc("openingDate"));
		Page<ElearningSession> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ElearningSession> findWithName(String name, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("name", name));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("openingDate"));
		Page<ElearningSession> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningSession> findNotYetOpened(LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("closed", false));
        conjunction.add(Restrictions.isNotNull("openingDate")).add(Restrictions.gt("openingDate", systemDateTime));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("openingDate"));
		Page<ElearningSession> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningSession> findCurrentlyOpened(LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("closed", false));
        conjunction.add(Restrictions.isNotNull("openingDate")).add(Restrictions.le("openingDate", systemDateTime));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("openingDate"));
		Page<ElearningSession> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningSession> findClosed(LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		Disjunction disjunction = Restrictions.disjunction();
		Criterion closed = Restrictions.eq("closed", true);
		Conjunction dateConjunction = Restrictions.conjunction();
		dateConjunction.add(Restrictions.isNotNull("closingDate")).add(Restrictions.gt("closingDate", systemDateTime));
		disjunction.add(closed).add(dateConjunction);
		conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("openingDate"));
		Page<ElearningSession> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ElearningSession> findNotClosed(LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		Criterion closed = Restrictions.eq("closed", false);
		Conjunction dateConjunction = Restrictions.conjunction();
		dateConjunction.add(Restrictions.isNotNull("closingDate")).add(Restrictions.ge("closingDate", systemDateTime));
		Criterion nullClose = Restrictions.isNull("closingDate");
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(dateConjunction).add(nullClose);
		conjunction.add(disjunction);
		conjunction.add(closed);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("openingDate"));
		Page<ElearningSession> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
