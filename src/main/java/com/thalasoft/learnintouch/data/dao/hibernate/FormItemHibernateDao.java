package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.FormItemDao;
import com.thalasoft.learnintouch.data.dao.domain.Form;
import com.thalasoft.learnintouch.data.dao.domain.FormItem;

@Repository
@Transactional
public class FormItemHibernateDao extends GenericHibernateDao<FormItem, Serializable> implements FormItemDao {

	@SuppressWarnings("unchecked")
	public List<FormItem> findWithForm(Form form) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("form", form)).addOrder(Order.asc("listOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<FormItem> findWithFormOrderById(Form form) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("form", form)).addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public FormItem findWithListOrder(Form form, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("form", form));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (FormItem) criteria.uniqueResult();
	}

	@Override
	public FormItem findNextWithListOrder(Form form, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("form", form)).add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (FormItem) criteria.uniqueResult();
	}

	@Override
	public FormItem findPreviousWithListOrder(Form form, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("form", form)).add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (FormItem) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(Form form) {
		Query query = getSession().createQuery("select count(distinct fi1.id) as count from FormItem fi1, FormItem fi2 where fi1.id != fi2.id and fi1.listOrder = fi2.listOrder and fi1.form.id = fi2.form.id and fi1.form.id = :formId");
		query.setLong("formId", form.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
