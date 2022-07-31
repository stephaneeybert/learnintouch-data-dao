package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.SmsListUserDao;
import com.thalasoft.learnintouch.data.dao.domain.SmsList;
import com.thalasoft.learnintouch.data.dao.domain.SmsListUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

@Repository
@Transactional
public class SmsListUserHibernateDao extends GenericHibernateDao<SmsListUser, Serializable> implements SmsListUserDao {

	private static final String DB_TABLE_USER_ACCOUNT = "userAccount";

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsListUser> findWithSmsList(SmsList smsList) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "slu");
		criteria.createAlias(DB_TABLE_USER_ACCOUNT, "u", CriteriaSpecification.INNER_JOIN);
		criteria.add(Restrictions.eq("slu.mailList", smsList));
		criteria.addOrder(Order.asc("u.firstname")).addOrder(Order.asc("u.lastname")).addOrder(Order.asc("u.email"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsListUser> findWithUser(UserAccount userAccount) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("userAccount", userAccount));
		return criteria.list();
	}

	@Override
	public SmsListUser findWithSmsListAndUser(SmsList smsList, UserAccount userAccount) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("smsList", smsList));
		criteria.add(Restrictions.eq("userAccount", userAccount));
		return (SmsListUser) criteria.uniqueResult();
	}

	@Override
	public long deleteBySmsList(SmsList smsList) {
		Query query = getSession().createQuery("delete from SmsListUser where smsList.id = ?");
		query.setLong(0, smsList.getId());
		return query.executeUpdate();
	}

}
