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

import com.thalasoft.learnintouch.data.dao.MailAddressDao;
import com.thalasoft.learnintouch.data.dao.domain.MailAddress;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class MailAddressDaoTest extends AbstractDaoTest {

	@Autowired
	private MailAddressDao mailAddressDao;

	private MailAddress mailAddress0;
	private MailAddress mailAddress1;
	private MailAddress mailAddress2;
	
	protected void setMailAddressDao(MailAddressDao mailAddressDao) {
		this.mailAddressDao = mailAddressDao;
	}

	public MailAddressDaoTest() {
		mailAddress0 = new MailAddress();
		mailAddress0.setEmail("stephane@yahoo.fr");
		mailAddress0.setFirstname("Stephane");
		mailAddress0.setLastname("Eybert");
		mailAddress0.setTextComment("Not much to say");
		mailAddress0.setCreationDatetime(new LocalDateTime());
		mailAddress1 = new MailAddress();
		mailAddress1.setEmail("marc@yahoo.fr");
		mailAddress1.setFirstname("Marc");
		mailAddress1.setLastname("Eybert");
		mailAddress1.setCreationDatetime(new LocalDateTime().plusDays(1));
		mailAddress2 = new MailAddress();
		mailAddress2.setEmail("cyril@gmail.com");
		mailAddress2.setFirstname("Cyril");
		mailAddress2.setLastname("Eybert");
		mailAddress2.setCreationDatetime(new LocalDateTime().plusDays(2));
	}

	@Before
	public void beforeAnyTest() throws Exception {
		mailAddress0 = mailAddressDao.makePersistent(mailAddress0);
		mailAddress1 = mailAddressDao.makePersistent(mailAddress1);
		mailAddress2 = mailAddressDao.makePersistent(mailAddress2);
	}
	
	@Test
	public void testResetImport() {
		mailAddress0.setImported(true);
		mailAddress1.setImported(true);
		mailAddress2.setImported(false);
		assertEquals(3, mailAddressDao.resetImport());
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(mailAddress0.getId());
		assertNotSame(mailAddress0.hashCode(), 0L);
		assertFalse(mailAddress0.toString().equals(""));
		MailAddress retrievedMailAddress = mailAddressDao.findWithId(mailAddress0.getId(), true);
		assertEquals(mailAddress0.hashCode(), retrievedMailAddress.hashCode());
		assertEquals(mailAddress0.getEmail(), retrievedMailAddress.getEmail());
		assertEquals(mailAddress0.getFirstname(), retrievedMailAddress.getFirstname());
		assertEquals(mailAddress0.getLastname(), retrievedMailAddress.getLastname());
		assertNotNull(retrievedMailAddress.getId());
	}

	@Test
	public void testFindAll() {
		Page<MailAddress> page = mailAddressDao.findAll(0, 0);
		List<MailAddress> mailAddresses = page.getPageItems();
		assertEquals(3, mailAddresses.size());
		assertEquals(mailAddress2.getEmail(), mailAddresses.get(0).getEmail());
		assertEquals(mailAddress1.getEmail(), mailAddresses.get(1).getEmail());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(2).getEmail());
	}

	@Test
	public void testFindWithEmail() {
		MailAddress mailAddress = mailAddressDao.findWithEmail("marc@yahoo.fr");
		assertEquals(mailAddress1.getEmail(), mailAddress.getEmail());
		mailAddress = mailAddressDao.findWithEmail("cyril@gmail.com");
		assertEquals(mailAddress2.getEmail(), mailAddress.getEmail());
	}
	
	@Test
	public void testFindImported() {
		mailAddress0.setImported(true);
		mailAddress1.setImported(true);
		Page<MailAddress> page = mailAddressDao.findImported(0, 0);
		List<MailAddress> mailAddresses = page.getPageItems();
		assertEquals(2, mailAddresses.size());
		assertEquals(mailAddress1.getEmail(), mailAddresses.get(0).getEmail());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(1).getEmail());
	}

	@Test
	public void testFindSubscribers() {
		mailAddress0.setSubscribe(true);
		mailAddress2.setSubscribe(true);
		Page<MailAddress> page = mailAddressDao.findSubscribers(0, 0);
		List<MailAddress> mailAddresses = page.getPageItems();
		assertEquals(2, mailAddresses.size());
		assertEquals(mailAddress2.getEmail(), mailAddresses.get(0).getEmail());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(1).getEmail());
	}

	@Test
	public void testFindNonSubscribers() {
		mailAddress0.setSubscribe(true);
		mailAddress2.setSubscribe(true);
		Page<MailAddress> page = mailAddressDao.findNonSubscribers(0, 0);
		List<MailAddress> mailAddresses = page.getPageItems();
		assertEquals(1, mailAddresses.size());
		assertEquals(mailAddress1.getEmail(), mailAddresses.get(0).getEmail());
	}

	@Test
	public void testFindSubscribersWithCountryLike() {
		mailAddress0.setSubscribe(true);
		mailAddress0.setCountry("France");
		mailAddress2.setSubscribe(true);
		mailAddress2.setCountry("Ireland");
		Page<MailAddress> page = mailAddressDao.findSubscribersWithCountryLike("an", 0, 0);
		List<MailAddress> mailAddresses = page.getPageItems();
		assertEquals(2, mailAddresses.size());
		assertEquals(mailAddress2.getEmail(), mailAddresses.get(0).getEmail());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(1).getEmail());
		page = mailAddressDao.findSubscribersWithCountryLike("fran", 0, 0);
		mailAddresses = page.getPageItems();
		assertEquals(1, mailAddresses.size());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(0).getEmail());
	}

	@Test
	public void testFindLikeCountry() {
		mailAddress0.setSubscribe(true);
		mailAddress0.setCountry("France");
		mailAddress2.setSubscribe(true);
		mailAddress2.setCountry("Ireland");
		Page<MailAddress> page = mailAddressDao.findWithCountryLike("an", 0, 0);
		List<MailAddress> mailAddresses = page.getPageItems();
		assertEquals(2, mailAddresses.size());
		assertEquals(mailAddress2.getEmail(), mailAddresses.get(0).getEmail());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(1).getEmail());
		page = mailAddressDao.findWithCountryLike("fran", 0, 0);
		mailAddresses = page.getPageItems();
		assertEquals(1, mailAddresses.size());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(0).getEmail());
	}

	@Test
	public void testFindWithPatternLike() {
		mailAddress0.setSubscribe(true);
		mailAddress0.setCountry("France");
		mailAddress2.setSubscribe(true);
		mailAddress2.setCountry("Ireland");
		Page<MailAddress> page = mailAddressDao.findWithPatternLike("an", 0, 0);
		List<MailAddress> mailAddresses = page.getPageItems();
		assertEquals(2, mailAddresses.size());
		assertEquals(mailAddress2.getEmail(), mailAddresses.get(0).getEmail());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(1).getEmail());
		page = mailAddressDao.findWithPatternLike("fran", 0, 0);
		mailAddresses = page.getPageItems();
		assertEquals(1, mailAddresses.size());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(0).getEmail());
		page = mailAddressDao.findWithPatternLike("Cyril", 0, 0);
		mailAddresses = page.getPageItems();
		assertEquals(1, mailAddresses.size());
		assertEquals(mailAddress2.getEmail(), mailAddresses.get(0).getEmail());
		page = mailAddressDao.findWithPatternLike("eyb", 0, 0);
		mailAddresses = page.getPageItems();
		assertEquals(3, mailAddresses.size());
		page = mailAddressDao.findWithPatternLike("marc", 0, 0);
		mailAddresses = page.getPageItems();
		assertEquals(1, mailAddresses.size());
		assertEquals(mailAddress1.getEmail(), mailAddresses.get(0).getEmail());
	}

	@Test
	public void testFindWithCreationDateTime() {
		LocalDateTime fromDate = new LocalDateTime();
		LocalDateTime toDate = new LocalDateTime().plusDays(1);
		Page<MailAddress> page = mailAddressDao.findWithCreationDateTimeBetween(fromDate, toDate, 0, 0);
		List<MailAddress> mailAddresses = page.getPageItems();
		assertEquals(2, mailAddresses.size());
		assertEquals(mailAddress1.getEmail(), mailAddresses.get(0).getEmail());
		assertEquals(mailAddress0.getEmail(), mailAddresses.get(1).getEmail());
	}

	@Test
	public void testDeleteImported() {
		mailAddress0.setImported(true);
		mailAddress1.setImported(true);
		mailAddress2.setImported(false);
		assertEquals(2, mailAddressDao.deleteImported());
	}
	
	@Test
	public void testCountImported() {
		mailAddress0.setImported(true);
		mailAddress1.setImported(true);
		mailAddress2.setImported(false);
		assertEquals(2, mailAddressDao.countImported());
	}
	
}
