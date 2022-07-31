package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NavbarItemDao;
import com.thalasoft.learnintouch.data.dao.domain.NavbarItem;
import com.thalasoft.learnintouch.data.dao.domain.NavbarLanguage;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

@Repository
@Transactional
public class NavbarItemHibernateDao extends GenericHibernateDao<NavbarItem, Serializable> implements NavbarItemDao {

	@Override
	public NavbarItem findWithName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name)).addOrder(Order.asc("listOrder"));
		return (NavbarItem) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NavbarItem> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image)).addOrder(Order.asc("listOrder"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NavbarItem> findWithImageOver(String imageOver) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("imageOver", imageOver)).addOrder(Order.asc("listOrder"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NavbarItem> findWithNavbarLanguage(NavbarLanguage navbarLanguage) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navbarLanguage", navbarLanguage)).addOrder(Order.asc("listOrder"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NavbarItem> findWithNavbarLanguageOrderById(NavbarLanguage navbarLanguage) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navbarLanguage", navbarLanguage)).addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	@Override
	public NavbarItem findWithListOrder(NavbarLanguage navbarLanguage, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navbarLanguage", navbarLanguage));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (NavbarItem) criteria.uniqueResult();
	}

	@Override
	public NavbarItem findNextWithListOrder(NavbarLanguage navbarLanguage, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navbarLanguage", navbarLanguage)).add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (NavbarItem) criteria.uniqueResult();
	}

	@Override
	public NavbarItem findPreviousWithListOrder(NavbarLanguage navbarLanguage, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("navbarLanguage", navbarLanguage)).add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (NavbarItem) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(NavbarLanguage navbarLanguage) {
		Query query = getSession().createQuery("select count(distinct ni1.id) as count from NavbarItem ni1, NavbarItem ni2 where ni1.id != ni2.id and ni1.listOrder = ni2.listOrder and ni1.navbarLanguage.id = ni2.navbarLanguage.id and ni1.navbarLanguage.id = :navbarLanguageId");
		query.setLong("navbarLanguageId", navbarLanguage.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@Override
	public long resetNavigationModelReferences(TemplateModel templateModel) {
		Query query = getSession().createQuery("update NavbarItem set templateModel = null where templateModel.id = :templateModelId");
		query.setLong("templateModelId", templateModel.getId());
		return query.executeUpdate();
	}

}
