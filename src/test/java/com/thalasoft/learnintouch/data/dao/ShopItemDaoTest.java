package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.ShopCategoryDao;
import com.thalasoft.learnintouch.data.dao.ShopItemDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopCategory;
import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ShopItemDaoTest extends AbstractDaoTest {

	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Autowired
	private ShopItemDao shopItemDao;

	private ShopCategory shopCategory0;
	private ShopItem shopItem0;
	private ShopItem shopItem1;
	private ShopItem shopItem2;
	private ShopItem shopItem3;

	protected void setShopCategoryDao(ShopCategoryDao shopCategoryDao) {
		this.shopCategoryDao = shopCategoryDao;
	}

	protected void setShopItemDao(ShopItemDao shopItemDao) {
		this.shopItemDao = shopItemDao;
	}

	public ShopItemDaoTest() {
		shopCategory0 = new ShopCategory();
		shopCategory0.setName("cars");
		shopCategory0.setDescription("car for rent");
		shopCategory0.setListOrder(4);
		shopItem0 = new ShopItem();
		shopItem0.setName("skates");
		shopItem0.setAdded(new LocalDateTime());
		shopItem0.setLastModified(new LocalDateTime().plusDays(2));
		shopItem0.setAvailable(new LocalDateTime().plusDays(10));
		shopItem0.setHide(true);
		shopItem0.setLongDescription("The long description for the skates");
		shopItem0.setPrice("100.44");
		shopItem0.setReference("SK100");
		shopItem0.setShippingFee("12.50");
		shopItem0.setShortDescription("The short description for the skates");
		shopItem0.setUrl("www.skates.com");
		shopItem0.setWeight("20");
		shopItem0.setListOrder(1);
		shopItem0.setShopCategory(shopCategory0);
		shopItem1 = new ShopItem();
		shopItem1.setName("cycles");
		shopItem1.setShortDescription("The short description for the cycles");
		shopItem1.setListOrder(2);
		shopItem1.setAdded(new LocalDateTime());
		shopItem1.setLastModified(new LocalDateTime().plusDays(2));
		shopItem1.setAvailable(new LocalDateTime().plusDays(10));
		shopItem1.setShopCategory(shopCategory0);
		shopItem2 = new ShopItem();
		shopItem2.setName("bikes");
		shopItem2.setShortDescription("The short description for the bikes");
		shopItem2.setListOrder(1);
		shopItem2.setAdded(new LocalDateTime());
		shopItem2.setLastModified(new LocalDateTime().plusDays(2));
		shopItem2.setAvailable(new LocalDateTime().plusDays(10));
		shopItem3 = new ShopItem();
		shopItem3.setName("rollers");
		shopItem3.setAdded(new LocalDateTime());
		shopItem3.setLastModified(new LocalDateTime().plusDays(2));
		shopItem3.setAvailable(new LocalDateTime().plusDays(10));
		shopItem3.setListOrder(1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		shopCategory0 = shopCategoryDao.makePersistent(shopCategory0);
		shopItem1 = shopItemDao.makePersistent(shopItem1);
		shopItem0 = shopItemDao.makePersistent(shopItem0);
		shopItem2 = shopItemDao.makePersistent(shopItem2);
		shopItem3 = shopItemDao.makePersistent(shopItem3);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(shopItem0.getId());
		assertNotSame(shopItem0.hashCode(), 0L);
		assertFalse(shopItem0.toString().equals(""));
		ShopItem shopItem = shopItemDao.findWithId(shopItem0.getId(), true);
		assertEquals(shopItem0.hashCode(), shopItem.hashCode());
		assertEquals(shopItem0.getName(), shopItem.getName());
		assertEquals(shopItem0.getReference(), shopItem.getReference());
		assertEquals(shopItem0.getWeight(), shopItem.getWeight());
		assertEquals(shopItem0.getUrl(), shopItem.getUrl());
		assertEquals(shopItem0.getShortDescription(), shopItem.getShortDescription());
		assertEquals(shopItem0.getShippingFee(), shopItem.getShippingFee());
		assertEquals(shopItem0.getAvailable(), shopItem.getAvailable());
		assertEquals(shopItem0.getHide(), shopItem.getHide());
		assertEquals(shopItem0.getLastModified(), shopItem.getLastModified());
		assertEquals(shopItem0.getLongDescription(), shopItem.getLongDescription());
		assertEquals(shopItem0.getPrice(), shopItem.getPrice());
		assertEquals(shopItem0.getListOrder(), shopItem.getListOrder());
		assertNotNull(shopItem.getId());
	}

	@Test
	public void testFindWithCategory() {
		Page<ShopItem> page = shopItemDao.findWithCategory(shopCategory0, 0, 0);
		List<ShopItem> shopItems = page.getPageItems();
		assertEquals(2, shopItems.size());
		assertEquals(shopItem0.getId(), shopItems.get(0).getId());
		assertEquals(shopItem1.getId(), shopItems.get(1).getId());
		page = shopItemDao.findWithCategory(null, 0, 0);
		shopItems = page.getPageItems();
		assertEquals(2, shopItems.size());
		assertEquals(shopItem2.getId(), shopItems.get(0).getId());
	}

	@Test
	public void testFindWithCategoryOrderById() {
		Page<ShopItem> page = shopItemDao.findWithCategory(shopCategory0, 0, 0);
		List<ShopItem> shopItems = page.getPageItems();
		assertEquals(2, shopItems.size());
		assertEquals(shopItem0.getId(), shopItems.get(0).getId());
		assertEquals(shopItem1.getId(), shopItems.get(1).getId());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<ShopItem> page = shopItemDao.findWithPatternLike("bike", 0, 0);
		List<ShopItem> shopItems = page.getPageItems();
		assertEquals(1, shopItems.size());
		assertEquals(shopItem2.getId(), shopItems.get(0).getId());
		page = shopItemDao.findWithPatternLike("the short", 0, 0);
		shopItems = page.getPageItems();
		assertEquals(3, shopItems.size());
		assertEquals(shopItem2.getName(), shopItems.get(0).getName());
		assertEquals(shopItem1.getName(), shopItems.get(1).getName());
		assertEquals(shopItem0.getName(), shopItems.get(2).getName());
		page = shopItemDao.findWithPatternLike("the long", 0, 0);
		shopItems = page.getPageItems();
		assertEquals(1, shopItems.size());
		assertEquals(shopItem0.getName(), shopItems.get(0).getName());
		page = shopItemDao.findWithPatternLike("cannot be found", 0, 0);
		shopItems = page.getPageItems();
		assertEquals(0, shopItems.size());
	}

	@Test
	public void testFindWithListOrder() {
		ShopItem shopItem = shopItemDao.findWithListOrder(shopCategory0, 2);
		assertEquals(shopItem1.getId(), shopItem.getId());
		shopItem = shopItemDao.findWithListOrder(null, 2);
		assertNull(shopItem);
		shopItem = shopItemDao.findWithListOrder(null, 1);
		assertEquals(shopItem2.getId(), shopItem.getId());
	}

	@Test
	public void testFindNextByListOrder() {
		ShopItem shopItem = shopItemDao.findNextWithListOrder(null, 0);
		assertEquals(shopItem2.getId(), shopItem.getId());
		shopItem = shopItemDao.findNextWithListOrder(shopCategory0, 1);
		assertEquals(shopItem1.getId(), shopItem.getId());
		shopItem = shopItemDao.findNextWithListOrder(shopCategory0, 0);
		assertEquals(shopItem0.getId(), shopItem.getId());
		shopItem = shopItemDao.findNextWithListOrder(shopCategory0, 2);
		assertNull(shopItem);
	}

	@Test
	public void testFindPreviousByListOrder() {
		ShopItem shopItem = shopItemDao.findPreviousWithListOrder(null, 2);
		assertEquals(shopItem2.getId(), shopItem.getId());
		shopItem = shopItemDao.findPreviousWithListOrder(shopCategory0, 3);
		assertEquals(shopItem1.getId(), shopItem.getId());
		shopItem = shopItemDao.findPreviousWithListOrder(shopCategory0, 2);
		assertEquals(shopItem0.getId(), shopItem.getId());
		shopItem = shopItemDao.findPreviousWithListOrder(shopCategory0, 1);
		assertNull(shopItem);
	}

	@Test
	public void testCountListOrderDuplicates() {
		assertEquals(0, shopItemDao.countListOrderDuplicates(shopCategory0));
		shopItem1.setListOrder(1);
		assertEquals(2, shopItemDao.countListOrderDuplicates(shopCategory0));
		shopItem1.setListOrder(2);
		shopItem2.setListOrder(3);
		assertEquals(0, shopItemDao.countListOrderDuplicates(shopCategory0));
		shopItem2.setListOrder(1);
		assertEquals(2, shopItemDao.countListOrderDuplicates(null));
	}

}
