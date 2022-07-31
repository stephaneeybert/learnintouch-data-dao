package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.MailOutboxDao;
import com.thalasoft.learnintouch.data.dao.domain.MailOutbox;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class MailOutboxDaoTest extends AbstractDaoTest {

	@Autowired
	private MailOutboxDao mailOutboxDao;

	private MailOutbox mailOutbox0;
	private MailOutbox mailOutbox1;
	private MailOutbox mailOutbox2;

	protected void setMailOutboxDao(MailOutboxDao mailOutboxDao) {
		this.mailOutboxDao = mailOutboxDao;
	}

	public MailOutboxDaoTest() {
		mailOutbox0 = new MailOutbox();
		mailOutbox0.setFirstname("Stephane");
		mailOutbox0.setLastname("Eybert");
		mailOutbox0.setEmail("mittiprovence@yahoo.se");
		mailOutbox0.setPassword("toto");
		mailOutbox0.setMetaNames("MAIL_META_USER_FIRSTNAME");
		mailOutbox1 = new MailOutbox();
		mailOutbox1.setFirstname("Marc");
		mailOutbox1.setLastname("Eybert");
		mailOutbox1.setEmail("marc@hotmail.com");
		mailOutbox1.setPassword("cab");
		mailOutbox1.setSent(false);
		mailOutbox2 = new MailOutbox();
		mailOutbox2.setFirstname("Cyril");
		mailOutbox2.setLastname("Eybert");
		mailOutbox2.setEmail("cyril@yahoo.es");
		mailOutbox2.setPassword("windsup");
		mailOutbox2.setSent(true);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		mailOutbox0 = mailOutboxDao.makePersistent(mailOutbox0);
		mailOutbox1 = mailOutboxDao.makePersistent(mailOutbox1);
		mailOutbox2 = mailOutboxDao.makePersistent(mailOutbox2);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(mailOutbox0.getId());
		assertNotSame(mailOutbox0.hashCode(), 0L);
		assertFalse(mailOutbox0.toString().equals(""));
		MailOutbox mailOutbox = mailOutboxDao.findWithId(mailOutbox0.getId(), true);
		assertEquals(mailOutbox0.hashCode(), mailOutbox.hashCode());
		assertEquals(mailOutbox0.getFirstname(), mailOutbox.getFirstname());
		assertEquals(mailOutbox0.getLastname(), mailOutbox.getLastname());
		assertEquals(mailOutbox0.getEmail(), mailOutbox.getEmail());
		assertNotNull(mailOutbox.getId());
	}

	@Test
	public void testFindSent() {
		Page<MailOutbox> page = mailOutboxDao.findSent(0, 0);
		List<MailOutbox> mailOutboxes = page.getPageItems();
		assertEquals(1, mailOutboxes.size());
		assertEquals(mailOutbox2.getEmail(), mailOutboxes.get(0).getEmail());
	}

	@Test
	public void testFindUnsent() {
		Page<MailOutbox> page = mailOutboxDao.findUnsent(0, 0);
		List<MailOutbox> mailOutboxes = page.getPageItems();
		assertEquals(2, mailOutboxes.size());
		assertEquals(mailOutbox1.getEmail(), mailOutboxes.get(0).getEmail());
		assertEquals(mailOutbox0.getEmail(), mailOutboxes.get(1).getEmail());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<MailOutbox> page = mailOutboxDao.findWithPatternLike("marc", 0, 0);
		List<MailOutbox> mailOutboxes = page.getPageItems();
		assertEquals(1, mailOutboxes.size());
		assertEquals(mailOutbox1.getFirstname(), mailOutboxes.get(0).getFirstname());
		page = mailOutboxDao.findWithPatternLike("ahoo", 0, 0);
		mailOutboxes = page.getPageItems();
		assertEquals(2, mailOutboxes.size());
		assertEquals(mailOutbox2.getFirstname(), mailOutboxes.get(0).getFirstname());
		assertEquals(mailOutbox0.getFirstname(), mailOutboxes.get(1).getFirstname());
		page = mailOutboxDao.findWithPatternLike("toto", 0, 0);
		mailOutboxes = page.getPageItems();
		assertEquals(0, mailOutboxes.size());
	}

	@Test
	public void testCountUnsent() {
		long count = mailOutboxDao.countUnsent();
		assertEquals(2, count);
	}

	@Test
	public void testDeleteAll() {
		mailOutbox0 = mailOutboxDao.makePersistent(mailOutbox0);
		mailOutbox1 = mailOutboxDao.makePersistent(mailOutbox1);
		mailOutbox2 = mailOutboxDao.makePersistent(mailOutbox2);
		long count = mailOutboxDao.deleteAll();
		assertEquals(3, count);
		Page<MailOutbox> page = mailOutboxDao.findAll(0, 0);
		List<MailOutbox> mailOutboxes = page.getPageItems();
		assertEquals(0, mailOutboxes.size());
	}

}
