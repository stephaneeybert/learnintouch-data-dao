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

import com.thalasoft.learnintouch.data.dao.MailListUserDao;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.MailListUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

@Repository
@Transactional
public class MailListUserHibernateDao extends GenericHibernateDao<MailListUser, Serializable> implements MailListUserDao {

	private static final String DB_TABLE_USER_ACCOUNT = "userAccount";

	@SuppressWarnings("unchecked")
	@Override
	public List<MailListUser> findWithUser(UserAccount userAccount) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("userAccount", userAccount));
		return criteria.list();
	}
	
	@Override
	public long deleteByMailList(MailList mailList) {
		Query query = getSession().createQuery("delete from MailListUser where mailList.id = ?");
		query.setLong(0, mailList.getId());
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MailListUser> findWithMailList(MailList mailList) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "mlu");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("mlu.mailList", mailList));
		criteria.addOrder(Order.asc("u.firstname")).addOrder(Order.asc("u.lastname")).addOrder(Order.asc("u.email"));
		return criteria.list();
	}
	
	@Override
	public MailListUser findWithMailListAndUser(MailList mailList, UserAccount userAccount) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("mailList", mailList));
		criteria.add(Restrictions.eq("userAccount", userAccount));
		return (MailListUser) criteria.uniqueResult();
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<MailListUser> findWithMailListAndSubscriberLikePattern(MailList mailList, String searchPattern) {
        Criteria criteria = getSession().createCriteria(getPersistentClass(), "mlu");
        criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
        String pattern = "%" + searchPattern + "%";
        Criterion firstname = Restrictions.ilike("u.firstname", pattern);
        Criterion lastname = Restrictions.ilike("u.lastname", pattern);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(firstname).add(lastname);
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
        Criterion homePhone = Restrictions.ilike("u.homePhone", pattern);
        Criterion workPhone = Restrictions.ilike("u.workPhone", pattern);
        Criterion mobilePhone = Restrictions.ilike("u.mobilePhone", pattern);
        Criterion fax = Restrictions.ilike("u.fax", pattern);
        disjunction.add(homePhone);
        disjunction.add(workPhone);
        disjunction.add(mobilePhone);
        disjunction.add(fax);
        criteria.add(Restrictions.eq("u.mail_subscribe", true));
        criteria.add(Restrictions.eq("mlu.mailList", mailList));
        criteria.addOrder(Order.asc("u.firstname")).addOrder(Order.asc("u.lastname")).addOrder(Order.asc("u.email"));
        return criteria.list();
    }
    
}
