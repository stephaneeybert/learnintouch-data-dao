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
import com.thalasoft.learnintouch.data.dao.ContactRefererDao;
import com.thalasoft.learnintouch.data.dao.domain.Contact;
import com.thalasoft.learnintouch.data.dao.domain.ContactReferer;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ContactRefererDaoTest extends AbstractDaoTest {

	private static Logger logger = LoggerFactory.getLogger(ContactRefererDaoTest.class);

	@Autowired
	private ContactRefererDao contactRefererDao;

	@Autowired
	private ContactDao contactDao;

	private ContactReferer contactReferer0;
	private ContactReferer contactReferer1;

	protected void setContactRefererDao(ContactRefererDao contactRefererDao) {
		this.contactRefererDao = contactRefererDao;
	}

	protected void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	public ContactRefererDaoTest() {
		contactReferer0 = new ContactReferer();
		contactReferer0.setDescription("Google");
		contactReferer0.setListOrder(2);
		contactReferer1 = new ContactReferer();
		contactReferer1.setDescription("Facebook");
		contactReferer1.setListOrder(1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		contactReferer0 = contactRefererDao.makePersistent(contactReferer0);
		contactReferer1 = contactRefererDao.makePersistent(contactReferer1);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(contactReferer0.getId());
		assertNotSame(contactReferer0.hashCode(), 0L);
		assertFalse(contactReferer0.toString().equals(""));
		ContactReferer retrievedContactReferer = contactRefererDao.findWithId(contactReferer0.getId(), true);
		assertEquals(contactReferer0.hashCode(), retrievedContactReferer.hashCode());
		assertEquals(contactReferer0.getDescription(), retrievedContactReferer.getDescription());
		assertNotNull(retrievedContactReferer.getId());
	}

	@Test
	public void testFindAll() {
		Page<ContactReferer> page = contactRefererDao.findAll(0, 0);
		List<ContactReferer> contactReferers = page.getPageItems();  
		assertEquals(2, contactReferers.size());
		assertEquals(contactReferer1.getDescription(), contactReferers.get(0).getDescription());
		assertEquals(contactReferer0.getDescription(), contactReferers.get(1).getDescription());
	}

	@Test
	public void testFindAllOrderById() {
		List<ContactReferer> contactReferers = contactRefererDao.findAllOrderById();
		assertEquals(contactReferer0.getListOrder(), contactReferers.get(0).getListOrder());
		assertEquals(contactReferer1.getListOrder(), contactReferers.get(1).getListOrder());
	}

	@Test
	public void testFindFirst() {
		ContactReferer contactReferer = contactRefererDao.findFirst();
		assertEquals(contactReferer1.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactReferer1.getDescription(), contactReferer.getDescription());
	}

	@Test
	public void testFindNext() {
		ContactReferer contactReferer = contactRefererDao.findNextWithListOrder(0);
		assertEquals(contactReferer1.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactReferer1.getDescription(), contactReferer.getDescription());
		contactReferer = contactRefererDao.findNextWithListOrder(1);
		assertEquals(contactReferer0.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactReferer0.getDescription(), contactReferer.getDescription());
	}

	@Test
	public void testFindPrevious() {
		ContactReferer contactReferer = contactRefererDao.findPreviousWithListOrder(2);
		assertEquals(contactReferer1.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactReferer1.getDescription(), contactReferer.getDescription());
		contactReferer = contactRefererDao.findPreviousWithListOrder(3);
		assertEquals(contactReferer0.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactReferer0.getDescription(), contactReferer.getDescription());
	}

	@Test
	public void testFindWithListOrder() {
		ContactReferer contactReferer = contactRefererDao.findWithListOrder(1);
		assertEquals(contactReferer1.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactReferer1.getId(), contactReferer.getId());
		contactReferer = contactRefererDao.findWithListOrder(2);
		assertEquals(contactReferer0.getListOrder(), contactReferer.getListOrder());
		assertEquals(contactReferer0.getId(), contactReferer.getId());
	}

	@Test
	public void testCountListOrderDuplicates() {
		long count = contactRefererDao.countListOrderDuplicates();
		assertEquals(0, count);
		contactReferer0.setListOrder(1);
		count = contactRefererDao.countListOrderDuplicates();
		assertEquals(2, count);
	}

	@Test
	public void testDelete() {
		assertEquals(2, contactRefererDao.countAll());
		contactRefererDao.makeTransient(contactReferer0);
		assertEquals(1, contactRefererDao.countAll());
		Contact contact = new Contact();
		contact.setEmail("email");
		contact.setMessage("message");
		LocalDateTime contactDatetime = new LocalDateTime();
		contact.setContactDatetime(contactDatetime);
		contact.setContactReferer(contactReferer1);
		contact = contactDao.makePersistent(contact);
		try {
			contactRefererDao.makeTransient(contactReferer1);
			contactRefererDao.flush();
			fail("The contact referer was deleted when it should not have been.");
		} catch	(DataAccessException e) {
		} finally {
			contactRefererDao.clear();			
		}
		assertEquals(1, contactRefererDao.countAll());
		contact.setContactReferer(null);
		contact = contactDao.makePersistent(contact);
		contactRefererDao.makeTransient(contactReferer1);
		assertEquals(0, contactRefererDao.countAll());
	}

}