package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.RssFeedLanguageDao;
import com.thalasoft.learnintouch.data.dao.domain.RssFeed;
import com.thalasoft.learnintouch.data.dao.domain.RssFeedLanguage;

@Repository
@Transactional
public class RssFeedLanguageHibernateDao extends GenericHibernateDao<RssFeedLanguage, Serializable> implements RssFeedLanguageDao {

	@SuppressWarnings("unchecked")
	public List<RssFeedLanguage> findWithRssFeed(RssFeed rssFeed) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("rssFeed", rssFeed)).addOrder(Order.asc("languageCode"));
		criteria.setMaxResults(1);
		return criteria.list();
	}

	public RssFeedLanguage findWithRssFeedAndLanguage(RssFeed rssFeed, String languageCode) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("rssFeed", rssFeed)).add(Restrictions.eq("languageCode", languageCode));
		criteria.setMaxResults(1);
		return (RssFeedLanguage) criteria.uniqueResult();
	}

	public RssFeedLanguage findWithRssFeedAndNoLanguage(RssFeed rssFeed) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("rssFeed", rssFeed));
		criteria.add(Restrictions.or(Restrictions.isNull("languageCode"), Restrictions.eq("languageCode", "")));
		criteria.setMaxResults(1);
		return (RssFeedLanguage) criteria.uniqueResult();
	}

}
