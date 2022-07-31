package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.TemplateElementDao;
import com.thalasoft.learnintouch.data.dao.domain.TemplateContainer;
import com.thalasoft.learnintouch.data.dao.domain.TemplateElement;

@Repository
@Transactional
public class TemplateElementHibernateDao extends GenericHibernateDao<TemplateElement, Serializable> implements TemplateElementDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateElement> findWithContainer(TemplateContainer templateContainer) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateContainer", templateContainer));
		criteria.addOrder(Order.asc("listOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateElement> findWithContainerOrderById(TemplateContainer templateContainer) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateContainer", templateContainer));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public TemplateElement findWithListOrder(TemplateContainer templateContainer, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateContainer", templateContainer));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (TemplateElement) criteria.uniqueResult();
	}

	@Override
	public TemplateElement findNextWithListOrder(TemplateContainer templateContainer, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateContainer", templateContainer));
		criteria.add(Restrictions.gt("listOrder", listOrder));
		criteria.addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (TemplateElement) criteria.uniqueResult();
	}

	@Override
	public TemplateElement findPreviousWithListOrder(TemplateContainer templateContainer, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateContainer", templateContainer));
		criteria.add(Restrictions.lt("listOrder", listOrder));
		criteria.addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (TemplateElement) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(TemplateContainer templateContainer) {
		String statement = "select count(distinct te1.id) as count from TemplateElement te1, TemplateElement te2 where te1.id != te2.id and te1.listOrder = te2.listOrder and te1.templateContainer.id = te2.templateContainer.id and te1.templateContainer.id = :templateContainerId";
		Query query = getSession().createQuery(statement);
		query.setLong("templateContainerId", templateContainer.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
