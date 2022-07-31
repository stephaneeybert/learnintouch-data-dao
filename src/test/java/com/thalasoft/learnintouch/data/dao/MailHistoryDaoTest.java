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

import com.thalasoft.learnintouch.data.dao.AdminDao;
import com.thalasoft.learnintouch.data.dao.MailHistoryDao;
import com.thalasoft.learnintouch.data.dao.MailListDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.MailHistory;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class MailHistoryDaoTest extends AbstractDaoTest {

	@Autowired
	private MailHistoryDao mailHistoryDao;

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private MailListDao mailListDao;

	private MailHistory mailHistory0;
	private MailHistory mailHistory1;
	private MailHistory mailHistory2;
	private Admin admin;
	private MailList mailList;
	
	protected void setMailHistoryDao(MailHistoryDao mailHistoryDao) {
		this.mailHistoryDao = mailHistoryDao;
	}

	protected void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	protected void setMailListDao(MailListDao mailListDao) {
		this.mailListDao = mailListDao;
	}

	public MailHistoryDaoTest() {
		mailHistory0 = new MailHistory();
		mailHistory0.setSubject("Hello");
		mailHistory0.setBody("Hello from Stephane");
		mailHistory0.setSendDatetime(new LocalDateTime());

		mailHistory1 = new MailHistory();
		mailHistory1.setSubject("Salut");
		mailHistory1.setBody("Salut mon pote");
		mailHistory1.setAttachments("file.txt,image.png");
		mailHistory1.setSendDatetime(new LocalDateTime().plusDays(1));

		mailHistory2 = new MailHistory();
		mailHistory2.setSubject("Hi");
		mailHistory2.setBody("Hi from Stephane");
		mailHistory2.setSendDatetime(new LocalDateTime());

		mailList = new MailList();
		mailList.setName("Next party");

		admin = new Admin();
		admin.setFirstname("Stephane");
		admin.setLastname("Eybert");
		admin.setLogin("stephane");
		admin.setPassword("toto");
        admin.setPasswordSalt("");
        admin.setEmail("stephane@thalasoft.com");
	}

	@Before
	public void beforeAnyTest() throws Exception {
		mailHistory1 = mailHistoryDao.makePersistent(mailHistory1);
		mailHistory0 = mailHistoryDao.makePersistent(mailHistory0);
		mailHistory2 = mailHistoryDao.makePersistent(mailHistory2);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(mailHistory0.getId());
		assertNotSame(mailHistory0.hashCode(), 0L);
		assertFalse(mailHistory0.toString().equals(""));
		MailHistory retrievedMailHistory = mailHistoryDao.findWithId(mailHistory0.getId(), true);
		assertEquals(mailHistory0.hashCode(), retrievedMailHistory.hashCode());
		assertEquals(mailHistory0.getSubject(), retrievedMailHistory.getSubject());
		assertEquals(mailHistory0.getBody(), retrievedMailHistory.getBody());
		assertNotNull(retrievedMailHistory.getId());
	}

	@Test
	public void testFindAll() {
		Page<MailHistory> page = mailHistoryDao.findAll(0, 0);
		List<MailHistory> mailHistories = page.getPageItems();
		assertEquals(3, mailHistories.size());
		assertEquals(mailHistory0.getSubject(), mailHistories.get(0).getSubject());
		assertEquals(mailHistory2.getSubject(), mailHistories.get(1).getSubject());
		assertEquals(mailHistory1.getSubject(), mailHistories.get(2).getSubject());
	}

	@Test
	public void testFindWithAdmin() {
		admin = adminDao.makePersistent(admin);
		mailHistory1.setAdmin(admin);
		mailHistory2.setAdmin(admin);
		Page<MailHistory> page = mailHistoryDao.findWithAdmin(admin, 0, 0);
		List<MailHistory> mailHistories = page.getPageItems();
		assertEquals(2, mailHistories.size());
		assertEquals(mailHistory2.getSubject(), mailHistories.get(0).getSubject());
		assertEquals(mailHistory1.getSubject(), mailHistories.get(1).getSubject());
		mailHistory2.setAdmin(null);
		page = mailHistoryDao.findWithAdmin(null, 0, 0);
		mailHistories = page.getPageItems();
		assertEquals(2, mailHistories.size());
		assertEquals(mailHistory0.getSubject(), mailHistories.get(0).getSubject());
		assertEquals(mailHistory2.getSubject(), mailHistories.get(1).getSubject());
	}
	
	@Test
	public void testFindWithMailList() {
		mailList = mailListDao.makePersistent(mailList);
		mailHistory0.setMailList(mailList);
		mailHistory2.setMailList(mailList);
		Page<MailHistory> page = mailHistoryDao.findWithMailList(mailList, 0, 0);
		List<MailHistory> mailHistories = page.getPageItems();
		assertEquals(2, mailHistories.size());
		assertEquals(mailHistory0.getSubject(), mailHistories.get(0).getSubject());
		assertEquals(mailHistory2.getSubject(), mailHistories.get(1).getSubject());
		page = mailHistoryDao.findWithMailList(null, 0, 0);
		mailHistories = page.getPageItems();
		assertEquals(1, mailHistories.size());
		assertEquals(mailHistory1.getSubject(), mailHistories.get(0).getSubject());
	}

	@Test
	public void testFindWithPatternLike() {
		admin = adminDao.makePersistent(admin);
		mailHistory0.setAdmin(admin);
		mailHistory1.setAdmin(admin);
		Page<MailHistory> page = mailHistoryDao.findWithPatternLike("not found", 0, 0);
		List<MailHistory> mailHistories = page.getPageItems();
		assertEquals(0, mailHistories.size());
		page = mailHistoryDao.findWithPatternLike("steph", 0, 0);
		mailHistories = page.getPageItems();
		assertEquals(3, mailHistories.size());
		assertEquals(mailHistory0.getSubject(), mailHistories.get(0).getSubject());
		assertEquals(mailHistory2.getSubject(), mailHistories.get(1).getSubject());
		assertEquals(mailHistory1.getSubject(), mailHistories.get(2).getSubject());
		page = mailHistoryDao.findWithPatternLike("salu", 0, 0);
		mailHistories = page.getPageItems();
		assertEquals(1, mailHistories.size());
		assertEquals(mailHistory1.getSubject(), mailHistories.get(0).getSubject());
	}
	
	@Test
	public void testDeleteAll() {
		long countDeleted = mailHistoryDao.deleteAll();
		assertEquals(3, countDeleted);
		Page<MailHistory> page = mailHistoryDao.findAll(0, 0);
		List<MailHistory> mailHistories = page.getPageItems();
		assertEquals(0, mailHistories.size());
	}
	
}
