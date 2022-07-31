package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.DocumentDao;
import com.thalasoft.learnintouch.data.dao.domain.Document;
import com.thalasoft.learnintouch.data.dao.domain.DocumentCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class DocumentHibernateDao extends GenericHibernateDao<Document, Serializable> implements DocumentDao {

	@Override
	public Page<Document> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("documentCategory")).add(Order.asc("listOrder"));
		Page<Document> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Document> findWithCategory(DocumentCategory documentCategory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion;
		if (documentCategory != null) {
			criterion = Restrictions.eq("documentCategory", documentCategory);
		} else {
			criterion = Restrictions.isNull("documentCategory");
		}
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<Document> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> findWithCategoryOrderById(DocumentCategory documentCategory) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (documentCategory != null) {
			criteria.add(Restrictions.eq("documentCategory", documentCategory));
		} else {
			criteria.add(Restrictions.isNull("documentCategory"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	@Override
	public Page<Document> findPublished(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		Criterion criterion = Restrictions.ne("hide", true); 
		criteria.add(criterion);
        OrderList orderList = new OrderList().add(Order.asc("documentCategory")).add(Order.asc("listOrder"));
		Page<Document> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}
	
	@Override
	public Document findWithListOrder(DocumentCategory documentCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (documentCategory != null) {
			criteria.add(Restrictions.eq("documentCategory", documentCategory));
		} else {
			criteria.add(Restrictions.isNull("documentCategory"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (Document) criteria.uniqueResult();
	}

	@Override
	public Document findNextWithListOrder(DocumentCategory documentCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (documentCategory != null) {
			criteria.add(Restrictions.eq("documentCategory", documentCategory));
		} else {
			criteria.add(Restrictions.isNull("documentCategory"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (Document) criteria.uniqueResult();
	}

	@Override
	public Document findPreviousWithListOrder(DocumentCategory documentCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (documentCategory != null) {
			criteria.add(Restrictions.eq("documentCategory", documentCategory));
		} else {
			criteria.add(Restrictions.isNull("documentCategory"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (Document) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(DocumentCategory documentCategory) {
		String statement = "select count(distinct d1.id) as count from Document d1, Document d2 where d1.id != d2.id and d1.listOrder = d2.listOrder and ";
		if (documentCategory != null) {
			statement += "d1.documentCategory = d2.documentCategory and d1.documentCategory.id = :documentCategoryId";
		} else {
			statement += "d1.documentCategory.id is null and d2.documentCategory.id is null";
		}
		Query query = getSession().createQuery(statement);
		if (documentCategory != null) {
			query.setLong("documentCategoryId", documentCategory.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Document> findWithFilename(String filename) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("filename", filename));
		return criteria.list();
	}

}
