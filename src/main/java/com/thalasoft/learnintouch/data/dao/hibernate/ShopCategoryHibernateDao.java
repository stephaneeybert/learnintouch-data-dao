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

import com.thalasoft.learnintouch.data.dao.ShopCategoryDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ShopCategoryHibernateDao extends GenericHibernateDao<ShopCategory, Serializable> implements ShopCategoryDao {

	@Override
	public Page<ShopCategory> findWithParent(ShopCategory parent, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (parent != null) {
		    conjunction.add(Restrictions.eq("parent", parent));
		} else {
		    conjunction.add(Restrictions.isNull("parent"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<ShopCategory> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopCategory> findWithParentOrderById(ShopCategory parent) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public ShopCategory findWithListOrder(ShopCategory parent, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ShopCategory) criteria.uniqueResult();
	}

	@Override
	public ShopCategory findNextWithListOrder(ShopCategory parent, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ShopCategory) criteria.uniqueResult();
	}

	@Override
	public ShopCategory findPreviousWithListOrder(ShopCategory parent, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (parent != null) {
			criteria.add(Restrictions.eq("parent", parent));
		} else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ShopCategory) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(ShopCategory parent) {
		String statement = "select count(distinct sc1.id) as count from ShopCategory sc1, ShopCategory sc2 where sc1.id != sc2.id and sc1.listOrder = sc2.listOrder and ";
		if (parent != null) {
			statement += "sc1.parent = sc2.parent and sc1.parent.id = :parentId";
		} else {
			statement += "sc1.parent.id is null and sc2.parent.id is null";
		}
		Query query = getSession().createQuery(statement);
		if (parent != null) {
			query.setLong("parentId", parent.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
