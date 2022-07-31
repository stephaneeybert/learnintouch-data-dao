package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.MailAddressDao;
import com.thalasoft.learnintouch.data.dao.domain.MailAddress;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class MailAddressHibernateDao extends GenericHibernateDao<MailAddress, Serializable> implements MailAddressDao {

	@Override
	public Page<MailAddress> findImported(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("imported", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<MailAddress> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public MailAddress findWithEmail(String email) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("email", email));
		return (MailAddress) criteria.uniqueResult();
	}

	@Override
	public Page<MailAddress> findSubscribersWithCountryLike(String searchPattern, int pageNumber, int pageSize) {
	    Criteria criteria = getSession().createCriteria(getPersistentClass());
		String pattern = "%" + searchPattern + "%";
		Criterion country = Restrictions.ilike("country", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(country);
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("subscribe", true));
		conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

    @Override
    public Page<MailAddress> findSubscriberWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        String pattern = "%" + searchPattern + "%";
        Criterion email = Restrictions.ilike("email", pattern);
        Criterion firstname = Restrictions.ilike("firstname", pattern);
        Criterion lastname = Restrictions.ilike("lastname", pattern);
        Criterion textComment = Restrictions.ilike("textComment", pattern);
        Criterion country = Restrictions.ilike("country", pattern);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(email).add(firstname).add(lastname).add(textComment).add(country);
        if (searchPattern.contains(" ")) {
            String[] pieces = searchPattern.split(" ");
            if (pieces[0] != null) {
                Criterion firstnameBis = Restrictions.ilike("u.firstname", pieces[0]);
                disjunction.add(firstnameBis);
            }
            if (pieces[1] != null) {
                Criterion lastnameBis = Restrictions.ilike("u.lastname", pieces[1]);
                disjunction.add(lastnameBis);
            }
        }
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("subscribe", true));
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
        Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
        return page;
    }
    
	@Override
	public Page<MailAddress> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion email = Restrictions.ilike("email", pattern);
		Criterion firstname = Restrictions.ilike("firstname", pattern);
		Criterion lastname = Restrictions.ilike("lastname", pattern);
		Criterion textComment = Restrictions.ilike("textComment", pattern);
		Criterion country = Restrictions.ilike("country", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(email).add(firstname).add(lastname).add(textComment).add(country);
        if (searchPattern.contains(" ")) {
            String[] pieces = searchPattern.split(" ");
            if (pieces[0] != null) {
                Criterion firstnameBis = Restrictions.ilike("u.firstname", pieces[0]);
                disjunction.add(firstnameBis);
            }
            if (pieces[1] != null) {
                Criterion lastnameBis = Restrictions.ilike("u.lastname", pieces[1]);
                disjunction.add(lastnameBis);
            }
        }
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<MailAddress> findWithCountryLike(String searchPattern, int pageNumber, int pageSize) {
	    Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		String pattern = "%" + searchPattern + "%";
		conjunction.add(Restrictions.ilike("country", pattern));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<MailAddress> findSubscribers(int pageNumber, int pageSize) {
	    Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.eq("subscribe", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<MailAddress> findNonSubscribers(int pageNumber, int pageSize) {
	    Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.ne("subscribe", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<MailAddress> findWithCreationDateTimeBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.sqlRestriction("DATE(creation_datetime) between DATE('" + fromDateTime + "') and DATE('" + toDateTime + "')"));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("firstname")).add(Order.asc("lastname")).add(Order.asc("email"));
		Page<MailAddress> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public long resetImport() {
		Query query = getSession().createQuery("update MailAddress set imported = false");
		return query.executeUpdate();
	}

	@Override
	public long deleteImported() {
		Query query = getSession().createQuery("delete from MailAddress where imported = true");
		return query.executeUpdate();
	}

	@Override
	public long countImported() {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("imported", true));
		criteria.setProjection(Projections.rowCount());
		return ((Long) criteria.uniqueResult()).longValue();
	}
	
}
