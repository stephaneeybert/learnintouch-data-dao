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

import com.thalasoft.learnintouch.data.dao.ShopItemDao;
import com.thalasoft.learnintouch.data.dao.ShopItemImageDao;
import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.domain.ShopItemImage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ShopItemImageDaoTest extends AbstractDaoTest {

	@Autowired
	private ShopItemImageDao shopItemImageDao;

	@Autowired
	private ShopItemDao shopItemDao;

	private ShopItem shopItem0;
	private ShopItemImage shopItemImage0;
	private ShopItemImage shopItemImage1;
	private ShopItemImage shopItemImage2;
	private ShopItemImage shopItemImage3;

	protected void setShopItemImageDao(ShopItemImageDao shopItemImageDao) {
		this.shopItemImageDao = shopItemImageDao;
	}

	protected void setShopItemDao(ShopItemDao shopItemDao) {
		this.shopItemDao = shopItemDao;
	}

	public ShopItemImageDaoTest() {
		shopItem0 = new ShopItem();
		shopItem0.setName("skates");
		shopItem0.setAdded(new LocalDateTime());
		shopItem0.setLastModified(new LocalDateTime().plusDays(2));
		shopItem0.setAvailable(new LocalDateTime().plusDays(10));
		shopItem0.setListOrder(1);
		shopItemImage0 = new ShopItemImage();
		shopItemImage0.setListOrder(1);
		shopItemImage0.setImage("boat.png");
		shopItemImage0.setShopItem(shopItem0);
		shopItemImage1 = new ShopItemImage();
		shopItemImage1.setListOrder(4);
		shopItemImage1.setImage("car.png");
		shopItemImage1.setShopItem(shopItem0);
		shopItemImage2 = new ShopItemImage();
		shopItemImage2.setListOrder(3);
		shopItemImage2.setImage("plane.png");
		shopItemImage2.setShopItem(shopItem0);
		shopItemImage3 = new ShopItemImage();
		shopItemImage3.setListOrder(2);
		shopItemImage3.setImage("train.png");
		shopItemImage3.setShopItem(shopItem0);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		shopItem0 = shopItemDao.makePersistent(shopItem0);
		shopItemImage0 = shopItemImageDao.makePersistent(shopItemImage0);
		shopItemImage1 = shopItemImageDao.makePersistent(shopItemImage1);
		shopItemImage2 = shopItemImageDao.makePersistent(shopItemImage2);
		shopItemImage3 = shopItemImageDao.makePersistent(shopItemImage3);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(shopItemImage0.getId());
		assertNotSame(shopItemImage0.hashCode(), 0L);
		assertFalse(shopItemImage0.toString().equals(""));
		ShopItemImage shopItemImage = shopItemImageDao.findWithId(shopItemImage0.getId(), true);
		assertEquals(shopItemImage0.hashCode(), shopItemImage.hashCode());
		assertEquals(shopItemImage0.getDescription(), shopItemImage.getDescription());
		assertEquals(shopItemImage0.getImage(), shopItemImage.getImage());
		assertEquals(shopItemImage0.getListOrder(), shopItemImage.getListOrder());
		assertNotNull(shopItemImage.getId());
	}

	@Test
	public void testFindWithShopItem() {
		Page<ShopItemImage> page = shopItemImageDao.findWithShopItem(shopItem0, 0, 0);
		List<ShopItemImage> shopItemImages = page.getPageItems();
		assertEquals(4, shopItemImages.size());
		assertEquals(shopItemImage0.getId(), shopItemImages.get(0).getId());
		assertEquals(shopItemImage3.getId(), shopItemImages.get(1).getId());
		assertEquals(shopItemImage2.getId(), shopItemImages.get(2).getId());
		assertEquals(shopItemImage1.getId(), shopItemImages.get(3).getId());
	}

	@Test
	public void testFindWithShopItemOrderById() {
		shopItemImage0 = shopItemImageDao.makePersistent(shopItemImage0);
		shopItemImage1 = shopItemImageDao.makePersistent(shopItemImage1);
		shopItemImage2 = shopItemImageDao.makePersistent(shopItemImage2);
		shopItemImage3 = shopItemImageDao.makePersistent(shopItemImage3);
		List<ShopItemImage> shopItemImages = shopItemImageDao.findWithShopItemOrderById(shopItem0);
		assertEquals(4, shopItemImages.size());
		assertEquals(shopItemImage0.getId(), shopItemImages.get(0).getId());
		assertEquals(shopItemImage1.getId(), shopItemImages.get(1).getId());
		assertEquals(shopItemImage2.getId(), shopItemImages.get(2).getId());
		assertEquals(shopItemImage3.getId(), shopItemImages.get(3).getId());
	}

	@Test
	public void testFindWithImage() {
		List<ShopItemImage> shopItemImages = shopItemImageDao.findWithImage(shopItemImage0.getImage());
		assertEquals(1, shopItemImages.size());
		assertEquals(shopItemImage0.getId(), shopItemImages.get(0).getId());
		shopItemImages = shopItemImageDao.findWithImage("none.png");
		assertEquals(0, shopItemImages.size());
	}

	@Test
	public void testFindWithListOrder() {
		ShopItemImage shopItemImage = shopItemImageDao.findWithListOrder(shopItem0, 2);
		assertEquals(shopItemImage3.getId(), shopItemImage.getId());
	}

	@Test
	public void testFindNextByListOrder() {
		ShopItemImage shopItemImage = shopItemImageDao.findNextWithListOrder(shopItem0, 0);
		assertEquals(shopItemImage0.getId(), shopItemImage.getId());
		shopItemImage = shopItemImageDao.findNextWithListOrder(shopItem0, 1);
		assertEquals(shopItemImage3.getId(), shopItemImage.getId());
		shopItemImage = shopItemImageDao.findNextWithListOrder(shopItem0, 4);
		assertNull(shopItemImage);
	}

	@Test
	public void testFindPreviousByListOrder() {
		ShopItemImage shopItemImage = shopItemImageDao.findPreviousWithListOrder(shopItem0, 2);
		assertEquals(shopItemImage0.getId(), shopItemImage.getId());
		shopItemImage = shopItemImageDao.findPreviousWithListOrder(shopItem0, 3);
		assertEquals(shopItemImage3.getId(), shopItemImage.getId());
		shopItemImage = shopItemImageDao.findPreviousWithListOrder(shopItem0, 1);
		assertNull(shopItemImage);
	}

	@Test
	public void testCountListOrderDuplicates() {
		assertEquals(0, shopItemImageDao.countListOrderDuplicates(shopItem0));
		shopItemImage1.setListOrder(1);
		assertEquals(2, shopItemImageDao.countListOrderDuplicates(shopItem0));
		shopItemImage2.setListOrder(2);
		assertEquals(4, shopItemImageDao.countListOrderDuplicates(shopItem0));
		shopItemImage1.setListOrder(4);
		shopItemImage2.setListOrder(3);
		assertEquals(0, shopItemImageDao.countListOrderDuplicates(shopItem0));
	}

}
