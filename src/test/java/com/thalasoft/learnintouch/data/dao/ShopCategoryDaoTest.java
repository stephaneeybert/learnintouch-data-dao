package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.ShopCategoryDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ShopCategoryDaoTest extends AbstractDaoTest {

	@Autowired
	private ShopCategoryDao shopCategoryDao;

	private ShopCategory shopCategory0;
	private ShopCategory shopCategory1;
	private ShopCategory shopCategory2;

	protected void setShopCategoryDao(ShopCategoryDao shopCategoryDao) {
		this.shopCategoryDao = shopCategoryDao;
	}

	public ShopCategoryDaoTest() {
		shopCategory0 = new ShopCategory();
		shopCategory0.setName("cars");
		shopCategory0.setDescription("car for rent");
		shopCategory0.setListOrder(4);
		shopCategory1 = new ShopCategory();
		shopCategory1.setName("cycles");
		shopCategory1.setListOrder(2);
		shopCategory1.setParent(shopCategory0);
		shopCategory2 = new ShopCategory();
		shopCategory2.setName("bikes");
		shopCategory2.setListOrder(1);
		shopCategory2.setParent(shopCategory0);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		shopCategory1 = shopCategoryDao.makePersistent(shopCategory1);
		shopCategory0 = shopCategoryDao.makePersistent(shopCategory0);
		shopCategory2 = shopCategoryDao.makePersistent(shopCategory2);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(shopCategory0.getId());
		assertNotSame(shopCategory0.hashCode(), 0L);
		assertFalse(shopCategory0.toString().equals(""));
		ShopCategory shopCategory = shopCategoryDao.findWithId(shopCategory0.getId(), true);
		assertEquals(shopCategory0.hashCode(), shopCategory.hashCode());
		assertEquals(shopCategory0.getName(), shopCategory.getName());
		assertEquals(shopCategory0.getListOrder(), shopCategory.getListOrder());
		assertNotNull(shopCategory.getId());
	}

	@Test
	public void testFindWithParent() {
		Page<ShopCategory> page = shopCategoryDao.findWithParent(shopCategory0, 0, 0);
		List<ShopCategory> shopCategories = page.getPageItems();
		assertEquals(2, shopCategories.size());
		assertEquals(shopCategory2.getListOrder(), shopCategories.get(0).getListOrder());
		assertEquals(shopCategory1.getListOrder(), shopCategories.get(1).getListOrder());
		page = shopCategoryDao.findWithParent(null, 0, 0);
		shopCategories = page.getPageItems();
		assertEquals(1, shopCategories.size());
		assertEquals(shopCategory0.getListOrder(), shopCategories.get(0).getListOrder());
	}

	@Test
	public void testFindWithParentOrderById() {
		Page<ShopCategory> page = shopCategoryDao.findWithParent(shopCategory0, 0, 0);
		List<ShopCategory> shopCategories = page.getPageItems();
		assertEquals(2, shopCategories.size());
		assertEquals(shopCategory2.getListOrder(), shopCategories.get(0).getListOrder());
		assertEquals(shopCategory1.getListOrder(), shopCategories.get(1).getListOrder());
	}

	@Test
	public void testFindWithListOrder() {
		ShopCategory shopCategory = shopCategoryDao.findWithListOrder(shopCategory0, 2);
		assertEquals(shopCategory1.getId(), shopCategory.getId());
		shopCategory = shopCategoryDao.findWithListOrder(null, 2);
		assertNull(shopCategory);
		shopCategory = shopCategoryDao.findWithListOrder(null, 4);
		assertEquals(shopCategory0.getId(), shopCategory.getId());
	}

	@Test
	public void testFindNextByListOrder() {
		ShopCategory shopCategory = shopCategoryDao.findNextWithListOrder(null, 3);
		assertEquals(shopCategory0.getId(), shopCategory.getId());
		shopCategory = shopCategoryDao.findNextWithListOrder(shopCategory0, 1);
		assertEquals(shopCategory1.getId(), shopCategory.getId());
		shopCategory = shopCategoryDao.findNextWithListOrder(shopCategory0, 0);
		assertEquals(shopCategory2.getId(), shopCategory.getId());
		shopCategory = shopCategoryDao.findNextWithListOrder(shopCategory0, 2);
		assertNull(shopCategory);
	}

	@Test
	public void testFindPreviousByListOrder() {
		ShopCategory shopCategory = shopCategoryDao.findPreviousWithListOrder(null, 5);
		assertEquals(shopCategory0.getId(), shopCategory.getId());
		shopCategory = shopCategoryDao.findPreviousWithListOrder(shopCategory0, 3);
		assertEquals(shopCategory1.getId(), shopCategory.getId());
		shopCategory = shopCategoryDao.findPreviousWithListOrder(shopCategory0, 2);
		assertEquals(shopCategory2.getId(), shopCategory.getId());
		shopCategory = shopCategoryDao.findPreviousWithListOrder(shopCategory0, 1);
		assertNull(shopCategory);
	}

	@Test
	public void testCountListOrderDuplicates() {
		assertEquals(0, shopCategoryDao.countListOrderDuplicates(shopCategory0));
		shopCategory1.setListOrder(1);
		assertEquals(2, shopCategoryDao.countListOrderDuplicates(shopCategory0));
		shopCategory2.setParent(shopCategory1);
		assertEquals(0, shopCategoryDao.countListOrderDuplicates(shopCategory0));
		assertEquals(0, shopCategoryDao.countListOrderDuplicates(null));
		shopCategory0.setListOrder(1);
		shopCategory2.setParent(null);
		assertEquals(2, shopCategoryDao.countListOrderDuplicates(null));
	}

}
