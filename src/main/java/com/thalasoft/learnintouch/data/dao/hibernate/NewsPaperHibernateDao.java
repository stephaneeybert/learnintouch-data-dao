package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NewsPaperDao;
import com.thalasoft.learnintouch.data.dao.domain.NewsPaper;
import com.thalasoft.learnintouch.data.dao.domain.NewsPublication;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class NewsPaperHibernateDao extends GenericHibernateDao<NewsPaper, Serializable> implements NewsPaperDao {

	private static final String DB_TABLE_NEWS_PUBLICATION = "newsPublication";
	private static int NB_RECENT = 50;
	
	@Override
	public Page<NewsPaper> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public NewsPaper findWithTitle(String title) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("title", title));
		criteria.setMaxResults(1);
		return (NewsPaper) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsPaper> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@Override
	public Page<NewsPaper> findWithNewsPublication(NewsPublication newsPublication, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("newsPublication", newsPublication));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion title = Restrictions.ilike("title", pattern);
		Criterion header = Restrictions.ilike("header", pattern);
		Criterion footer = Restrictions.ilike("footer", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(title).add(header).add(footer);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findCurrentWithPatternLike(String searchPattern, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion title = Restrictions.ilike("title", pattern);
		Criterion header = Restrictions.ilike("header", pattern);
		Criterion footer = Restrictions.ilike("footer", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(title).add(header).add(footer);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
		conjunction.add(Restrictions.eq("notPublished", false));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNotNull("releaseDate")).add(Restrictions.isNotNull("archiveDate")).add(Restrictions.le("releaseDate", systemDateTime)).add(
		Restrictions.ge("archiveDate", systemDateTime));
		Conjunction datesConjunction1 = Restrictions.conjunction();
		datesConjunction1.add(Restrictions.isNotNull("releaseDate")).add(Restrictions.isNull("archiveDate")).add(Restrictions.le("releaseDate", systemDateTime));
		Conjunction datesConjunction2 = Restrictions.conjunction();
		datesConjunction2.add(Restrictions.isNull("releaseDate")).add(Restrictions.isNotNull("archiveDate")).add(Restrictions.ge("archiveDate", systemDateTime));
		Disjunction datesDisjunction = Restrictions.disjunction();
		datesDisjunction.add(datesConjunction0).add(datesConjunction1).add(datesConjunction2);
        conjunction.add(datesDisjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findCurrentWithPatternInNewsPaperAndNewsPublicationLike(String searchPattern, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion title = Restrictions.ilike("npa.title", pattern);
		Criterion header = Restrictions.ilike("npa.header", pattern);
		Criterion footer = Restrictions.ilike("npa.footer", pattern);
		Criterion name = Restrictions.ilike("npu.name", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(title).add(header).add(footer).add(name);
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "npa");
		criteria.createAlias(DB_TABLE_NEWS_PUBLICATION, "npu", CriteriaSpecification.LEFT_JOIN);
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(disjunction);
		conjunction.add(Restrictions.eq("notPublished", false));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNotNull("npa.releaseDate")).add(Restrictions.isNotNull("npa.archiveDate")).add(Restrictions.le("npa.releaseDate", systemDateTime)).add(
		Restrictions.ge("npa.archiveDate", systemDateTime));
		Conjunction datesConjunction1 = Restrictions.conjunction();
		datesConjunction1.add(Restrictions.isNotNull("npa.releaseDate")).add(Restrictions.isNull("npa.archiveDate")).add(Restrictions.le("npa.releaseDate", systemDateTime));
		Conjunction datesConjunction2 = Restrictions.conjunction();
		datesConjunction2.add(Restrictions.isNull("npa.releaseDate")).add(Restrictions.isNotNull("npa.archiveDate")).add(Restrictions.ge("npa.archiveDate", systemDateTime));
		Disjunction datesDisjunction = Restrictions.disjunction();
		datesDisjunction.add(datesConjunction0).add(datesConjunction1).add(datesConjunction2);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("npa.releaseDate")).add(Order.asc("npa.title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findWithNewsPublicationAndCurrent(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("newsPublication", newsPublication));
        conjunction.add(Restrictions.eq("notPublished", false));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNotNull("releaseDate")).add(Restrictions.isNotNull("archiveDate")).add(Restrictions.le("releaseDate", systemDateTime)).add(
		Restrictions.ge("archiveDate", systemDateTime));
		Conjunction datesConjunction1 = Restrictions.conjunction();
		datesConjunction1.add(Restrictions.isNotNull("releaseDate")).add(Restrictions.isNull("archiveDate")).add(Restrictions.le("releaseDate", systemDateTime));
		Conjunction datesConjunction2 = Restrictions.conjunction();
		datesConjunction2.add(Restrictions.isNull("releaseDate")).add(Restrictions.isNotNull("archiveDate")).add(Restrictions.ge("archiveDate", systemDateTime));
		Disjunction datesDisjunction = Restrictions.disjunction();
		datesDisjunction.add(datesConjunction0).add(datesConjunction1).add(datesConjunction2);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findCurrent(LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("notPublished", false));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNotNull("releaseDate")).add(Restrictions.isNotNull("archiveDate")).add(Restrictions.le("releaseDate", systemDateTime)).add(
		Restrictions.ge("archiveDate", systemDateTime));
		Conjunction datesConjunction1 = Restrictions.conjunction();
		datesConjunction1.add(Restrictions.isNotNull("releaseDate")).add(Restrictions.isNull("archiveDate")).add(Restrictions.le("releaseDate", systemDateTime));
		Conjunction datesConjunction2 = Restrictions.conjunction();
		datesConjunction2.add(Restrictions.isNull("releaseDate")).add(Restrictions.isNotNull("archiveDate")).add(Restrictions.ge("archiveDate", systemDateTime));
		Disjunction datesDisjunction = Restrictions.disjunction();
		datesDisjunction.add(datesConjunction0).add(datesConjunction1).add(datesConjunction2);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findWithNewsPublicationAndDeferred(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("newsPublication", newsPublication));
        conjunction.add(Restrictions.eq("notPublished", false));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNotNull("releaseDate")).add(Restrictions.gt("releaseDate", systemDateTime));
		conjunction.add(datesConjunction0);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findWithNewsPublicationAndPublished(NewsPublication newsPublication, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("newsPublication", newsPublication));
        conjunction.add(Restrictions.eq("notPublished", false));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findWithNewsPublicationAndNotPublished(NewsPublication newsPublication, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("newsPublication", newsPublication));
        conjunction.add(Restrictions.eq("notPublished", true));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findWithPatternLikeAndNewsPublication(String searchPattern, NewsPublication newsPublication, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion title = Restrictions.ilike("npa.title", pattern);
		Criterion header = Restrictions.ilike("npa.header", pattern);
		Criterion footer = Restrictions.ilike("npa.footer", pattern);
		Criterion name = Restrictions.ilike("npu.name", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(title).add(header).add(footer).add(name);
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "npa");
		criteria.createAlias(DB_TABLE_NEWS_PUBLICATION, "npu", CriteriaSpecification.LEFT_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        conjunction.add(Restrictions.eq("newsPublication", newsPublication));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("npa.releaseDate")).add(Order.asc("npa.title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findWithNewsPublicationAndRecent(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("newsPublication", newsPublication));
        conjunction.add(Restrictions.eq("notPublished", false));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNotNull("releaseDate")).add(Restrictions.le("releaseDate", systemDateTime));
		Disjunction datesDisjunction = Restrictions.disjunction();
		datesDisjunction.add(datesConjunction0);
        criteria.add(conjunction);
        criteria.setMaxResults(NB_RECENT);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findWithNewsPublicationAndArchived(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("newsPublication", newsPublication));
        conjunction.add(Restrictions.eq("notPublished", false));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNotNull("archiveDate")).add(Restrictions.lt("archiveDate", systemDateTime));
		conjunction.add(datesConjunction0);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<NewsPaper> findWithNewsPublicationAndPassed(NewsPublication newsPublication, LocalDateTime systemDateTime, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("newsPublication", newsPublication));
		Conjunction datesConjunction0 = Restrictions.conjunction();
		datesConjunction0.add(Restrictions.isNotNull("archiveDate")).add(Restrictions.lt("archiveDate", systemDateTime));
		Disjunction datesDisjunction = Restrictions.disjunction();
		datesDisjunction.add(datesConjunction0);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.desc("releaseDate")).add(Order.asc("title"));
		Page<NewsPaper> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
