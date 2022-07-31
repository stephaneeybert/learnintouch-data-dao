package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NewsStoryDao;
import com.thalasoft.learnintouch.data.dao.domain.NewsEditor;
import com.thalasoft.learnintouch.data.dao.domain.NewsHeading;
import com.thalasoft.learnintouch.data.dao.domain.NewsPaper;
import com.thalasoft.learnintouch.data.dao.domain.NewsStory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class NewsStoryHibernateDao extends GenericHibernateDao<NewsStory, Serializable> implements NewsStoryDao {

	@Override
	public NewsStory findWithListOrder(NewsPaper newsPaper, NewsHeading newsHeading, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsPaper", newsPaper));
		if (newsHeading != null) {
			criteria.add(Restrictions.eq("newsHeading", newsHeading));
		} else {
			criteria.add(Restrictions.isNull("newsHeading"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (NewsStory) criteria.uniqueResult();
	}

	@Override
	public NewsStory findNextWithListOrder(NewsPaper newsPaper, NewsHeading newsHeading, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsPaper", newsPaper));
		if (newsHeading != null) {
			criteria.add(Restrictions.eq("newsHeading", newsHeading));
		} else {
			criteria.add(Restrictions.isNull("newsHeading"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder));
		criteria.addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (NewsStory) criteria.uniqueResult();
	}

	@Override
	public NewsStory findPreviousWithListOrder(NewsPaper newsPaper, NewsHeading newsHeading, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("newsPaper", newsPaper));
		if (newsHeading != null) {
			criteria.add(Restrictions.eq("newsHeading", newsHeading));
		} else {
			criteria.add(Restrictions.isNull("newsHeading"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder));
		criteria.addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (NewsStory) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(NewsPaper newsPaper, NewsHeading newsHeading) {
		String statement = "select count(distinct ns1.id) as count from NewsStory ns1, NewsStory ns2 where ns1.id != ns2.id and ns1.listOrder = ns2.listOrder and ns1.newsPaper.id = ns2.newsPaper.id and ns1.newsPaper.id = :newsPaperId and ";
		if (newsHeading != null) {
			statement += "ns1.newsHeading.id = ns2.newsHeading.id and ns1.newsHeading.id = :newsHeadingId";
		} else {
			statement += "ns1.newsHeading.id is null and ns2.newsHeading.id is null";
		}
		Query query = getSession().createQuery(statement);
		query.setLong("newsPaperId", newsPaper.getId());
		if (newsHeading != null) {
			query.setLong("newsHeadingId", newsHeading.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public Page<NewsStory> findWithNewsPaper(NewsPaper newsPaper, int pageNumber, int pageSize) {
		String statement = "select distinct ns.* from NewsStory ns left join NewsHeading nh on ns.newsHeading.id = nh.id where ns.newsPaper.id = :newsPaperId order by nh.listOrder, ns.listOrder";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("newsPaperId", newsPaper.getId());
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@Override
	public Page<NewsStory> findWithNewsHeading(NewsHeading newsHeading, int pageNumber, int pageSize) {
		String statement = "select * from NewsStory where ";
		if (newsHeading != null) {
			statement += "newsHeading.id = :newsHeadingId ";
		} else {
			statement += "newsHeading.id is null ";
		}
		statement += "order by listOrder";
		Map<String, Object> parameters = new HashMap<String, Object>();
		if (newsHeading != null) {
			parameters.put("newsHeadingId", newsHeading.getId());
		}
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@Override
	public Page<NewsStory> findWithNewsEditor(NewsEditor newsEditor, int pageNumber, int pageSize) {
		String statement = "select distinct ns.* from NewsStory ns left join NewsHeading nh on ns.newsHeading.id = nh.id where ";
		if (newsEditor != null) {
			statement += "newsEditor.id = :newsEditorId ";
		} else {
			statement += "newsEditor.id is null ";
		}
		statement += "order by nh.listOrder, ns.listOrder";
		Map<String, Object> parameters = new HashMap<String, Object>();
		if (newsEditor != null) {
			parameters.put("newsEditorId", newsEditor.getId());
		}
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@Override
	public Page<NewsStory> findWithNewsPaperAndNewsHeading(NewsPaper newsPaper, NewsHeading newsHeading, int pageNumber, int pageSize) {
		String statement = "select * from NewsStory where ns.newsPaper.id = :newsPaperId and (ns.newsHeading.id = nh.id or ns.newsHeading.id is null) ";
		if (newsHeading != null) {
			statement += "newsHeading.id = :newsHeadingId ";
		} else {
			statement += "newsHeading.id is null ";
		}
		statement += "order by listOrder";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("newsPaperId", newsPaper.getId());
		if (newsHeading != null) {
			parameters.put("newsHeadingId", newsHeading.getId());
		}
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsStory> findWithNewsPaperAndNewsHeadingOrderById(NewsPaper newsPaper, NewsHeading newsHeading) {
		String statement = "select * from NewsStory where ns.newsPaper.id = :newsPaperId and (ns.newsHeading.id = nh.id or ns.newsHeading.id is null) ";
		if (newsHeading != null) {
			statement += "newsHeading.id = :newsHeadingId ";
		} else {
			statement += "newsHeading.id is null ";
		}
		statement += "order by id";
		Query query = getSession().createQuery(statement);
		query.setLong("newsPaperId", newsPaper.getId());
		if (newsHeading != null) {
			query.setLong("newsHeadingId", newsHeading.getId());
		}
		return query.list();
	}

	@Override
	public Page<NewsStory> findWithNewsPaperAndNewsEditor(NewsPaper newsPaper, NewsEditor newsEditor, int pageNumber, int pageSize) {
		String statement = "select distinct ns.* from NewsStory ns left join newsHeading nh on ns.newsHeading.id = nh.id where ns.newsPaper.id = :newsPaperId ";
		if (newsEditor != null) {
			statement += "newsEditor.id = :newsEditorId ";
		} else {
			statement += "newsEditor.id is null ";
		}
		statement += "order by nh.listOrder, ns.listOrder";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("newsPaperId", newsPaper.getId());
		if (newsEditor != null) {
			parameters.put("newsEditorId", newsEditor.getId());
		}
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@Override
	public Page<NewsStory> findWithNewsHeadingAndNewsEditor(NewsHeading newsHeading, NewsEditor newsEditor, int pageNumber, int pageSize) {
		String statement = "select * from NewsStory where ";
		if (newsHeading != null) {
			statement += "newsHeading.id = :newsHeadingId ";
		} else {
			statement += "newsHeading.id is null ";
		}
		if (newsEditor != null) {
			statement += "newsEditor.id = :newsEditorId ";
		} else {
			statement += "newsEditor.id is null ";
		}
		statement += "order by listOrder";
		Map<String, Object> parameters = new HashMap<String, Object>();
		if (newsHeading != null) {
			parameters.put("newsHeadingId", newsHeading.getId());
		}
		if (newsEditor != null) {
			parameters.put("newsEditorId", newsEditor.getId());
		}
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@Override
	public Page<NewsStory> findWithNewsPaperAndNewsHeadingAndNewsEditor(NewsPaper newsPaper, NewsHeading newsHeading, NewsEditor newsEditor, int pageNumber, int pageSize) {
		String statement = "select * from NewsStory where newsPaper.id = :newsPaperId and ";
		if (newsHeading != null) {
			statement += "newsHeading.id = :newsHeadingId ";
		} else {
			statement += "newsHeading.id is null ";
		}
		if (newsEditor != null) {
			statement += "newsEditor.id = :newsEditorId ";
		} else {
			statement += "newsEditor.id is null ";
		}
		statement += "order by listOrder";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("newsPaperId", newsPaper.getId());
		if (newsHeading != null) {
			parameters.put("newsHeadingId", newsHeading.getId());
		}
		if (newsEditor != null) {
			parameters.put("newsEditorId", newsEditor.getId());
		}
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@Override
	public Page<NewsStory> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion headline = Restrictions.ilike("headline", pattern);
		Criterion excerpt = Restrictions.ilike("excerpt", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(headline).add(excerpt);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("headline"));
		Page<NewsStory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public List<NewsStory> findWithAudio(String audio) {
		Criterion criterion = Restrictions.eq("audio", audio);
		return findObjectsByCriteria(criterion);
	}

	@Override
	public Page<NewsStory> findWithNewsPaperAndCurrent(NewsPaper newsPaper, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		String statement = "select distinct ns.* from NewsStory ns left join newsHeading nh on ns.newsHeading.id = nh.id where ns.newsPaper.id = :newsPaperId "
			+ "and ((ns.releaseDate is not null and ns.archiveDate is not null and DATE(ns.releaseDate) <= DATE(':systemDate') and DATE(ns.archiveDate) >= DATE(':systemDate')) "
			+ "or (ns.releaseDate is not null and ns.archiveDate is null and DATE(ns.releaseDate) <= DATE(':systemDate')) "
			+ "or (ns.releaseDate is null and ns.archiveDate is not null and DATE(ns.archiveDate) >= DATE(':systemDate'))) "
			+ "order by nh.listOrder, ns.listOrder";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("newsPaperId", newsPaper.getId());
		parameters.put("systemDate", systemDateTime.toDateTime().toDate());
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@Override
	public Page<NewsStory> findWithNewsPaperAndArchived(NewsPaper newsPaper, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		String statement = "select * from NewsStory where newsPaper.id = :newsPaperId "
			+ "and archiveDate is not null and DATE(archiveDate) < DATE(':systemDate')) "
			+ "order by releaseDate desc";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("newsPaperId", newsPaper.getId());
		parameters.put("systemDate", systemDateTime.toDateTime().toDate());
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

	@Override
	public Page<NewsStory> findWithNewsPaperAndDeferred(NewsPaper newsPaper, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		String statement = "select * from NewsStory where newsPaper.id = :newsPaperId "
			+ "and releaseDate is not null and DATE(releaseDate) > DATE(':systemDate')) "
			+ "order by releaseDate desc";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("newsPaperId", newsPaper.getId());
		parameters.put("systemDate", systemDateTime.toDateTime().toDate());
		Page<NewsStory> page = getPage(pageNumber, pageSize, statement, parameters, getSession());
		return page;
	}

}
