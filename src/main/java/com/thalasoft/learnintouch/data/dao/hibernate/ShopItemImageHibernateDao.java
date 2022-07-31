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

import com.thalasoft.learnintouch.data.dao.ShopItemImageDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.domain.ShopItemImage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class ShopItemImageHibernateDao extends GenericHibernateDao<ShopItemImage, Serializable> implements ShopItemImageDao {

	@Override
	public Page<ShopItemImage> findWithShopItem(ShopItem shopItem, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (shopItem != null) {
		    conjunction.add(Restrictions.eq("shopItem", shopItem));
		} else {
		    conjunction.add(Restrictions.isNull("shopItem"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<ShopItemImage> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopItemImage> findWithShopItemOrderById(ShopItem shopItem) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (shopItem != null) {
			criteria.add(Restrictions.eq("shopItem", shopItem));
		} else {
			criteria.add(Restrictions.isNull("shopItem"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShopItemImage> findWithImage(String image) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("image", image));
		return criteria.list();
	}

	@Override
	public ShopItemImage findWithListOrder(ShopItem shopItem, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("shopItem", shopItem));
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (ShopItemImage) criteria.uniqueResult();
	}

	@Override
	public ShopItemImage findNextWithListOrder(ShopItem shopItem, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("shopItem", shopItem));
		criteria.add(Restrictions.gt("listOrder", listOrder)).addOrder(Order.asc("listOrder")).setMaxResults(1);
		return (ShopItemImage) criteria.uniqueResult();
	}

	@Override
	public ShopItemImage findPreviousWithListOrder(ShopItem shopItem, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("shopItem", shopItem));
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (ShopItemImage) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(ShopItem shopItem) {
		Query query = getSession().createQuery("select count(distinct sii1.id) as count from ShopItemImage sii1, ShopItemImage sii2 where sii1.id != sii2.id and sii1.shopItem = sii2.shopItem and sii1.listOrder = sii2.listOrder and sii1.shopItem.id = :shopItemId");
		query.setLong("shopItemId", shopItem.getId());
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}

}
