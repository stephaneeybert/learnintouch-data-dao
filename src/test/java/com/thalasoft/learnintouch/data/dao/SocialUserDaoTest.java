package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.SocialUserDao;
import com.thalasoft.learnintouch.data.dao.UserAccountDao;
import com.thalasoft.learnintouch.data.dao.domain.SocialUser;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;

public class SocialUserDaoTest extends AbstractDaoTest {

	@Autowired
	private UserAccountDao userDao;

	@Autowired
	private SocialUserDao socialUserDao;

	private UserAccount userAccount0;
	private UserAccount userAccount1;
	private UserAccount userAccount2;
	private SocialUser socialUser0;
	private SocialUser socialUser1;
	private String facebookUserId0;
	private String facebookUserId1;

	protected void setUserDao(UserAccountDao userDao) {
		this.userDao = userDao;
	}

	protected void setSocialUserDao(SocialUserDao socialUserDao) {
		this.socialUserDao = socialUserDao;
	}

	public SocialUserDaoTest() {
		userAccount0 = new UserAccount();
		userAccount0.setEmail("stephane@thalasoft.com");
		userAccount0.setPassword("ggdefd");
		userAccount0.setPasswordSalt("43432");
		userAccount0.setReadablePassword("toto");
		userAccount0.setFirstname("Stephane");
		userAccount0.setLastname("Eybert");
		userAccount0.setLastLogin(new LocalDateTime());
		userAccount1 = new UserAccount();
		userAccount1.setEmail("marc@yahoo.se");
		userAccount1.setPassword("ggdefd");
		userAccount1.setPasswordSalt("43432");
		userAccount1.setFirstname("Marc");
		userAccount1.setLastname("Eybert");
		userAccount1.setLastLogin(new LocalDateTime());
		userAccount2 = new UserAccount();
		userAccount2.setEmail("cyrileybert@yahoo.se");
		userAccount2.setPassword("ggdefd");
		userAccount2.setPasswordSalt("43432");
		userAccount2.setFirstname("Cyril");
		userAccount2.setLastname("Eybert");
		userAccount2.setLastLogin(new LocalDateTime());
		socialUser0 = new SocialUser();
		socialUser0.setUserAccount(userAccount0);
		facebookUserId0 = "1351701198";
		socialUser0.setFacebookUserId(facebookUserId0);
		socialUser1 = new SocialUser();
		socialUser1.setUserAccount(userAccount1);
		facebookUserId1 = "54354538";
		socialUser1.setFacebookUserId(facebookUserId1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		userAccount1 = userDao.makePersistent(userAccount1);
		userAccount0 = userDao.makePersistent(userAccount0);
		userAccount2 = userDao.makePersistent(userAccount2);
		socialUser1 = socialUserDao.makePersistent(socialUser1);
		socialUser0 = socialUserDao.makePersistent(socialUser0);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(socialUser0.getId());
		SocialUser socialUser = socialUserDao.findWithId(socialUser0.getId(), false);
		assertNotSame(socialUser0.hashCode(), 0L);
		assertEquals(socialUser0.hashCode(), socialUser.hashCode());
		assertFalse(socialUser0.toString().equals(""));
		assertNotNull(socialUser.getId());
		assertEquals(socialUser0.getFacebookUserId(), socialUser.getFacebookUserId());
		assertTrue(socialUserDao.isFoundById(socialUser0.getId()));
		socialUserDao.makeTransient(socialUser0);
		assertFalse(socialUserDao.isFoundById(socialUser0.getId()));
	}

	@Test
	public void testFindWithFacebookUserIdAndUser() {
		SocialUser socialUser = socialUserDao.findWithFacebookUserIdAndUser(facebookUserId0, userAccount0);
		assertEquals(socialUser0.getFacebookUserId(), socialUser.getFacebookUserId());
	}

	@Test
	public void testFindWithFacebookUserId() {
		SocialUser socialUser = socialUserDao.findWithFacebookUserId(facebookUserId1);
		assertEquals(socialUser1.getFacebookUserId(), socialUser.getFacebookUserId());
	}

	@Test
	public void testFindWithUser() {
		SocialUser socialUser = socialUserDao.findWithUser(userAccount0);
		assertEquals(socialUser0.getFacebookUserId(), socialUser.getFacebookUserId());
	}

}