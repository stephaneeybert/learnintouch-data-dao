package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.MailListAddressDao;
import com.thalasoft.learnintouch.data.dao.domain.MailAddress;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.MailListAddress;

@Repository
@Transactional
public class MailListAddressHibernateDao extends GenericHibernateDao<MailListAddress, Serializable> implements MailListAddressDao {

    private static final String DB_TABLE_MAIL_ADDRESS = "mailAddress";

    @SuppressWarnings("unchecked")
    @Override
    public List<MailListAddress> findWithMailList(MailList mailList) {
        Criteria criteria = getSession().createCriteria(getPersistentClass(), "mla");
        criteria.createAlias(DB_TABLE_MAIL_ADDRESS, "ma", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("mla.mailList", mailList));
        criteria.addOrder(Order.asc("ma.firstname")).addOrder(Order.asc("ma.lastname")).addOrder(Order.asc("ma.email"));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MailListAddress> findWithMailAddress(MailAddress mailAddress) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("mailAddress", mailAddress));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MailListAddress> findWithMailListAndSubscriberLikePattern(MailList mailList, String searchPattern) {
        Criteria criteria = getSession().createCriteria(getPersistentClass(), "mla");
        criteria.createAlias(DB_TABLE_MAIL_ADDRESS, "ma", CriteriaSpecification.INNER_JOIN);
        criteria.add(Restrictions.eq("mla.mailList", mailList));
        String pattern = "%" + searchPattern + "%";
        Criterion firstname = Restrictions.ilike("ma.firstname", pattern);
        Criterion lastname = Restrictions.ilike("ma.lastname", pattern);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(firstname).add(lastname);
        if (searchPattern.contains(" ")) {
            String[] pieces = searchPattern.split(" ");
            if (pieces[0] != null) {
                Criterion firstnameBis = Restrictions.ilike("ma.firstname", pieces[0]);
                disjunction.add(firstnameBis);
            }
            if (pieces[1] != null) {
                Criterion lastnameBis = Restrictions.ilike("ma.lastname", pieces[1]);
                disjunction.add(lastnameBis);
            }
        }
        Criterion email = Restrictions.ilike("ma.email", pattern);
        Criterion textComment = Restrictions.ilike("ma.text_comment", pattern);
        Criterion country = Restrictions.ilike("ma.country", pattern);
        disjunction.add(email).add(textComment).add(country);
        criteria.add(Restrictions.eq("ma.subscribe", true));
        criteria.addOrder(Order.asc("ma.firstname")).addOrder(Order.asc("ma.lastname")).addOrder(Order.asc("ma.email"));
        return criteria.list();
    }

    @Override
    public MailListAddress findWithMailListAndMailAddress(MailList mailList, MailAddress mailAddress) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("mailList", mailList));
        criteria.add(Restrictions.eq("mailAddress", mailAddress));
        return (MailListAddress) criteria.uniqueResult();
    }

    @Override
    public long deleteByMailList(MailList mailList) {
        Query query = getSession().createQuery("delete from MailListAddress where mailList.id = ?");
        query.setLong(0, mailList.getId());
        return query.executeUpdate();
    }

}
