package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.MailListDao;
import com.thalasoft.learnintouch.data.dao.MailListUserDao;
import com.thalasoft.learnintouch.data.dao.UserAccountDao;
import com.thalasoft.learnintouch.data.dao.domain.MailList;
import com.thalasoft.learnintouch.data.dao.domain.MailListUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

public class MailListUserDaoTest extends AbstractDaoTest {

	@Autowired
	private MailListUserDao mailListUserDao;

	@Autowired
	private MailListDao mailListDao;

	@Autowired
	private UserAccountDao userDao;

	private UserAccount userAccount0;
	private UserAccount userAccount1;
	private UserAccount userAccount2;
	private MailList mailList0;
	private MailList mailList1;
	private MailListUser mailListUser0;
	private MailListUser mailListUser1;
	private MailListUser mailListUser2;
	
	protected void setMailListUserDao(MailListUserDao mailListUserDao) {
		this.mailListUserDao = mailListUserDao;
	}

	protected void setMailListDao(MailListDao mailListDao) {
		this.mailListDao = mailListDao;
	}

	protected void setUserDao(UserAccountDao userDao) {
		this.userDao = userDao;
	}

	public MailListUserDaoTest() {
		userAccount0 = new UserAccount();
		userAccount0.setEmail("marc@thalasoft.com");
		userAccount0.setPassword("gfgg");
		userAccount0.setPasswordSalt("frere");
		userAccount0.setCreationDatetime(new LocalDateTime());
		userAccount0.setFirstname("Marc");
		userAccount0.setLastname("Eybert");
		userAccount0.setLastLogin(new LocalDateTime());

		userAccount1 = new UserAccount();
		userAccount1.setEmail("mittiprovence@yahoo.se");
		userAccount1.setPassword("gfgg");
		userAccount1.setPasswordSalt("frere");
		userAccount1.setCreationDatetime(new LocalDateTime());
		userAccount1.setFirstname("Stephane");
		userAccount1.setLastname("Eybert");
		userAccount1.setLastLogin(new LocalDateTime());

		userAccount2 = new UserAccount();
		userAccount2.setEmail("cyril.eybert@yahoo.es");
		userAccount2.setPassword("gfgg");
		userAccount2.setPasswordSalt("frere");
		userAccount2.setCreationDatetime(new LocalDateTime());
		userAccount2.setFirstname("Cyril");
		userAccount2.setLastname("Eybert");
		userAccount2.setLastLogin(new LocalDateTime());
		
		mailList0 = new MailList();
		mailList0.setName("Liste 0");
		
		mailList1 = new MailList();
		mailList1.setName("Liste 1");
		
		mailListUser0 = new MailListUser();
		mailListUser0.setMailList(mailList0);
		mailListUser0.setUserAccount(userAccount0);

		mailListUser1 = new MailListUser();
		mailListUser1.setMailList(mailList0);
		mailListUser1.setUserAccount(userAccount1);

		mailListUser2 = new MailListUser();
		mailListUser2.setMailList(mailList1);
		mailListUser2.setUserAccount(userAccount1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		userAccount0 = userDao.makePersistent(userAccount0);
		userAccount1 = userDao.makePersistent(userAccount1);
		userAccount2 = userDao.makePersistent(userAccount2);
		mailList0 = mailListDao.makePersistent(mailList0);
		mailList1 = mailListDao.makePersistent(mailList1);
		mailListUser0 = mailListUserDao.makePersistent(mailListUser0);
		mailListUser1 = mailListUserDao.makePersistent(mailListUser1);
		mailListUser2 = mailListUserDao.makePersistent(mailListUser2);
	}
	
	@Test
	public void testFindWithMailList() {
		List<MailListUser> mailListUsers = mailListUserDao.findWithMailList(mailList0);
		assertEquals(2, mailListUsers.size());
		assertEquals(userAccount0.getEmail(), mailListUsers.get(0).getUserAccount().getEmail());		
		assertEquals(userAccount1.getEmail(), mailListUsers.get(1).getUserAccount().getEmail());		
	}

	@Test
	public void testFindWithUser() {
		List<MailListUser> mailListUseres = mailListUserDao.findWithUser(userAccount1);
		assertEquals(2, mailListUseres.size());
		assertEquals(mailList0.getName(), mailListUseres.get(0).getMailList().getName());		
	}

	@Test
	public void testDeleteByMailList() {
		long countDeleted = mailListUserDao.deleteByMailList(mailList0);
		assertEquals(2, countDeleted);
	}

	@Test
	public void testFindWithMailListAndUser() {
		MailListUser mailListUser = mailListUserDao.findWithMailListAndUser(mailList0, userAccount1);
		assertEquals(mailList0.getName(), mailListUser.getMailList().getName());		
		assertEquals(userAccount1.getEmail(), mailListUser.getUserAccount().getEmail());		
		mailListUser = mailListUserDao.findWithMailListAndUser(mailList0, userAccount2);
		assertNull(mailListUser);
	}
		
}
