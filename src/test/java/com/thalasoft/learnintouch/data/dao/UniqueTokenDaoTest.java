package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.UniqueTokenDao;
import com.thalasoft.learnintouch.data.dao.domain.UniqueToken;

public class UniqueTokenDaoTest extends AbstractDaoTest {

	@Autowired
	private UniqueTokenDao uniqueTokenDao;

	private UniqueToken uniqueToken0;
	private UniqueToken uniqueToken1;

	protected void setUniqueTokenDao(UniqueTokenDao uniqueTokenDao) {
		this.uniqueTokenDao = uniqueTokenDao;
	}

	public UniqueTokenDaoTest() {
		uniqueToken0 = new UniqueToken();
		uniqueToken0.setName("user_login");
		uniqueToken0.setValue("2712940504ed");
		uniqueToken0.setCreationDatetime(new LocalDateTime());

		uniqueToken1 = new UniqueToken();
		uniqueToken1.setName("user_email");
		uniqueToken1.setValue("d4d975e2600b");
		uniqueToken1.setCreationDatetime(new LocalDateTime().plusDays(1));
	}

	@Before
	public void beforeAnyTest() throws Exception {
		uniqueToken1 = uniqueTokenDao.makePersistent(uniqueToken1);
		uniqueToken0 = uniqueTokenDao.makePersistent(uniqueToken0);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(uniqueToken0.getId());
		UniqueToken uniqueToken = uniqueTokenDao.findWithId(uniqueToken0.getId(), false);
		assertNotSame(uniqueToken0.hashCode(), 0L);
		assertEquals(uniqueToken0.hashCode(), uniqueToken.hashCode());
		assertFalse(uniqueToken0.toString().equals(""));
		assertNotNull(uniqueToken.getId());
		assertEquals(uniqueToken0.getValue(), uniqueToken.getValue());
		assertTrue(uniqueTokenDao.isFoundById(uniqueToken0.getId()));
		uniqueTokenDao.makeTransient(uniqueToken0);
		assertFalse(uniqueTokenDao.isFoundById(uniqueToken0.getId()));
	}

	@Test
	public void testFindWithNameAndValue() {
		UniqueToken uniqueToken = uniqueTokenDao.findWithNameAndValue("user_login", "2712940504ed");
		assertEquals(uniqueToken0.getName(), uniqueToken.getName());
		uniqueToken = uniqueTokenDao.findWithNameAndValue("user_login", "294050");
		assertNull(uniqueToken);
	}

}