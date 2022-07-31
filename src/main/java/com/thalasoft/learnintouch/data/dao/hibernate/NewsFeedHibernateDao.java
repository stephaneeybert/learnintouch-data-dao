package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NewsFeedDao;
import com.thalasoft.learnintouch.data.dao.domain.NewsFeed;
import com.thalasoft.learnintouch.data.dao.domain.NewsPaper;

@Repository
@Transactional
public class NewsFeedHibernateDao extends GenericHibernateDao<NewsFeed, Serializable> implements NewsFeedDao {

	@Override
	public NewsFeed findWithNewsPaper(NewsPaper newsPaper) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsPaper", newsPaper));
		criteria.setMaxResults(1);
		return (NewsFeed) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsFeed> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

}
