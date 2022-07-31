package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.MailDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Mail;
import com.thalasoft.learnintouch.data.dao.domain.MailCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class MailHibernateDao extends GenericHibernateDao<Mail, Serializable> implements MailDao {

	private static final String DB_TABLE_MAIL_CATEGORY = "mailCategory";
	private static final String DB_TABLE_ADMIN = "admin";

	@Override
	public Page<Mail> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("subject"));
		Page<Mail> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Mail> findWithCategory(MailCategory mailCategory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (mailCategory != null) {
		    conjunction.add(Restrictions.eq("mailCategory", mailCategory));
		} else {
		    conjunction.add(Restrictions.isNull("mailCategory"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("subject"));
		Page<Mail> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Mail> findWithAdmin(Admin admin, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (admin != null) {
		    conjunction.add(Restrictions.eq("admin", admin));
		} else {
		    conjunction.add(Restrictions.isNull("admin"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("subject"));
		Page<Mail> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Mail> findWithAdminAndCategory(Admin admin, MailCategory mailCategory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (admin != null) {
		    conjunction.add(Restrictions.eq("admin", admin));
		} else {
		    conjunction.add(Restrictions.isNull("admin"));
		}
		if (mailCategory != null) {
		    conjunction.add(Restrictions.eq("mailCategory", mailCategory));
		} else {
		    conjunction.add(Restrictions.isNull("mailCategory"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("subject"));
		Page<Mail> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Mail> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "m");
		criteria.createAlias(DB_TABLE_ADMIN, "a", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias(DB_TABLE_MAIL_CATEGORY, "mc", CriteriaSpecification.LEFT_JOIN);
		Criterion description = Restrictions.ilike("m.description", pattern);
		Criterion subject = Restrictions.ilike("m.subject", pattern);
		Criterion body = Restrictions.ilike("m.body", pattern);
		Criterion firstname = Restrictions.ilike("a.firstname", pattern);
		Criterion lastname = Restrictions.ilike("a.lastname", pattern);
		Criterion login = Restrictions.ilike("a.login", pattern);
		Criterion email = Restrictions.ilike("a.email", pattern);
		Criterion name = Restrictions.ilike("mc.name", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(description).add(subject).add(body).add(firstname).add(lastname).add(login).add(email).add(name);
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("m.subject"));
		Page<Mail> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Mail> findWithBodyLikeImage(String image, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.ilike("body", "%" + image + "%"));
        criteria.add(conjunction);
		Page<Mail> page = getPage(pageNumber, pageSize, criteria);
		return page;
	}

	@Override
	public Page<Mail> findWithAttachmentsLikeFile(String file, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.ilike("attachments", "%" + file + "%"));
        criteria.add(conjunction);
		Page<Mail> page = getPage(pageNumber, pageSize, criteria);
		return page;
	}

	@Override
	public long deleteByDate(LocalDateTime sinceDateTime) {
		Query query = getSession().createQuery("delete from Mail where sendDatetime is not null and DATE(sendDatetime) >= DATE(?)");
		query.setTimestamp(0, sinceDateTime.toDateTime().toDate());
		return query.executeUpdate();
	}

}
