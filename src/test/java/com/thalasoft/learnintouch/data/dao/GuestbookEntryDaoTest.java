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

import com.thalasoft.learnintouch.data.dao.GuestbookEntryDao;
import com.thalasoft.learnintouch.data.dao.domain.GuestbookEntry;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class GuestbookEntryDaoTest extends AbstractDaoTest {

	@Autowired
	private GuestbookEntryDao guestbookDao;

	private GuestbookEntry guestbookEntry0;
	private GuestbookEntry guestbookEntry1;
	
	protected void setGuestbookDao(GuestbookEntryDao guestbookDao) {
		this.guestbookDao = guestbookDao;
	}

	public GuestbookEntryDaoTest() {
		guestbookEntry0 = new GuestbookEntry();
		guestbookEntry0.setBody("body");
		// See if setting of the releaseDateTime should and could be done in the constructor 
		LocalDateTime releaseDateTime = new LocalDateTime();
		guestbookEntry0.setPublicationDatetime(releaseDateTime);
		guestbookEntry1 = new GuestbookEntry();
		guestbookEntry1.setBody("body");
		guestbookEntry1.setPublicationDatetime(releaseDateTime);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		guestbookEntry1 = guestbookDao.makePersistent(guestbookEntry1);
		guestbookEntry0 = guestbookDao.makePersistent(guestbookEntry0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(guestbookEntry0.getId());
		assertNotSame(guestbookEntry0.hashCode(), 0L);
		assertFalse(guestbookEntry0.toString().equals(""));
		GuestbookEntry retrievedFormValid = guestbookDao.findWithId(guestbookEntry0.getId(), false);
		assertEquals(guestbookEntry0.hashCode(), retrievedFormValid.hashCode());
		assertEquals(guestbookEntry0.getBody(), retrievedFormValid.getBody());
		assertNotNull(retrievedFormValid.getId());
	}

	@Test
	public void testFindAll() {
		Page<GuestbookEntry> page = guestbookDao.findAll(0, 0);
		List<GuestbookEntry> guestbooks = page.getPageItems();
		assertEquals(2, guestbooks.size());
	}

}
