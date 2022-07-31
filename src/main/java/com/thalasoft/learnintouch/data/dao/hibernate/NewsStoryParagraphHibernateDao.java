package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NewsStoryParagraphDao;
import com.thalasoft.learnintouch.data.dao.domain.NewsStory;
import com.thalasoft.learnintouch.data.dao.domain.NewsStoryParagraph;

@Repository
@Transactional
public class NewsStoryParagraphHibernateDao extends GenericHibernateDao<NewsStoryParagraph, Serializable> implements NewsStoryParagraphDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsStoryParagraph> findWithNewsStory(NewsStory newsStory) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsStory", newsStory));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

}
