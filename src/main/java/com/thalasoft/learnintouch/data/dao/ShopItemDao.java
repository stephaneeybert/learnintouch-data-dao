package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ShopCategory;
import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ShopItemDao extends GenericDao<ShopItem, Serializable> {

	public Page<ShopItem> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<ShopItem> findWithCategory(ShopCategory shopCategory, int pageNumber, int pageSize);

	public List<ShopItem> findWithCategoryOrderById(ShopCategory shopCategory);
	
	public ShopItem findWithListOrder(ShopCategory shopCategory, int listOrder);
	
	public ShopItem findNextWithListOrder(ShopCategory photoAlbum, int listOrder);
	
	public ShopItem findPreviousWithListOrder(ShopCategory photoAlbum, int listOrder);

	public long countListOrderDuplicates(ShopCategory photoAlbum);
	
}
