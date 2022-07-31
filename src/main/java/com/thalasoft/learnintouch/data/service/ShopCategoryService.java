package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.ShopCategory;

public interface ShopCategoryService {

	public ShopCategory save(ShopCategory shopCategory);

	public boolean isNotUsedByAnyShopItem(ShopCategory shopCategory);

}
