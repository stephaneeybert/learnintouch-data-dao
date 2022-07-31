package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.domain.ShopItemImage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ShopItemImageDao extends GenericDao<ShopItemImage, Serializable> {

	public Page<ShopItemImage> findWithShopItem(ShopItem shopItem, int pageNumber, int pageSize);
	
	public List<ShopItemImage> findWithShopItemOrderById(ShopItem shopItem);

	public List<ShopItemImage> findWithImage(String image);

	public ShopItemImage findWithListOrder(ShopItem shopItem, int listOrder);
	
	public ShopItemImage findNextWithListOrder(ShopItem shopItem, int listOrder);
	
	public ShopItemImage findPreviousWithListOrder(ShopItem shopItem, int listOrder);
	
	public long countListOrderDuplicates(ShopItem shopItem);
	
}
