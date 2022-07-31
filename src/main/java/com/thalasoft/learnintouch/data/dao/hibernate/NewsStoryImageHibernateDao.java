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

import com.thalasoft.learnintouch.data.dao.NewsStoryImageDao;
import com.thalasoft.learnintouch.data.dao.domain.NewsStory;
import com.thalasoft.learnintouch.data.dao.domain.NewsStoryImage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class NewsStoryImageHibernateDao extends GenericHibernateDao<NewsStoryImage, Serializable> implements NewsStoryImageDao {

	@Override
	public NewsStoryImage findWithListOrder(NewsStory newsStory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsStory", newsStory));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (NewsStoryImage) criteria.uniqueResult();
	}

	@Override
	public NewsStoryImage findNextWithListOrder(NewsStory newsStory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsStory", newsStory));
		criteria.add(Restrictions.gt("listOrder", listOrder));
		criteria.addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (NewsStoryImage) criteria.uniqueResult();
	}

	@Override
	public NewsStoryImage findPreviousWithListOrder(NewsStory newsStory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsStory", newsStory));
		criteria.add(Restrictions.lt("listOrder", listOrder));
		criteria.addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (NewsStoryImage) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(NewsStory newsStory) {
		String statement = "select count(distinct nsi1.id) as count from NewsStoryImage nsi1, NewsStoryImage nsi2 where nsi1.id != nsi2.id and nsi1.listOrder = nsi2.listOrder and nsi1.newsStory.id = nsi2.newsStory.id and nsi1.newsStory.id = :newsStoryId";
		Query query = getSession().createQuery(statement);
		query.setLong("newsStoryId", newsStory.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public Page<NewsStoryImage> findWithNewsStory(NewsStory newsStory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("newsStory", newsStory));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<NewsStoryImage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsStoryImage> findWithNewsStoryOrderById(NewsStory newsStory) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsStory", newsStory));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsStoryImage> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}
	
}
