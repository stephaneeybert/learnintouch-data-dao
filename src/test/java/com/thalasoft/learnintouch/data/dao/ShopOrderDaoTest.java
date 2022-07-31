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
import com.thalasoft.learnintouch.data.dao.UserAccountDao;
import com.thalasoft.learnintouch.data.dao.domain.Address;
import com.thalasoft.learnintouch.data.dao.domain.ShopItem;
import com.thalasoft.learnintouch.data.dao.domain.ShopOrder;
import com.thalasoft.learnintouch.data.dao.domain.ShopOrderItem;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ShopOrderDaoTest extends AbstractDaoTest {

	@Autowired
	private ShopOrderDao shopOrderDao;

	@Autowired
	private ShopItemDao shopItemDao;

	@Autowired
	private UserAccountDao userDao;

	private Address address0;
	private UserAccount user0;
	private ShopOrderItem shopOrderItem0;
	private ShopOrderItem shopOrderItem1;
	private ShopItem shopItem0;
	private ShopOrder shopOrder0;
	private ShopOrder shopOrder1;

	protected void setShopOrderDao(ShopOrderDao shopOrderDao) {
		this.shopOrderDao = shopOrderDao;
	}

	protected void setShopItemDao(ShopItemDao shopItemDao) {
		this.shopItemDao = shopItemDao;
	}

	protected void setUserDao(UserAccountDao userDao) {
		this.userDao = userDao;
	}

	public ShopOrderDaoTest() {
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
		shopOrder0.setPaymentType("card");
		shopOrder0.setClientIp("127.0.0.9");
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
		shopOrder1.setPaymentType("card");
		shopOrder1.setClientIp("127.0.0.9");
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
		shopOrder1 = shopOrderDao.makePersistent(shopOrder1);
		shopOrder0 = shopOrderDao.makePersistent(shopOrder0);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(shopOrder0.getId());
		assertNotSame(shopOrder0.hashCode(), 0L);
		assertFalse(shopOrder0.toString().equals(""));
		ShopOrder shopOrder = shopOrderDao.findWithId(shopOrder0.getId(), true);
		assertEquals(shopOrder0.hashCode(), shopOrder.hashCode());
		assertEquals(shopOrder0.getCurrency(), shopOrder.getCurrency());
		assertEquals(shopOrder0.getDueDate(), shopOrder.getDueDate());
		assertEquals(shopOrder0.getEmail(), shopOrder.getEmail());
		assertEquals(shopOrder0.getFax(), shopOrder.getFax());
		assertEquals(shopOrder0.getFirstname(), shopOrder.getFirstname());
		assertEquals(shopOrder0.getLastname(), shopOrder.getLastname());
		assertEquals(shopOrder0.getHandlingFee(), shopOrder.getHandlingFee());
		assertEquals(shopOrder0.getInvoiceLanguageCode(), shopOrder.getInvoiceLanguageCode());
		assertEquals(shopOrder0.getInvoiceNote(), shopOrder.getInvoiceNote());
		assertEquals(shopOrder0.getInvoiceNumber(), shopOrder.getInvoiceNumber());
		assertEquals(shopOrder0.getMessage(), shopOrder.getMessage());
		assertEquals(shopOrder0.getMobilePhone(), shopOrder.getMobilePhone());
		assertEquals(shopOrder0.getOrderDate(), shopOrder.getOrderDate());
		assertEquals(shopOrder0.getOrganisation(), shopOrder.getOrganisation());
		assertEquals(shopOrder0.getPaymentTransactionId(), shopOrder.getPaymentTransactionId());
		assertEquals(shopOrder0.getPaymentType(), shopOrder.getPaymentType());
		assertEquals(shopOrder0.getStatus(), shopOrder.getStatus());
		assertEquals(shopOrder0.getTelephone(), shopOrder.getTelephone());
		assertEquals(shopOrder0.getInvoiceAddress().getAddress1(), shopOrder.getInvoiceAddress().getAddress1());
		assertNotNull(shopOrder.getId());
	}

	@Test
	public void testFindAll() {
		Page<ShopOrder> page = shopOrderDao.findAll(0, 0);
		List<ShopOrder> shopOrders = page.getPageItems();
		assertEquals(2, shopOrders.size());
		assertEquals(shopOrder0.getId(), shopOrders.get(0).getId());
		assertEquals(shopOrder1.getId(), shopOrders.get(1).getId());
	}

	@Test
	public void testWithEmail() {
		ShopOrder shopOrder = shopOrderDao.findWithEmail("mittiprovence@yahoo.se");
		assertEquals(shopOrder0.getId(), shopOrder.getId());
	}

	@Test
	public void testFindWithUser() {
		Page<ShopOrder> page = shopOrderDao.findWithUser(user0, 0, 0);
		List<ShopOrder> shopOrders = page.getPageItems();
		assertEquals(2, shopOrders.size());
		assertEquals(shopOrder0.getId(), shopOrders.get(0).getId());
		assertEquals(shopOrder1.getId(), shopOrders.get(1).getId());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<ShopOrder> page = shopOrderDao.findWithPatternLike("steph", 0, 0);
		List<ShopOrder> shopOrders = page.getPageItems();
		assertEquals(1, shopOrders.size());
		assertEquals(shopOrder0.getId(), shopOrders.get(0).getId());
		page = shopOrderDao.findWithPatternLike("marc", 0, 0);
		shopOrders = page.getPageItems();
		assertEquals(1, shopOrders.size());
		assertEquals(shopOrder1.getId(), shopOrders.get(0).getId());
		page = shopOrderDao.findWithPatternLike("eybert", 0, 0);
		shopOrders = page.getPageItems();
		assertEquals(2, shopOrders.size());
		assertEquals(shopOrder0.getId(), shopOrders.get(0).getId());
		assertEquals(shopOrder1.getId(), shopOrders.get(1).getId());
		page = shopOrderDao.findWithPatternLike("cannot be found", 0, 0);
		shopOrders = page.getPageItems();
		assertEquals(0, shopOrders.size());
	}

	@Test
	public void testFindWithStatus() {
		Page<ShopOrder> page = shopOrderDao.findWithStatus(shopOrder0.getStatus(), 0, 0);
		List<ShopOrder> shopOrders = page.getPageItems();
		assertEquals(2, shopOrders.size());
		assertEquals(shopOrder0.getId(), shopOrders.get(0).getId());
		assertEquals(shopOrder1.getId(), shopOrders.get(1).getId());
	}

	@Test
	public void testFindWithStatusAndYearAndMonth() {
		Page<ShopOrder> page = shopOrderDao.findWithStatusAndYearAndMonth(shopOrder0.getStatus(), shopOrder0.getOrderDate().getYear(), shopOrder0.getOrderDate().getMonthOfYear(), 0, 0);
		List<ShopOrder> shopOrders = page.getPageItems();
		assertEquals(1, shopOrders.size());
		assertEquals(shopOrder0.getId(), shopOrders.get(0).getId());
	}

	@Test
	public void testFindWithYearAndMonth() {
		Page<ShopOrder> page = shopOrderDao.findWithYearAndMonth(shopOrder0.getOrderDate().getYear(), shopOrder0.getOrderDate().getMonthOfYear(), 0, 0);
		List<ShopOrder> shopOrders = page.getPageItems();
		assertEquals(1, shopOrders.size());
		assertEquals(shopOrder0.getId(), shopOrders.get(0).getId());
	}

}
