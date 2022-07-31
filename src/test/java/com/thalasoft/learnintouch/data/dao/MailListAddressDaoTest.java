package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.MailAddressDao;
import com.thalasoft.learnintouch.data.dao.MailListAddressDao;
import com.thalasoft.learnintouch.data.dao.MailListDao;
import com.thalasoft.learnintouch.data.dao.domain.MailAddress;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.MailListAddress;

public class MailListAddressDaoTest extends AbstractDaoTest {

	@Autowired
	private MailListAddressDao mailListAddressDao;

	@Autowired
	private MailListDao mailListDao;

	@Autowired
	private MailAddressDao mailAddressDao;

	private MailAddress mailAddress0;
	private MailAddress mailAddress1;
	private MailAddress mailAddress2;
	private MailList mailList0;
	private MailList mailList1;
	private MailListAddress mailListAddress0;
	private MailListAddress mailListAddress1;
	private MailListAddress mailListAddress2;
	
	protected void setMailListAddressDao(MailListAddressDao mailListAddressDao) {
		this.mailListAddressDao = mailListAddressDao;
	}

	protected void setMailListDao(MailListDao mailListDao) {
		this.mailListDao = mailListDao;
	}

	protected void setMailAddressDao(MailAddressDao mailAddressDao) {
		this.mailAddressDao = mailAddressDao;
	}

	public MailListAddressDaoTest() {
		mailAddress0 = new MailAddress();
		mailAddress0.setEmail("stephane@thalasoft.com");
		mailAddress0.setCreationDatetime(new LocalDateTime());

		mailAddress1 = new MailAddress();
		mailAddress1.setEmail("mittiprovence@yahoo.se");
		mailAddress1.setCreationDatetime(new LocalDateTime());

		mailAddress2 = new MailAddress();
		mailAddress2.setEmail("cyril.eybert@yahoo.es");
		mailAddress2.setCreationDatetime(new LocalDateTime());
		
		mailList0 = new MailList();
		mailList0.setName("Liste 0");
		
		mailList1 = new MailList();
		mailList1.setName("Liste 1");
		
		mailListAddress0 = new MailListAddress();
		mailListAddress0.setMailList(mailList0);
		mailListAddress0.setMailAddress(mailAddress0);

		mailListAddress1 = new MailListAddress();
		mailListAddress1.setMailList(mailList0);
		mailListAddress1.setMailAddress(mailAddress1);

		mailListAddress2 = new MailListAddress();
		mailListAddress2.setMailList(mailList1);
		mailListAddress2.setMailAddress(mailAddress1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		mailAddress0 = mailAddressDao.makePersistent(mailAddress0);
		mailAddress1 = mailAddressDao.makePersistent(mailAddress1);
		mailAddress2 = mailAddressDao.makePersistent(mailAddress2);
		mailList0 = mailListDao.makePersistent(mailList0);
		mailList1 = mailListDao.makePersistent(mailList1);
		mailListAddress0 = mailListAddressDao.makePersistent(mailListAddress0);
		mailListAddress1 = mailListAddressDao.makePersistent(mailListAddress1);
		mailListAddress2 = mailListAddressDao.makePersistent(mailListAddress2);
	}
	
	@Test
	public void testFindWithMailList() {
		List<MailListAddress> mailListAddresses = mailListAddressDao.findWithMailList(mailList0);
		assertEquals(2, mailListAddresses.size());
		assertEquals(mailAddress1.getEmail(), mailListAddresses.get(0).getMailAddress().getEmail());		
	}

	@Test
	public void testFindWithMailAddress() {
		List<MailListAddress> mailListAddresses = mailListAddressDao.findWithMailAddress(mailAddress1);
		assertEquals(2, mailListAddresses.size());
		assertEquals(mailList0.getName(), mailListAddresses.get(0).getMailList().getName());		
	}

	@Test
	public void testDeleteByMailList() {
		long countDeleted = mailListAddressDao.deleteByMailList(mailList0);
		assertEquals(2, countDeleted);
	}

	@Test
	public void testFindWithMailListAndMailAddress() {
		MailListAddress mailListAddress = mailListAddressDao.findWithMailListAndMailAddress(mailList0, mailAddress1);
		assertEquals(mailList0.getName(), mailListAddress.getMailList().getName());		
		assertEquals(mailAddress1.getEmail(), mailListAddress.getMailAddress().getEmail());		
		mailListAddress = mailListAddressDao.findWithMailListAndMailAddress(mailList0, mailAddress2);
		assertNull(mailListAddress);
	}
		
}
