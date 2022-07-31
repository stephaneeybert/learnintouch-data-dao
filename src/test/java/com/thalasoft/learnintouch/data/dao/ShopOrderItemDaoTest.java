package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.ShopItemDao;
import com.thalasoft.learnintouch.data.dao.ShopOrderDao;
import com.thalasoft.learnintouch.data.dao.ShopOrderItemDao;
import com.thalasoft.learnintouch.data.dao.UserAccountDao;
import com.thalasoft.learnintouch.data.dao.domain.Address;
import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.domain.ShopOrder;
import com.thalasoft.learnintouch.data.dao.domain.ShopOrderItem;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ShopOrderItemDaoTest extends AbstractDaoTest {

	@Autowired
	private ShopOrderItemDao shopOrderItemDao;

	@Autowired
	private ShopItemDao shopItemDao;

	@Autowired
	private ShopOrderDao shopOrderDao;

	@Autowired
	private UserAccountDao userDao;

	private Address address0;
	private UserAccount user0;
	private ShopOrderItem shopOrderItem0;
	private ShopOrderItem shopOrderItem1;
	private ShopItem shopItem0;
	private ShopOrder shopOrder0;
	private ShopOrder shopOrder1;

	protected void setShopOrderItemDao(ShopOrderItemDao shopOrderItemDao) {
		this.shopOrderItemDao = shopOrderItemDao;
	}

	protected void setShopItemDao(ShopItemDao shopItemDao) {
		this.shopItemDao = shopItemDao;
	}

	protected void setUserDao(UserAccountDao userDao) {
		this.userDao = userDao;
	}

	public ShopOrderItemDaoTest() {
		address0 = new Address();
		address0.setAddress1("6 place Emile");
		address0.setAddress2("Couteron");
		address0.setZipCode("54000");
		address0.setCity("Nancy");
		address0.setPostalBox("PoBox 001");
		address0.setState("PACA");
		address0.setCountry("France");
		user0 = new UserAccount();
		user0.setEmail("stephane@thalasoft.com");
		user0.setPassword("ezaezf");
		user0.setPasswordSalt("43453");
		user0.setReadablePassword("toto");
		user0.setFirstname("Stephane");
		user0.setLastname("Eybert");
		user0.setOrganisation("Thalasoft");
		user0.setMobilePhone("0667859807");
		user0.setMailSubscribe(true);
		user0.setSmsSubscribe(true);
		user0.setImage("stephane.png");
		user0.setAddress(address0);
		user0.setValidUntil(new LocalDateTime().minusDays(1));
		user0.setLastLogin(new LocalDateTime());
		shopItem0 = new ShopItem();
		shopItem0.setName("bikes");
		shopItem0.setListOrder(1);
		shopItem0.setPrice("111");
		shopItem0.setAdded(new LocalDateTime());
		shopItem0.setLastModified(new LocalDateTime().plusDays(2));
		shopItem0.setAvailable(new LocalDateTime().plusDays(10));
		shopOrder0 = new ShopOrder();
		shopOrder0.setFirstname("Stephane");
		shopOrder0.setLastname("Eybert");
		shopOrder0.setEmail("mittiprovence@yahoo.se");
		shopOrder0.setCurrency("EUR");
		shopOrder0.setOrderDate(new LocalDateTime().plusMonths(1));
		shopOrder0.setDueDate(new LocalDateTime().plusDays(80));
		shopOrder0.setStatus("paid");
		shopOrder0.setClientIp("127.0.0.9");
		shopOrder0.setPaymentType("card");
		shopOrder0.setInvoiceAddress(address0);
		shopOrder0.setUserAccount(user0);
		shopOrderItem0 = new ShopOrderItem();
		shopOrderItem0.setShopItem(shopItem0);
		shopOrderItem0.setPrice(shopItem0.getPrice());
		shopOrderItem0.setShopOrder(shopOrder0);
		shopOrder1 = new ShopOrder();
		shopOrder1.setFirstname("Marc");
		shopOrder1.setLastname("Eybert");
		shopOrder1.setEmail("marc.eybert@yahoo.fr");
		shopOrder1.setCurrency("EUR");
		shopOrder1.setOrderDate(new LocalDateTime().minusDays(2));
		shopOrder1.setDueDate(new LocalDateTime().plusDays(30));
		shopOrder1.setStatus("paid");
		shopOrder1.setClientIp("127.0.0.9");
		shopOrder1.setPaymentType("card");
		shopOrder1.setInvoiceAddress(address0);
		shopOrder1.setUserAccount(user0);
		shopOrderItem1 = new ShopOrderItem();
		shopOrderItem1.setName("book");
		shopOrderItem1.setPrice("100");
		shopOrderItem1.setShopOrder(shopOrder1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		user0 = userDao.makePersistent(user0);
		shopItem0 = shopItemDao.makePersistent(shopItem0);
		shopOrder0 = shopOrderDao.makePersistent(shopOrder0);
		shopOrder1 = shopOrderDao.makePersistent(shopOrder1);
		shopOrderItem0 = shopOrderItemDao.makePersistent(shopOrderItem0);
		shopOrderItem1 = shopOrderItemDao.makePersistent(shopOrderItem1);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(shopOrderItem0.getId());
		assertNotSame(shopOrderItem0.hashCode(), 0L);
		assertFalse(shopOrderItem0.toString().equals(""));
		ShopOrderItem shopOrderItem = shopOrderItemDao.findWithId(shopOrderItem0.getId(), true);
		assertEquals(shopOrderItem0.hashCode(), shopOrderItem.hashCode());
		assertEquals(shopOrderItem0.getName(), shopOrderItem.getName());
		assertEquals(shopOrderItem0.getImageUrl(), shopOrderItem.getImageUrl());
		assertEquals(shopOrderItem0.getIsGift(), shopOrderItem.getIsGift());
		assertEquals(shopOrderItem0.getOptions(), shopOrderItem.getOptions());
		assertEquals(shopOrderItem0.getPrice(), shopOrderItem.getPrice());
		assertEquals(shopOrderItem0.getQuantity(), shopOrderItem.getQuantity());
		assertEquals(shopOrderItem0.getReference(), shopOrderItem.getReference());
		assertEquals(shopOrderItem0.getShippingFee(), shopOrderItem.getShippingFee());
		assertEquals(shopOrderItem0.getShortDescription(), shopOrderItem.getShortDescription());
		assertNotNull(shopOrderItem.getId());
	}
	@Test
	public void testFindWithPatternLike() {
		Page<ShopOrderItem> page = shopOrderItemDao.findWithPatternLike("boo", 0, 0);
		List<ShopOrderItem> shopOrderItems = page.getPageItems();
		assertEquals(1, shopOrderItems.size());
		assertEquals(shopOrderItem1.getId(), shopOrderItems.get(0).getId());
		page = shopOrderItemDao.findWithPatternLike("cannot be found", 0, 0);
		shopOrderItems = page.getPageItems();
		assertEquals(0, shopOrderItems.size());
	}
	
	@Test
	public void testFindWithShopOrder() {
		Page<ShopOrderItem> page = shopOrderItemDao.findWithShopOrder(shopOrder0, 0, 0);
		List<ShopOrderItem> shopOrderItems = page.getPageItems();
		assertEquals(1, shopOrderItems.size());
	}
	
	@Test
	public void testFindWithShopOrderAndShopItem() {
		ShopOrderItem shopOrderItem = shopOrderItemDao.findWithShopOrderAndShopItem(shopOrder0, shopItem0);
		assertEquals(shopItem0, shopOrderItem.getShopItem());
		shopOrderItem = shopOrderItemDao.findWithShopOrderAndShopItem(shopOrder1, null);
		assertEquals(shopOrderItem1.getId(), shopOrderItem.getId());
	}

}
