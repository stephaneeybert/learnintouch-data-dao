package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NewsHeadingDao;
import com.thalasoft.learnintouch.data.dao.domain.NewsHeading;
import com.thalasoft.learnintouch.data.dao.domain.NewsPublication;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class NewsHeadingHibernateDao extends GenericHibernateDao<NewsHeading, Serializable> implements NewsHeadingDao {

	@Override
	public Page<NewsHeading> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<NewsHeading> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public NewsHeading findWithListOrder(NewsPublication newsPublication, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsPublication", newsPublication));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (NewsHeading) criteria.uniqueResult();
	}

	@Override
	public NewsHeading findNextWithListOrder(NewsPublication newsPublication, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsPublication", newsPublication));
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (NewsHeading) criteria.uniqueResult();
	}

	@Override
	public NewsHeading findPreviousWithListOrder(NewsPublication newsPublication, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsPublication", newsPublication));
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (NewsHeading) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(NewsPublication newsPublication) {
		Query query = getSession().createQuery("select count(distinct nh1.id) as count from NewsHeading nh1, NewsHeading nh2 where nh1.id != nh2.id and nh1.listOrder = nh2.listOrder and nh1.newsPublication.id = nh2.newsPublication.id and nh1.newsPublication.id = :newsPublicationId");
		query.setLong("newsPublicationId", newsPublication.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}
	
	@Override
	public Page<NewsHeading> findWithNewsPublication(NewsPublication newsPublication, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (newsPublication != null) {
		    conjunction.add(Restrictions.eq("newsPublication", newsPublication));
		} else {
		    conjunction.add(Restrictions.isNull("newsPublication"));
		}
        criteria.add(conjunction);
		criteria.addOrder(Order.asc("listOrder"));
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<NewsHeading> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsHeading> findWithNewsPublicationOrderById(NewsPublication newsPublication) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (newsPublication != null) {
			criteria.add(Restrictions.eq("newsPublication", newsPublication));
		} else {
			criteria.add(Restrictions.isNull("newsPublication"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsHeading> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

}
