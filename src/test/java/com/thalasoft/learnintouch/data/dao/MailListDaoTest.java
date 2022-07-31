package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.MailListDao;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class MailListDaoTest extends AbstractDaoTest {

	@Autowired
	private MailListDao mailListDao;

	private MailList mailList0;
	private MailList mailList1;
	private MailList mailList2;
	private String name0 = "Friends";
	private String name1 = "Prospects";
	private String name2 = "Family";
	
	protected void setMailListDao(MailListDao mailListDao) {
		this.mailListDao = mailListDao;
	}

	public MailListDaoTest() {
		mailList0 = new MailList();
		mailList0.setName(name0);
		mailList1 = new MailList();
		mailList1.setName(name1);
		mailList2 = new MailList();
		mailList2.setName(name2);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		mailList1 = mailListDao.makePersistent(mailList1);
		mailList0 = mailListDao.makePersistent(mailList0);
		mailList2 = mailListDao.makePersistent(mailList2);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(mailList0.getId());
		assertNotSame(mailList0.hashCode(), 0L);
		assertFalse(mailList0.toString().equals(""));
		MailList retrievedMail = mailListDao.findWithId(mailList0.getId(), true);
		assertEquals(mailList0.hashCode(), retrievedMail.hashCode());
		assertEquals(mailList0.getName(), retrievedMail.getName());
		assertNotNull(retrievedMail.getId());
	}

	@Test
	public void testFindAll() {
		Page<MailList> page = mailListDao.findAll(0, 0);
		List<MailList> mailLists = page.getPageItems();
		assertEquals(3, mailLists.size());
		assertEquals(name2, mailLists.get(0).getName());
		assertEquals(name0, mailLists.get(1).getName());
		assertEquals(name1, mailLists.get(2).getName());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<MailList> page = mailListDao.findWithPatternLike("frien", 0, 0);
		List<MailList> mailLists = page.getPageItems();
		assertEquals(1, mailLists.size());
		assertEquals(name0, mailLists.get(0).getName());
		page = mailListDao.findWithPatternLike("e", 0, 0);
		mailLists = page.getPageItems();
		assertEquals(2, mailLists.size());
		assertEquals(name0, mailLists.get(0).getName());
		assertEquals(name1, mailLists.get(1).getName());
	}
	
}
