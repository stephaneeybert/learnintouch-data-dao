package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.NavmenuItemDao;
import com.thalasoft.learnintouch.data.dao.domain.NavmenuItem;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

@Repository
@Transactional
public class NavmenuItemHibernateDao extends GenericHibernateDao<NavmenuItem, Serializable> implements NavmenuItemDao {	
	
	@Override
	public NavmenuItem findWithName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name)).addOrder(Order.asc("listOrder"));
		return (NavmenuItem) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NavmenuItem> findWithParent(NavmenuItem parent) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("parent", parent)).addOrder(Order.asc("listOrder"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NavmenuItem> findWithParentOrderById(NavmenuItem parent) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("parent", parent)).addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public NavmenuItem findWithListOrder(NavmenuItem parent, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("parent", parent));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (NavmenuItem) criteria.uniqueResult();
	}

	@Override
	public NavmenuItem findNextWithListOrder(NavmenuItem parent, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("parent", parent)).add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (NavmenuItem) criteria.uniqueResult();
	}

	@Override
	public NavmenuItem findPreviousWithListOrder(NavmenuItem parent, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("parent", parent)).add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (NavmenuItem) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(NavmenuItem parent) {
		String statement = "select count(distinct ni1.id) as count from NavmenuItem ni1, NavmenuItem ni2 where ni1.id != ni2.id and ni1.listOrder = ni2.listOrder and ";
		if (parent != null) {
			statement += "ni1.parent.id = ni2.parent.id and ni1.parent.id = :parentId";
		} else {
			statement += "ni1.parent.id is null and ni2.parent.id is null";
		}
		Query query = getSession().createQuery(statement);
		if (parent != null) {
			query.setLong("parentId", parent.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NavmenuItem> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image)).addOrder(Order.asc("listOrder"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NavmenuItem> findWithImageOver(String imageOver) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("imageOver", imageOver)).addOrder(Order.asc("listOrder"));
		return criteria.list();
	}
	
	@Override
	public long resetNavigationModelReferences(TemplateModel templateModel) {
		Query query = getSession().createQuery("update NavmenuItem set templateModel = NULL where templateModel.id = :templateModelId");
		query.setLong("templateModelId", templateModel.getId());
		return query.executeUpdate();
	}

}
