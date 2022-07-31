package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.NavmenuDao;
import com.thalasoft.learnintouch.data.dao.NavmenuItemDao;
import com.thalasoft.learnintouch.data.dao.NavmenuLanguageDao;
import com.thalasoft.learnintouch.data.dao.domain.Navmenu;
import com.thalasoft.learnintouch.data.dao.domain.NavmenuItem;
import com.thalasoft.learnintouch.data.dao.domain.NavmenuLanguage;

public class NavmenuLanguageDaoTest extends AbstractDaoTest {

	@Autowired
	private NavmenuDao navmenuDao;

	@Autowired
	private NavmenuLanguageDao navmenuLanguageDao;

	@Autowired
	private NavmenuItemDao navmenuItemDao;

	private Navmenu navmenu0;
	private NavmenuItem navmenuItem0;
	private NavmenuItem navmenuItem1;
	private NavmenuItem navmenuItem2;
	private NavmenuItem navmenuItem3;
	private NavmenuLanguage navmenuLanguage0;
	private NavmenuLanguage navmenuLanguage1;
	private NavmenuLanguage navmenuLanguage2;
	private NavmenuLanguage navmenuLanguage3;

	protected void setNavmenuLanguageDao(NavmenuLanguageDao navmenuLanguageDao) {
		this.navmenuLanguageDao = navmenuLanguageDao;
	}

	protected void setNavmenuItemDao(NavmenuItemDao navmenuItemDao) {
		this.navmenuItemDao = navmenuItemDao;
	}

	public NavmenuLanguageDaoTest() {
		navmenu0 = new Navmenu();
		navmenuItem0 = new NavmenuItem();
		navmenuItem0.setName("item");
		navmenuItem1 = new NavmenuItem();
		navmenuItem1.setName("item");
		navmenuItem2 = new NavmenuItem();
		navmenuItem2.setName("item");
		navmenuItem3 = new NavmenuItem();
		navmenuItem3.setName("item");
		navmenuLanguage0 = new NavmenuLanguage();
		navmenuLanguage0.setLanguageCode("en");
		navmenuLanguage0.setNavmenuItem(navmenuItem0);
		navmenuLanguage0.setNavmenu(navmenu0);
		navmenuLanguage1 = new NavmenuLanguage();
		navmenuLanguage1.setLanguageCode("fr");
		navmenuLanguage1.setNavmenuItem(navmenuItem1);
		navmenuLanguage1.setNavmenu(navmenu0);
		navmenuLanguage2 = new NavmenuLanguage();
		navmenuLanguage2.setLanguageCode("se");
		navmenuLanguage2.setNavmenuItem(navmenuItem2);
		navmenuLanguage2.setNavmenu(navmenu0);
		navmenuLanguage3 = new NavmenuLanguage();
		navmenuLanguage3.setLanguageCode("");
		navmenuLanguage3.setNavmenuItem(navmenuItem3);
		navmenuLanguage3.setNavmenu(navmenu0);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		navmenuItem0 = navmenuItemDao.makePersistent(navmenuItem0);
		navmenuItem1 = navmenuItemDao.makePersistent(navmenuItem1);
		navmenuItem2 = navmenuItemDao.makePersistent(navmenuItem2);
		navmenuItem3 = navmenuItemDao.makePersistent(navmenuItem3);
		navmenu0 = navmenuDao.makePersistent(navmenu0);
		navmenuLanguage0 = navmenuLanguageDao.makePersistent(navmenuLanguage0);
		navmenuLanguage1 = navmenuLanguageDao.makePersistent(navmenuLanguage1);
		navmenuLanguage2 = navmenuLanguageDao.makePersistent(navmenuLanguage2);
		navmenuLanguage3 = navmenuLanguageDao.makePersistent(navmenuLanguage3);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(navmenuLanguage0.getId());
		assertNotSame(navmenuLanguage0.hashCode(), 0L);
		assertFalse(navmenuLanguage0.toString().equals(""));
		NavmenuLanguage navmenuLanguage = navmenuLanguageDao.findWithId(navmenuLanguage0.getId(), false);
		assertEquals(navmenuLanguage0.hashCode(), navmenuLanguage.hashCode());
		assertEquals(navmenuLanguage0.getLanguageCode(), navmenuLanguage.getLanguageCode());
		assertNotNull(navmenuLanguage.getId());
	}

	@Test
	public void testFindWithNavmenu() {
		List<NavmenuLanguage> navmenuLanguages = navmenuLanguageDao.findWithNavmenu(navmenu0);
		assertEquals(4, navmenuLanguages.size());
		assertEquals(navmenuLanguage3.getLanguageCode(), navmenuLanguages.get(0).getLanguageCode());
		assertEquals(navmenuLanguage0.getLanguageCode(), navmenuLanguages.get(1).getLanguageCode());
		assertEquals(navmenuLanguage1.getLanguageCode(), navmenuLanguages.get(2).getLanguageCode());
		assertEquals(navmenuLanguage2.getLanguageCode(), navmenuLanguages.get(3).getLanguageCode());
	}

	@Test
	public void testFindWithNavmenuItem() {
		NavmenuLanguage navmenuLanguage = navmenuLanguageDao.findWithNavmenuItem(navmenuItem0);
		assertEquals(navmenuLanguage0.getLanguageCode(), navmenuLanguage.getLanguageCode());
	}

	@Test
	public void testFindWithNavmenuAndLanguage() {
		NavmenuLanguage navmenuLanguage = navmenuLanguageDao.findWithNavmenuAndLanguage(navmenu0, navmenuLanguage1.getLanguageCode());
		assertEquals(navmenuLanguage1.getLanguageCode(), navmenuLanguage.getLanguageCode());
	}

	@Test
	public void testFindWithNavmenuAndNoLanguage() {
		NavmenuLanguage navmenuLanguage = navmenuLanguageDao.findWithNavmenuAndNoLanguage(navmenu0);
		assertEquals(navmenuLanguage3.getLanguageCode(), navmenuLanguage.getLanguageCode());
		navmenuLanguage3.setLanguageCode("");
		navmenuLanguage = navmenuLanguageDao.findWithNavmenuAndNoLanguage(navmenu0);
		assertEquals(navmenuLanguage3.getLanguageCode(), navmenuLanguage.getLanguageCode());
	}

}
