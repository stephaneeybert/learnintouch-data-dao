package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.thalasoft.learnintouch.data.dao.ProfileDao;
import com.thalasoft.learnintouch.data.dao.domain.Profile;

@TransactionConfiguration(defaultRollback = true)
public class ProfileDaoTest extends AbstractDaoTest {

	@Autowired
	private ProfileDao profileDao;

	private Profile profile0;
	private Profile profile1;

	protected void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}

	public ProfileDaoTest() {
		profile0 = new Profile();
		profile0.setName("LENGTH");
		profile0.setValue("10");
		profile1 = new Profile();
		profile1.setName("WIDTH");
		profile1.setValue("20");
	}
	
	@Before
	public void beforeAnyTest() throws Exception {
		profile1 = profileDao.makePersistent(profile1);
		profile0 = profileDao.makePersistent(profile0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(profile0.getId());
		assertNotSame(profile0.hashCode(), 0L);
		assertFalse(profile0.toString().equals(""));
		Profile profile = profileDao.findWithId(profile0.getId(), true);
		assertEquals(profile0.hashCode(), profile.hashCode());
		assertEquals(profile0.getName(), profile.getName());
		assertEquals(profile0.getValue(), profile.getValue());
		assertNotNull(profile.getId());
	}

	@Test
	public void testFindWithName() {
		Profile profile = profileDao.findWithName(profile0.getName());
		assertEquals(profile0.getName(), profile.getName());
	}
	
}