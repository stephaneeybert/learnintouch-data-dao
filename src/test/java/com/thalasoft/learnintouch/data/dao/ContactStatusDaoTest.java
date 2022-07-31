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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.thalasoft.learnintouch.data.dao.ContactDao;
import com.thalasoft.learnintouch.data.dao.ContactStatusDao;
import com.thalasoft.learnintouch.data.dao.domain.Contact;
import com.thalasoft.learnintouch.data.dao.domain.ContactStatus;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ContactStatusDaoTest extends AbstractDaoTest {

	private static Logger logger = LoggerFactory.getLogger(ContactStatusDaoTest.class);

	@Autowired
	private ContactStatusDao contactStatusDao;
	
	@Autowired
	private ContactDao contactDao;

	private ContactStatus contactStatus0;
	private ContactStatus contactStatus1;
	private String name0 = "received";
	private String description0 = "The contact message has been received.";
	private String name1 = "read";
	private String description1 = "The contact message has been read.";

	protected void setContactStatusDao(ContactStatusDao contactStatusDao) {
		this.contactStatusDao = contactStatusDao;
	}

	protected void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	public ContactStatusDaoTest() {
		contactStatus0 = new ContactStatus();
		contactStatus0.setName(name0);
		contactStatus0.setDescription(description0);
		contactStatus0.setListOrder(1);
		contactStatus1 = new ContactStatus();
		contactStatus1.setName(name1);
		contactStatus1.setDescription(description1);
		contactStatus1.setListOrder(2);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		contactStatus1 = contactStatusDao.makePersistent(contactStatus1);
		contactStatus0 = contactStatusDao.makePersistent(contactStatus0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(contactStatus0.getId());
		assertNotSame(contactStatus0.hashCode(), 0L);
		assertFalse(contactStatus0.toString().equals(""));
		ContactStatus retrievedContactStatus = contactStatusDao.findWithId(contactStatus0.getId(), true);
		assertEquals(contactStatus0.hashCode(), retrievedContactStatus.hashCode());
		assertEquals(contactStatus0.getDescription(), retrievedContactStatus.getDescription());
		assertNotNull(retrievedContactStatus.getId());
	}

	@Test
	public void testFindAll() {
		Page<ContactStatus> page = contactStatusDao.findAll(0, 0);
		List<ContactStatus> contactReferers = page.getPageItems();
		assertEquals(2, contactReferers.size());
		assertEquals(name0, contactReferers.get(0).getName());
		assertEquals(description0, contactReferers.get(0).getDescription());
		assertEquals(name1, contactReferers.get(1).getName());
		assertEquals(description1, contactReferers.get(1).getDescription());
	}

	@Test
	public void testFindAllOrderById() {
		List<ContactStatus> contactReferers = contactStatusDao.findAllOrderById();
		assertEquals(2, contactReferers.get(0).getListOrder());
		assertEquals(1, contactReferers.get(1).getListOrder());
		assertEquals(name1, contactReferers.get(0).getName());
		assertEquals(description1, contactReferers.get(0).getDescription());
		assertEquals(name0, contactReferers.get(1).getName());
		assertEquals(description0, contactReferers.get(1).getDescription());
	}

	@Test
	public void testFindFirst() {
		ContactStatus contactReferer = contactStatusDao.findFirst();
		assertEquals(contactStatus0.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactStatus0.getDescription(), contactReferer.getDescription());
	}

	@Test
	public void testFindNext() {
		ContactStatus contactReferer = contactStatusDao.findNextWithListOrder(0);
		assertEquals(contactStatus0.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactStatus0.getDescription(), contactReferer.getDescription());
		contactReferer = contactStatusDao.findNextWithListOrder(1);
		assertEquals(contactStatus1.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactStatus1.getDescription(), contactReferer.getDescription());
	}

	@Test
	public void testFindPrevious() {
		ContactStatus contactReferer = contactStatusDao.findPreviousWithListOrder(2);
		assertEquals(contactStatus0.getListOrder(), contactReferer.getListOrder());
		assertEquals(description0, contactReferer.getDescription());
		contactReferer = contactStatusDao.findPreviousWithListOrder(3);
		assertEquals(contactStatus1.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactStatus1.getDescription(), contactReferer.getDescription());
	}

	@Test
	public void testFindWithListOrder() {
		ContactStatus contactReferer = contactStatusDao.findWithListOrder(1);
		assertEquals(contactStatus0.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactStatus0.getId(), contactReferer.getId());
		contactReferer = contactStatusDao.findWithListOrder(2);
		assertEquals(contactStatus1.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactStatus1.getId(), contactReferer.getId());
	}

	@Test
	public void testCountDuplicateListOrders() {
		long count = contactStatusDao.countListOrderDuplicates();
		assertEquals(0, count);
		contactStatus1.setListOrder(1);
		count = contactStatusDao.countListOrderDuplicates();
		assertEquals(2, count);
	}

	@Test
	public void testDelete() {
		long count = contactStatusDao.countAll();
		assertEquals(2, count);
		contactStatusDao.makeTransient(contactStatus0);
		count = contactStatusDao.countAll();
		assertEquals(1, count);
		Contact contact = new Contact();
		contact.setEmail("email");
		contact.setMessage("message");
		LocalDateTime contactDatetime = new LocalDateTime();
		contact.setContactDatetime(contactDatetime);
		contact.setContactStatus(contactStatus1);
		contact = contactDao.makePersistent(contact);
		try {
			contactStatusDao.makeTransient(contactStatus1);
			contactStatusDao.flush();
			fail("The contact status was deleted when it should not have been.");
		} catch (DataAccessException e) {
		} finally {
			contactStatusDao.clear();
		}
		count = contactStatusDao.countAll();
		assertEquals(1, count);
		contact = contactDao.makePersistent(contact);
		contact.setContactStatus(null);
		contactStatusDao.makeTransient(contactStatus1);
		count = contactStatusDao.countAll();
		assertEquals(0, count);
	}

}
