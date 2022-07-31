package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.thalasoft.learnintouch.data.dao.PreferenceDao;
import com.thalasoft.learnintouch.data.dao.domain.Preference;

@TransactionConfiguration(defaultRollback = true)
public class PreferenceDaoTest extends AbstractDaoTest {

	@Autowired
	private PreferenceDao preferenceDao;

	private Preference preference0;
	private Preference preference1;
	private String name0 = "USER_AUTO_REGISTER";
	private String value0 = "1";
	private int type0 = 1;
	private String value0bis = "0";
	private String name1 = "USER_SECURITY_CODE";
	private String value1 = "0";
	private int type1 = 2;

	protected void setPreferenceDao(PreferenceDao preferenceDao) {
		this.preferenceDao = preferenceDao;
	}

	public PreferenceDaoTest() {
		preference0 = new Preference();
		preference0.setName(name0);
		preference0.setValue(value0);
		preference0.setType(type0);
		preference1 = new Preference();
		preference1.setName(name1);
		preference1.setValue(value1);
		preference1.setType(type1);
	}
	
	@Before
	public void beforeAnyTest() throws Exception {
		preference1 = preferenceDao.makePersistent(preference1);
		preference0 = preferenceDao.makePersistent(preference0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(preference0.getId());
		preference0.setValue(value0bis);
		Preference loadedPreference = preferenceDao.findWithId(preference0.getId(), true);
		assertNotNull(loadedPreference.getId());
		assertEquals(preference0.getValue(), loadedPreference.getValue());
		assertNotSame(preference0.hashCode(), 0L);
		assertEquals(preference0.hashCode(), loadedPreference.hashCode());
        assertFalse(preference0.toString().equals(""));
        assertEquals(preference0.getType(), loadedPreference.getType());
	}

	@Test
	public void testFindWithName() {
		Preference preference = preferenceDao.findWithName(name0);
		assertEquals(name0, preference.getName());
		assertEquals(value0, preference.getValue());
	}
	
}