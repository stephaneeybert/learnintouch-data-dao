package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.NavbarDao;
import com.thalasoft.learnintouch.data.dao.NavbarLanguageDao;
import com.thalasoft.learnintouch.data.dao.domain.Navbar;
import com.thalasoft.learnintouch.data.dao.domain.NavbarLanguage;

public class NavbarLanguageDaoTest extends AbstractDaoTest {

	@Autowired
	private NavbarDao navbarDao;

	@Autowired
	private NavbarLanguageDao navbarLanguageDao;

	private Navbar navbar0;
	private NavbarLanguage navbarLanguage0;
	private NavbarLanguage navbarLanguage1;
	private NavbarLanguage navbarLanguage2;
	private NavbarLanguage navbarLanguage3;

	protected void setNavbarLanguageDao(NavbarLanguageDao navbarLanguageDao) {
		this.navbarLanguageDao = navbarLanguageDao;
	}

	public NavbarLanguageDaoTest() {
		navbar0 = new Navbar();
		navbarLanguage0 = new NavbarLanguage();
		navbarLanguage0.setLanguageCode("en");
		navbarLanguage0.setNavbar(navbar0);
		navbarLanguage1 = new NavbarLanguage();
		navbarLanguage1.setLanguageCode("fr");
		navbarLanguage1.setNavbar(navbar0);
		navbarLanguage2 = new NavbarLanguage();
		navbarLanguage2.setLanguageCode("se");
		navbarLanguage2.setNavbar(navbar0);
		navbarLanguage3 = new NavbarLanguage();
		navbarLanguage3.setNavbar(navbar0);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		navbar0 = navbarDao.makePersistent(navbar0);
		navbarLanguage0 = navbarLanguageDao.makePersistent(navbarLanguage0);
		navbarLanguage1 = navbarLanguageDao.makePersistent(navbarLanguage1);
		navbarLanguage2 = navbarLanguageDao.makePersistent(navbarLanguage2);
		navbarLanguage3 = navbarLanguageDao.makePersistent(navbarLanguage3);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(navbarLanguage0.getId());
		assertNotSame(navbarLanguage0.hashCode(), 0L);
		assertFalse(navbarLanguage0.toString().equals(""));
		NavbarLanguage navbarLanguage = navbarLanguageDao.findWithId(navbarLanguage0.getId(), false);
		assertEquals(navbarLanguage0.hashCode(), navbarLanguage.hashCode());
		assertEquals(navbarLanguage0.getLanguageCode(), navbarLanguage.getLanguageCode());
		assertNotNull(navbarLanguage.getId());
	}

	@Test
	public void testFindWithNavbar() {
		List<NavbarLanguage> navbarLanguages = navbarLanguageDao.findWithNavbar(navbar0);
		assertEquals(4, navbarLanguages.size());
		assertEquals(navbarLanguage3.getLanguageCode(), navbarLanguages.get(0).getLanguageCode());
		assertEquals(navbarLanguage0.getLanguageCode(), navbarLanguages.get(1).getLanguageCode());
		assertEquals(navbarLanguage1.getLanguageCode(), navbarLanguages.get(2).getLanguageCode());
		assertEquals(navbarLanguage2.getLanguageCode(), navbarLanguages.get(3).getLanguageCode());
	}

	@Test
	public void testFindWithNavbarAndLanguage() {
		NavbarLanguage navbarLanguage = navbarLanguageDao.findWithNavbarAndLanguage(navbar0, navbarLanguage1.getLanguageCode());
		assertEquals(navbarLanguage1.getLanguageCode(), navbarLanguage.getLanguageCode());
	}

	@Test
	public void testFindWithNavbarAndNoLanguage() {
		NavbarLanguage navbarLanguage = navbarLanguageDao.findWithNavbarAndNoLanguage(navbar0);
		assertEquals(navbarLanguage3.getLanguageCode(), navbarLanguage.getLanguageCode());
		navbarLanguage3.setLanguageCode("");
		navbarLanguage = navbarLanguageDao.findWithNavbarAndNoLanguage(navbar0);
		assertEquals(navbarLanguage3.getLanguageCode(), navbarLanguage.getLanguageCode());
	}

}
