package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ShopItemDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopCategory;
import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ShopItemHibernateDao extends GenericHibernateDao<ShopItem, Serializable> implements ShopItemDao {

	@Override
	public Page<ShopItem> findWithCategory(ShopCategory shopCategory, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (shopCategory != null) {
		    conjunction.add(Restrictions.eq("shopCategory", shopCategory));
		} else {
		    conjunction.add(Restrictions.isNull("shopCategory"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<ShopItem> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopItem> findWithCategoryOrderById(ShopCategory shopCategory) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (shopCategory != null) {
			criteria.add(Restrictions.eq("shopCategory", shopCategory));
		} else {
			criteria.add(Restrictions.isNull("shopCategory"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public Page<ShopItem> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion name = Restrictions.ilike("name", pattern);
		Criterion shortDescription = Restrictions.ilike("shortDescription", pattern);
		Criterion longDescription = Restrictions.ilike("longDescription", pattern);
		Criterion reference = Restrictions.ilike("reference", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(name).add(shortDescription).add(longDescription).add(reference);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ShopItem> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public ShopItem findWithListOrder(ShopCategory shopCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (shopCategory != null) {
			criteria.add(Restrictions.eq("shopCategory", shopCategory));
		} else {
			criteria.add(Restrictions.isNull("shopCategory"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ShopItem) criteria.uniqueResult();
	}

	@Override
	public ShopItem findNextWithListOrder(ShopCategory shopCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (shopCategory != null) {
			criteria.add(Restrictions.eq("shopCategory", shopCategory));
		} else {
			criteria.add(Restrictions.isNull("shopCategory"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ShopItem) criteria.uniqueResult();
	}

	@Override
	public ShopItem findPreviousWithListOrder(ShopCategory shopCategory, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (shopCategory != null) {
			criteria.add(Restrictions.eq("shopCategory", shopCategory));
		} else {
			criteria.add(Restrictions.isNull("shopCategory"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ShopItem) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(ShopCategory shopCategory) {
		String statement = "select count(distinct si1.id) as count from ShopItem si1, ShopItem si2 where si1.id != si2.id and si1.listOrder = si2.listOrder ";
		if (shopCategory != null) {
			statement += "and si1.shopCategory.id = si2.shopCategory.id and si1.shopCategory.id = :shopCategoryId";
		} else {
			statement += "and si1.shopCategory.id is null and si2.shopCategory.id is null";
		}
		Query query = getSession().createQuery(statement);
		if (shopCategory != null) {
			query.setLong("shopCategoryId", shopCategory.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}
	
}
