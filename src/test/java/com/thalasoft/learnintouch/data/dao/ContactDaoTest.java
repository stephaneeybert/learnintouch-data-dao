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

import com.thalasoft.learnintouch.data.dao.ContactDao;
import com.thalasoft.learnintouch.data.dao.ContactRefererDao;
import com.thalasoft.learnintouch.data.dao.ContactStatusDao;
import com.thalasoft.learnintouch.data.dao.domain.Contact;
import com.thalasoft.learnintouch.data.dao.domain.ContactReferer;
import com.thalasoft.learnintouch.data.dao.domain.ContactStatus;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ContactDaoTest extends AbstractDaoTest {

	@Autowired
	private ContactDao contactDao;

	@Autowired
	private ContactStatusDao contactStatusDao;

	@Autowired
	private ContactRefererDao contactRefererDao;

	private Contact contact0;
	private Contact contact1;
	private Contact contact2;
	private ContactStatus contactStatus;
	private ContactReferer contactReferer;

	protected void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}
	
	protected void setContactRefererDao(ContactRefererDao contactRefererDao) {
		this.contactRefererDao = contactRefererDao;
	}
	
	protected void setContactStatusDao(ContactStatusDao contactStatusDao) {
		this.contactStatusDao = contactStatusDao;
	}
	
	public ContactDaoTest() {
		contact0 = new Contact();
		contact0.setContactDatetime(new LocalDateTime());
		contact0.setMessage("Hello Marc");
		contact0.setEmail("stephane@thalasoft.com");
		contact1 = new Contact();
		contact1.setContactDatetime(new LocalDateTime().plusDays(1));
		contact1.setMessage("Hello Steph");
		contact1.setEmail("marc@thalasoft.com");
		contact2 = new Contact();
		contact2.setContactDatetime(new LocalDateTime().plusDays(2));
		contact2.setMessage("Hello Cyr");
		contact2.setEmail("cyril@thalasoft.com");
		contactStatus = new ContactStatus();
		contactStatus.setName("Read");
		contactReferer = new ContactReferer();
		contactReferer.setDescription("Google");
	}
	
	@Before
	public void beforeAnyTest() throws Exception {
		contact0 = contactDao.makePersistent(contact0);
		contact2 = contactDao.makePersistent(contact2);
		contact1 = contactDao.makePersistent(contact1);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(contact0.getId());
		assertNotSame(contact0.hashCode(), 0L);
		assertFalse(contact0.toString().equals(""));
		Contact retrievedContact = contactDao.findWithId(contact0.getId(), true);
		assertEquals(contact0.hashCode(), retrievedContact.hashCode());
		assertEquals(contact0.getMessage(), retrievedContact.getMessage());
		assertNotNull(retrievedContact.getId());
	}
	
	@Test
	public void testFindAll() {
		Page<Contact> page = contactDao.findAll(0, 0);
		List<Contact> contacts = page.getPageItems();
		assertEquals(3, contacts.size());
		assertEquals("Hello Cyr", contacts.get(0).getMessage());
		assertEquals("Hello Steph", contacts.get(1).getMessage());
		assertEquals("Hello Marc", contacts.get(2).getMessage());
	}
	
	@Test
	public void testFindNotInGarbage() {
		contact0.setGarbage(true);
		Page<Contact> page = contactDao.findNotInGarbage(0, 0);
		List<Contact> contacts = page.getPageItems();
		assertEquals(2, contacts.size());
		assertEquals(false, contacts.get(0).getGarbage());
	}
	
	@Test
	public void testFindInGarbage() {
		contact0.setGarbage(true);
		Page<Contact> page = contactDao.findInGarbage(0, 0);
		List<Contact> contacts = page.getPageItems();
		assertEquals(1, contacts.size());
		assertEquals(true, contacts.get(0).getGarbage());
	}
	
	@Test
	public void testFindWithStatus() {
		contactStatus = contactStatusDao.makePersistent(contactStatus);
		contact0.setContactStatus(contactStatus);
		contact0.setGarbage(true);
		contact1.setContactStatus(contactStatus);
		Page<Contact> page = contactDao.findWithStatus(contactStatus, 0, 0);
		List<Contact> contacts = page.getPageItems();
		assertEquals(2, contacts.size());
	}

	@Test
	public void testFindNotInGarbageByStatus() {		
		contactStatus = contactStatusDao.makePersistent(contactStatus);
		contact0.setContactStatus(contactStatus);
		Page<Contact> page = contactDao.findNotInGarbageByStatus(contactStatus, 0, 0);
		List<Contact> contacts = page.getPageItems();
		assertEquals(1, contacts.size());
		assertEquals("Hello Marc", contacts.get(0).getMessage());
	}
	
	@Test
	public void testFindWithReferer() {
		contactReferer = contactRefererDao.makePersistent(contactReferer);
		contact0.setContactReferer(contactReferer);
		contact0.setGarbage(true);
		contact1.setContactReferer(contactReferer);
		Page<Contact> page = contactDao.findWithReferer(contactReferer, 0, 0);
		List<Contact> contacts = page.getPageItems();
		assertEquals(2, contacts.size());
	}
	
	@Test
	public void testDeleteByDate() {
		LocalDateTime sinceDate = new LocalDateTime().plusDays(1);
		long countDeleted = contactDao.deleteByDate(sinceDate);
		assertEquals(2, countDeleted);
		Page<Contact> page = contactDao.findAll(0, 0);
		List<Contact> contacts = page.getPageItems();
		assertEquals(1, contacts.size());
	}
	
	@Test
	public void testRetrieveLastPage() {
		Page<Contact> page = contactDao.findAll(3, 1);
		List<Contact> contacts = page.getPageItems();
		assertEquals(1, contacts.size());
		assert (page.isLastPage());
		assertEquals(1, page.getPageSize());
		assertEquals(3, page.getNumberOfPages());
		assertEquals(2, page.getPreviousPageNumber());
	}
    
}