package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ContentImportDao;
import com.thalasoft.learnintouch.data.dao.domain.ContentImport;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ContentImportHibernateDao extends GenericHibernateDao<ContentImport, Serializable> implements ContentImportDao {

	@Override
	public Page<ContentImport> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("domainName"));
		Page<ContentImport> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ContentImport> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.ilike("domainName", pattern);
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("domainName"));
		Page<ContentImport> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<ContentImport> findWithDomainName(String domainName, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Criterion criterion = Restrictions.eq("domainName", domainName);
		criteria.add(criterion);
		Page<ContentImport> page = getPage(pageNumber, pageSize, criteria);
		return page;
	}

	@Override
	public Page<ContentImport> findIsImporting(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.eq("isImporting", true);
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("domainName"));
		Page<ContentImport> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Page<ContentImport> findIsExporting(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Criterion criterion = Restrictions.eq("isExporting", true);
        criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("domainName"));
		Page<ContentImport> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public ContentImport findIsImportingByDomainName(String domainName) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("domainName", domainName))
		.add(Restrictions.eq("isImporting", true))
		.addOrder(Order.asc("domainName"));
		return (ContentImport) criteria.uniqueResult();
	}
	
	@Override
	public ContentImport findIsExportingByDomainName(String domainName) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("domainName", domainName))
		.add(Restrictions.eq("isExporting", true))
		.addOrder(Order.asc("domainName"));
		return (ContentImport) criteria.uniqueResult();
	}

}
