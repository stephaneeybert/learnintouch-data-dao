package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.domain.ShopOrder;
import com.thalasoft.learnintouch.data.dao.domain.ShopOrderItem;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ShopOrderItemDao extends GenericDao<ShopOrderItem, Serializable> {

	public Page<ShopOrderItem> findWithPatternLike(String pattern, int pageNumber, int pageSize);
	
	public Page<ShopOrderItem> findWithShopOrder(ShopOrder shopOrder, int pageNumber, int pageSize);
	
	public ShopOrderItem findWithShopOrderAndShopItem(ShopOrder shopOrder, ShopItem shopItem);
	
}
