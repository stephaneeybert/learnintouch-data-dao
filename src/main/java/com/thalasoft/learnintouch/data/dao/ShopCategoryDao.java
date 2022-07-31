package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ShopCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ShopCategoryDao extends GenericDao<ShopCategory, Serializable> {

	public Page<ShopCategory> findWithParent(ShopCategory parent, int pageNumber, int pageSize);

	public List<ShopCategory> findWithParentOrderById(ShopCategory parent);	
	
	public ShopCategory findWithListOrder(ShopCategory parent, int listOrder);

	public ShopCategory findNextWithListOrder(ShopCategory parent, int listOrder);

	public ShopCategory findPreviousWithListOrder(ShopCategory parent, int listOrder);

	public long countListOrderDuplicates(ShopCategory parent);

}
