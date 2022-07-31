package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.AddressDao;
import com.thalasoft.learnintouch.data.dao.MailListDao;
import com.thalasoft.learnintouch.data.dao.MailListUserDao;
import com.thalasoft.learnintouch.data.dao.UserAccountDao;
import com.thalasoft.learnintouch.data.dao.domain.Address;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.MailListUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class UserAccountDaoTest extends AbstractDaoTest {

    @Autowired
	private UserAccountDao userDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private MailListDao mailListDao;

	@Autowired
	private MailListUserDao mailListUserDao;

	private UserAccount user0;
	private UserAccount user1;
	private UserAccount user2;
	private Address address0;
	private Address address1;
	private MailList mailList0;

	protected void setUserDao(UserAccountDao userDao) {
		this.userDao = userDao;
	}

	protected void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	protected void setMailListDao(MailListDao mailListDao) {
		this.mailListDao = mailListDao;
	}

	protected void setMailListUserDao(MailListUserDao mailListUserDao) {
		this.mailListUserDao = mailListUserDao;
	}

	public UserAccountDaoTest() {
		address0 = new Address();
		address0.setAddress1("6 place Emile");
		address0.setAddress2("Couteron");
		address0.setZipCode("13100");
		address0.setCity("Aix-en-Provence");
		address0.setPostalBox("PoBox 001");
		address0.setState("PACA");
		address0.setCountry("Norge");

		address1 = new Address();
		address1.setAddress1("Totovg, 1");
		address1.setZipCode("13100");
		address1.setCity("Oslo");
		address1.setCountry("France");

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

		user1 = new UserAccount();
		user1.setEmail("marc@yahoo.se");
		user1.setPassword("ezaezf");
		user1.setPasswordSalt("43453");
		user1.setFirstname("Marc");
		user1.setLastname("Eybert");
		user1.setOrganisation("Russie");
		user1.setAddress(address1);
		user1.setMailSubscribe(true);
		user1.setSmsSubscribe(true);
		user1.setValidUntil(new LocalDateTime().plusDays(10));
		user1.setCreationDatetime(new LocalDateTime().plusDays(1));
		user1.setLastLogin(new LocalDateTime());

		user2 = new UserAccount();
		user2.setEmail("cyrileybert@yahoo.se");
		user2.setPassword("ezaezf");
		user2.setPasswordSalt("43453");
		user2.setFirstname("Cyril");
		user2.setLastname("Eybert");
		user2.setOrganisation("Groupama");
		user2.setMailSubscribe(true);
		user2.setSmsSubscribe(true);
		user2.setCreationDatetime(new LocalDateTime().plusDays(2));
		user2.setLastLogin(new LocalDateTime());
	}

	@Before
	public void beforeAnyTest() throws Exception {
		user1 = userDao.makePersistent(user1);
		user0 = userDao.makePersistent(user0);
		user2 = userDao.makePersistent(user2);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(user0.getId());
		UserAccount loadedUser = userDao.findWithId(user0.getId(), false);
		assertNotSame(user0.hashCode(), 0L);
		assertEquals(user0.hashCode(), loadedUser.hashCode());
		assertFalse(user0.toString().equals(""));
		assertNotNull(loadedUser.getId());
		assertEquals(user0.getEmail(), loadedUser.getEmail());
		assertEquals(user0.getAddress().getAddress1(), loadedUser.getAddress().getAddress1());
		assertTrue(userDao.isFoundById(user0.getId()));
		assertTrue(addressDao.isFoundById(user0.getAddress().getId()));
		userDao.makeTransient(user0);
		assertFalse(userDao.isFoundById(user0.getId()));
		assertFalse(addressDao.isFoundById(user0.getAddress().getId()));
	}

	@Test
	public void testFindAll() {
		Page<UserAccount> page = userDao.findAll(0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(3, users.size());
		assertEquals(user2.getFirstname(), users.get(0).getFirstname());
		assertEquals(user1.getFirstname(), users.get(1).getFirstname());
		assertEquals(user0.getFirstname(), users.get(2).getFirstname());
	}

	@Test
	public void testFindWithEmail() {
		UserAccount user = userDao.findWithEmail(user0.getEmail());
		assertEquals(user0.getEmail(), user.getEmail());
		assertEquals(user0.getFirstname(), user.getFirstname());
	}

	@Test
	public void testFindWithEmailAndReadablePassword() {
		UserAccount user = userDao.findWithEmailAndReadablePassword(user0.getEmail(), user0.getReadablePassword());
		assertEquals(user0.getEmail(), user.getEmail());
		assertEquals(user0.getFirstname(), user.getFirstname());
	}

	@Test
	public void testFindWithMobilePhone() {
		Page<UserAccount> page = userDao.findWithMobilePhone(user0.getMobilePhone(), 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user0.getMobilePhone(), users.get(0).getMobilePhone());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<UserAccount> page = userDao.findWithPatternLike("stephan", 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user0.getFirstname(), users.get(0).getFirstname());
		page = userDao.findWithPatternLike("6785", 0, 0);
		users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user0.getFirstname(), users.get(0).getFirstname());
		page = userDao.findWithPatternLike("12345", 0, 0);
		users = page.getPageItems();
		assertEquals(0, users.size());
	}

	@Test
	public void testFindMailSubscribersLikePattern() {
		Page<UserAccount> page = userDao.findMailSubscribersLikePattern("maaarc", 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(0, users.size());
		page = userDao.findMailSubscribersLikePattern("steph", 0, 0);
		users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user0.getFirstname(), users.get(0).getFirstname());
	}

	@Test
	public void testFindNotMailSubscribersLikePattern() {
		user1.setMailSubscribe(false);
		Page<UserAccount> page = userDao.findNotMailSubscribersLikePattern("marc", 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user1.getFirstname(), users.get(0).getFirstname());
		page = userDao.findNotMailSubscribersLikePattern("steph", 0, 0);
		users = page.getPageItems();
		assertEquals(0, users.size());
	}

	@Test
	public void testFindSmsSubscribersLikePattern() {
		user1.setSmsSubscribe(false);
		Page<UserAccount> page = userDao.findSmsSubscribersLikePattern("marc", 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(0, users.size());
		page = userDao.findSmsSubscribersLikePattern("steph", 0, 0);
		users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user0.getFirstname(), users.get(0).getFirstname());
	}

	@Test
	public void testFindNotSmsSubscribersLikePattern() {
		user1.setSmsSubscribe(false);
		Page<UserAccount> page = userDao.findNotSmsSubscribersLikePattern("marc", 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user1.getFirstname(), users.get(0).getFirstname());
		page = userDao.findNotSmsSubscribersLikePattern("steph", 0, 0);
		users = page.getPageItems();
		assertEquals(0, users.size());
	}

	@Test
	public void testFindMailSubscribersLikeCountry() {
		Page<UserAccount> page = userDao.findMailSubscribersLikeCountry(user0.getAddress().getCountry(), 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user0.getAddress().getCountry(), users.get(0).getAddress().getCountry());
	}

	@Test
	public void testFindAllMailSubscribers() {
		Page<UserAccount> page = userDao.findAllMailSubscribers(0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(3, users.size());
	}

	@Test
	public void testFindAllSmsSubscribers() {
		Page<UserAccount> page = userDao.findAllSmsSubscribers(0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
	}

	@Test
	public void testFindNotValid() {
		LocalDateTime today = new LocalDateTime();
		Page<UserAccount> page = userDao.findNotValid(today, 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user0.getFirstname(), users.get(0).getFirstname());
	}

	@Test
	public void testFindValidTemporarily() {
		LocalDateTime today = new LocalDateTime();
		Page<UserAccount> page = userDao.findValidTemporarily(today, 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user1.getFirstname(), users.get(0).getFirstname());
	}

	@Test
	public void testFindValidPermanently() {
		Page<UserAccount> page = userDao.findValidPermanently(0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user2.getFirstname(), users.get(0).getFirstname());
	}

	@Test
	public void testCountNotValidPermanently() {
		assertEquals(2, userDao.countNotValidPermanently());
	}

	@Test
	public void testFindWithImage() {
		List<UserAccount> users = userDao.findWithImage(user0.getImage());
		assertEquals(1, users.size());
		assertEquals(user0.getFirstname(), users.get(0).getFirstname());
		assertEquals(user0.getImage(), users.get(0).getImage());
	}

	@Test
	public void testFindWithCreationDateTime() {
		LocalDateTime fromDate = new LocalDateTime();
		LocalDateTime toDate = new LocalDateTime().plusDays(1);
		Page<UserAccount> page = userDao.findWithCreationDateTime(fromDate, toDate, 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(2, users.size());
		assertEquals(user1.getEmail(), users.get(0).getEmail());
		assertEquals(user0.getEmail(), users.get(1).getEmail());
	}

	@Test
	public void testFindCurrentMailSubscribers() {
		LocalDateTime today = new LocalDateTime();
		Page<UserAccount> page = userDao.findCurrentMailSubscribers(today, 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(2, users.size());
		assertEquals(user2.getFirstname(), users.get(0).getFirstname());
		assertEquals(user1.getFirstname(), users.get(1).getFirstname());
	}

	@Test
	public void testCurrentSmsSubscribers() {
		LocalDateTime today = new LocalDateTime();
		Page<UserAccount> page = userDao.findCurrentSmsSubscribers(today, 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(2, users.size());
		assertEquals(user2.getFirstname(), users.get(0).getFirstname());
		assertEquals(user1.getFirstname(), users.get(1).getFirstname());
	}

	@Test
	public void testExpiredMailSubscribers() {
		LocalDateTime today = new LocalDateTime();
		Page<UserAccount> page = userDao.findExpiredMailSubscribers(today, 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user0.getFirstname(), users.get(0).getFirstname());
	}

	@Test
	public void testExpiredSmsSubscribers() {
		LocalDateTime today = new LocalDateTime();
		Page<UserAccount> page = userDao.findExpiredSmsSubscribers(today, 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(1, users.size());
		assertEquals(user0.getFirstname(), users.get(0).getFirstname());
	}

	@Test
	public void testFindWithMailList() {
		mailList0 = new MailList();
		mailList0.setName("A list");
		mailList0 = mailListDao.makePersistent(mailList0);
		MailListUser mailListUser0 = new MailListUser(mailList0, user0);
		mailListUser0 = mailListUserDao.makePersistent(mailListUser0);
		MailListUser mailListUser1 = new MailListUser(mailList0, user1);
		mailListUser1 = mailListUserDao.makePersistent(mailListUser1);
		Page<UserAccount> page = userDao.findWithMailList(mailList0, 0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(2, users.size());
		assertEquals(user1.getFirstname(), users.get(0).getFirstname());
		assertEquals(user0.getFirstname(), users.get(1).getFirstname());
	}

	@Test
	public void countFindImported() {
		user0.setImported(true);
		user1.setImported(true);
		assertEquals(2, userDao.countImported());
	}

	@Test
	public void testFindImported() {
		user0.setImported(true);
		user1.setImported(true);
		Page<UserAccount> page = userDao.findImported(0, 0);
		List<UserAccount> users = page.getPageItems();
		assertEquals(2, users.size());
	}

	@Test
	public void testResetImported() {
		user0.setImported(true);
		user1.setImported(true);
		assertEquals(2, userDao.countImported());
		assertEquals(2, userDao.resetImported());
		assertEquals(0, userDao.countImported());
	}

}