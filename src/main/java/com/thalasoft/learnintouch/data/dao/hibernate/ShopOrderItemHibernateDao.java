package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ShopOrderItemDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.domain.ShopOrder;
import com.thalasoft.learnintouch.data.dao.domain.ShopOrderItem;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ShopOrderItemHibernateDao extends GenericHibernateDao<ShopOrderItem, Serializable> implements ShopOrderItemDao {

	@Override
	public Page<ShopOrderItem> findWithShopOrder(ShopOrder shopOrder, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("shopOrder", shopOrder));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ShopOrderItem> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public ShopOrderItem findWithShopOrderAndShopItem(ShopOrder shopOrder, ShopItem shopItem) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("shopOrder", shopOrder));
		if (shopItem != null) {
			criteria.add(Restrictions.eq("shopItem", shopItem));
		} else {
			criteria.add(Restrictions.isNull("shopItem"));
		}
		criteria.addOrder(Order.asc("name"));
		criteria.setMaxResults(1);
		return (ShopOrderItem) criteria.uniqueResult();
	}

	@Override
	public Page<ShopOrderItem> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion name = Restrictions.ilike("name", pattern);
		Criterion reference = Restrictions.ilike("reference", pattern);
		Criterion shortDescription = Restrictions.ilike("shortDescription", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(name).add(reference).add(shortDescription);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("name"));
		Page<ShopOrderItem> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

}
