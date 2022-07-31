package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.ShopCategoryDao;
import com.thalasoft.learnintouch.data.dao.ShopItemDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopCategory;
import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.ShopCategoryService;

@Transactional
public class ShopCategoryServiceImpl implements ShopCategoryService {

	@Autowired
	private ShopItemDao shopItemDao;
	
	@Autowired
	private ShopCategoryDao shopCategoryDao;

	protected void setShopItemDao(ShopItemDao shopItemDao) {
		this.shopItemDao = shopItemDao;
	}

	protected void setShopCategoryDao(ShopCategoryDao shopCategoryDao) {
		this.shopCategoryDao = shopCategoryDao;
	}

	@Override
	public ShopCategory save(ShopCategory shopCategory) {
		return shopCategoryDao.makePersistent(shopCategory);
	}

	@Override
	public boolean isNotUsedByAnyShopItem(ShopCategory shopCategory) {
		Page<ShopItem> page = shopItemDao.findWithCategory(shopCategory, 0, 0);
		List<ShopItem> shopItems = page.getPageItems();
		return shopItems.size() == 0;
	}

}
