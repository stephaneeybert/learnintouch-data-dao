package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.NavbarDao;
import com.thalasoft.learnintouch.data.dao.NavbarItemDao;
import com.thalasoft.learnintouch.data.dao.NavbarLanguageDao;
import com.thalasoft.learnintouch.data.dao.TemplateModelDao;
import com.thalasoft.learnintouch.data.dao.domain.Navbar;
import com.thalasoft.learnintouch.data.dao.domain.NavbarItem;
import com.thalasoft.learnintouch.data.dao.domain.NavbarLanguage;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

public class NavbarItemDaoTest extends AbstractDaoTest {

	@Autowired
	private NavbarDao navbarDao;

	@Autowired
	private NavbarLanguageDao navbarLanguageDao;

	@Autowired
	private NavbarItemDao navbarItemDao;

	@Autowired
	private TemplateModelDao templateModelDao;

	private Navbar navbar0;
	private NavbarLanguage navbarLanguage0;
	private NavbarLanguage navbarLanguage1;
	private NavbarLanguage navbarLanguage2;
	private NavbarLanguage navbarLanguage3;
	private NavbarItem navbarItem0;
	private NavbarItem navbarItem1;
	private NavbarItem navbarItem2;

	protected void setNavbarDao(NavbarDao navbarDao) {
		this.navbarDao = navbarDao;
	}

	protected void setNavbarLanguageDao(NavbarLanguageDao navbarLanguageDao) {
		this.navbarLanguageDao = navbarLanguageDao;
	}

	protected void setNavbarItemDao(NavbarItemDao navbarItemDao) {
		this.navbarItemDao = navbarItemDao;
	}

	public NavbarItemDaoTest() {
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
		navbarItem0 = new NavbarItem();
		navbarItem0.setListOrder(2);
		navbarItem0.setName("Home");
		navbarItem0.setNavbarLanguage(navbarLanguage0);
		navbarItem1 = new NavbarItem();
		navbarItem1.setListOrder(1);
		navbarItem1.setName("Calme");
		navbarItem1.setImage("calme.png");
		navbarItem1.setImageOver("calmeover.png");
		navbarItem1.setNavbarLanguage(navbarLanguage0);
		navbarItem2 = new NavbarItem();
		navbarItem2.setListOrder(3);
		navbarItem2.setName("Conditions");
		navbarItem2.setImage("conditions.png");
		navbarItem2.setImageOver("conditionsover.png");
		navbarItem2.setNavbarLanguage(navbarLanguage0);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		navbar0 = navbarDao.makePersistent(navbar0);
		navbarLanguage0 = navbarLanguageDao.makePersistent(navbarLanguage0);
		navbarLanguage1 = navbarLanguageDao.makePersistent(navbarLanguage1);
		navbarLanguage2 = navbarLanguageDao.makePersistent(navbarLanguage2);
		navbarLanguage3 = navbarLanguageDao.makePersistent(navbarLanguage3);
		navbarItem0 = navbarItemDao.makePersistent(navbarItem0);
		navbarItem1 = navbarItemDao.makePersistent(navbarItem1);
		navbarItem2 = navbarItemDao.makePersistent(navbarItem2);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(navbarItem0.getId());
		assertNotSame(navbarItem0.hashCode(), 0L);
		assertFalse(navbarItem0.toString().equals(""));
		NavbarItem navbarItem = navbarItemDao.findWithId(navbarItem0.getId(), false);
		assertEquals(navbarItem0.hashCode(), navbarItem.hashCode());
		assertEquals(navbarItem0.getName(), navbarItem.getName());
		assertNotNull(navbarItem.getId());
	}

	@Test
	public void testFindWithName() {
		NavbarItem navbarItem = navbarItemDao.findWithName(navbarItem1.getName());
		assertEquals(navbarItem1.getName(), navbarItem.getName());
	}

	@Test
	public void testFindWithImage() {
		List<NavbarItem> navbarItems = navbarItemDao.findWithImage(navbarItem1.getImage());
		assertEquals(1, navbarItems.size());
		assertEquals(navbarItem1.getName(), navbarItems.get(0).getName());
	}

	@Test
	public void testFindWithImageOver() {
		List<NavbarItem> navbarItems = navbarItemDao.findWithImageOver(navbarItem1.getImageOver());
		assertEquals(1, navbarItems.size());
		assertEquals(navbarItem1.getName(), navbarItems.get(0).getName());
	}

	@Test
	public void testFindWithNavbarLanguage() {
		List<NavbarItem> navbarItems = navbarItemDao.findWithNavbarLanguage(navbarLanguage0);
		assertEquals(3, navbarItems.size());
		assertEquals(navbarItem1.getName(), navbarItems.get(0).getName());
		assertEquals(navbarItem0.getName(), navbarItems.get(1).getName());
		assertEquals(navbarItem2.getName(), navbarItems.get(2).getName());
	}

	@Test
	public void testFindWithNavbarLanguageOrderById() {
		NavbarItem navbarItemA = new NavbarItem();
		navbarItemA.setListOrder(2);
		navbarItemA.setName("Test A");
		navbarItemA.setNavbarLanguage(navbarLanguage1);
		NavbarItem navbarItemB = new NavbarItem();
		navbarItemB.setListOrder(1);
		navbarItemB.setName("Test B");
		navbarItemB.setNavbarLanguage(navbarLanguage1);
		navbarItemA = navbarItemDao.makePersistent(navbarItemA);
		navbarItemB = navbarItemDao.makePersistent(navbarItemB);
		List<NavbarItem> navbarItems = navbarItemDao.findWithNavbarLanguageOrderById(navbarLanguage1);
		assertEquals(2, navbarItems.size());
		assertEquals(navbarItemA.getName(), navbarItems.get(0).getName());
		assertEquals(navbarItemB.getName(), navbarItems.get(1).getName());
	}

	@Test
	public void testFindWithListOrder() {
		NavbarItem navbarItem = navbarItemDao.findWithListOrder(navbarLanguage0, 2);
		assertEquals(navbarItem0.getListOrder(), navbarItem.getListOrder());
		navbarItem = navbarItemDao.findWithListOrder(null, 4);
		assertNull(navbarItem);
	}

	@Test
	public void testFindNextByListOrder() {
		NavbarItem navbarItem = navbarItemDao.findNextWithListOrder(navbarLanguage0, 1);
		assertEquals(navbarItem0.getListOrder(), navbarItem.getListOrder());
		navbarItem = navbarItemDao.findNextWithListOrder(navbarLanguage0, 0);
		assertEquals(navbarItem1.getListOrder(), navbarItem.getListOrder());
		navbarItem = navbarItemDao.findNextWithListOrder(navbarLanguage0, 3);
		assertNull(navbarItem);
	}

	@Test
	public void testFindPreviousByListOrder() {
		NavbarItem navbarItem = navbarItemDao.findPreviousWithListOrder(navbarLanguage0, 2);
		assertEquals(navbarItem1.getListOrder(), navbarItem.getListOrder());
		navbarItem = navbarItemDao.findPreviousWithListOrder(navbarLanguage0, 3);
		assertEquals(navbarItem0.getListOrder(), navbarItem.getListOrder());
		navbarItem = navbarItemDao.findPreviousWithListOrder(navbarLanguage0, 4);
		assertEquals(navbarItem2.getListOrder(), navbarItem.getListOrder());
		navbarItem = navbarItemDao.findPreviousWithListOrder(navbarLanguage0, 1);
		assertNull(navbarItem);
	}

	@Test
	public void testCountListOrderDuplicates() {
		long count = navbarItemDao.countListOrderDuplicates(navbarLanguage0);
		assertEquals(0, count);
		navbarItem2.setListOrder(2);
		count = navbarItemDao.countListOrderDuplicates(navbarLanguage0);
		assertEquals(2, count);
	}

	@Test
	public void testResetNavigationModelReferences() {
		TemplateModel templateModel0 = new TemplateModel();
		templateModel0.setName("demo");
		templateModel0.setModelType("simple");
		templateModel0 = templateModelDao.makePersistent(templateModel0);
		navbarItem0.setTemplateModel(templateModel0);
		navbarItem2.setTemplateModel(templateModel0);
		assertEquals(2, navbarItemDao.resetNavigationModelReferences(templateModel0));
	}

}
