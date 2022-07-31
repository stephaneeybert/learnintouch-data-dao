package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.thalasoft.learnintouch.data.dao.MailCategoryDao;
import com.thalasoft.learnintouch.data.dao.MailDao;
import com.thalasoft.learnintouch.data.dao.domain.Mail;
import com.thalasoft.learnintouch.data.dao.domain.MailCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class MailCategoryDaoTest extends AbstractDaoTest {

	@Autowired
	private MailCategoryDao mailCategoryDao;

	@Autowired
	private MailDao mailDao;

	private MailCategory mailCategory0;
	private MailCategory mailCategory1;
	private String name0 = "news";
	private String name1 = "reminders";
	
	protected void setMailCategoryDao(MailCategoryDao mailCategoryDao) {
		this.mailCategoryDao = mailCategoryDao;
	}

	protected void setMailDao(MailDao mailDao) {
		this.mailDao = mailDao;
	}

	public MailCategoryDaoTest() {
		mailCategory0 = new MailCategory();
		mailCategory0.setName(name0);
		mailCategory1 = new MailCategory();
		mailCategory1.setName(name1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		mailCategory1 = mailCategoryDao.makePersistent(mailCategory1);
		mailCategory0 = mailCategoryDao.makePersistent(mailCategory0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(mailCategory0.getId());
		assertNotSame(mailCategory0.hashCode(), 0L);
		assertFalse(mailCategory0.toString().equals(""));
		MailCategory retrievedMailCategory = mailCategoryDao.findWithId(mailCategory0.getId(), true);
		assertEquals(mailCategory0.hashCode(), retrievedMailCategory.hashCode());
		assertEquals(mailCategory0.getName(), retrievedMailCategory.getName());
		assertNotNull(retrievedMailCategory.getId());
	}
	
	@Test
	public void testFindAll() {
		Page<MailCategory> page = mailCategoryDao.findAll(0, 0);
		List<MailCategory> mailCategories = page.getPageItems();
		assertEquals(2, mailCategories.size());
		assertEquals(name0, mailCategories.get(0).getName());
		assertEquals(name1, mailCategories.get(1).getName());
	}
	
	@Test
	public void testDelete() {
		assertEquals(2, mailCategoryDao.countAll());
		mailCategoryDao.makeTransient(mailCategory0);
		assertEquals(1, mailCategoryDao.countAll());
		Mail mail = new Mail();
		mail.setSubject("Hello");
		mail.setBody("Hello Stephane");
		mail.setMailCategory(mailCategory1);
		LocalDateTime creationDateTime = new LocalDateTime();
		mail.setCreationDatetime(creationDateTime);
		mail = mailDao.makePersistent(mail);
		try {
			mailCategoryDao.makeTransient(mailCategory1);
			mailCategoryDao.flush();
			fail("The mail category was deleted when it should not have been.");
		} catch	(DataAccessException e) {
		} finally {
			mailCategoryDao.clear();			
		}
		assertEquals(1, mailCategoryDao.countAll());
		mail.setMailCategory(null);
		mail = mailDao.makePersistent(mail);
		mailCategoryDao.makeTransient(mailCategory1);
		assertEquals(0, mailCategoryDao.countAll());
	}
	
}
