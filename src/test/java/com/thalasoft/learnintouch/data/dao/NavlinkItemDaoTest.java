package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.NavlinkDao;
import com.thalasoft.learnintouch.data.dao.NavlinkItemDao;
import com.thalasoft.learnintouch.data.dao.TemplateModelDao;
import com.thalasoft.learnintouch.data.dao.domain.Navlink;
import com.thalasoft.learnintouch.data.dao.domain.NavlinkItem;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

public class NavlinkItemDaoTest extends AbstractDaoTest {

	@Autowired
	private NavlinkItemDao navlinkItemDao;

	@Autowired
	private NavlinkDao navlinkDao;

	@Autowired
	private TemplateModelDao templateModelDao;

	private Navlink navlink0;
	private NavlinkItem navlinkItem0;
	private NavlinkItem navlinkItem1;
	private NavlinkItem navlinkItem2;
	private NavlinkItem navlinkItem3;

	protected void setNavlinkItemDao(NavlinkItemDao navlinkItemDao) {
		this.navlinkItemDao = navlinkItemDao;
	}

	protected void setTemplateModelDao(TemplateModelDao templateModelDao) {
		this.templateModelDao = templateModelDao;
	}

	public NavlinkItemDaoTest() {
		navlink0 = new Navlink();
		navlinkItem0 = new NavlinkItem();
		navlinkItem0.setLanguageCode("en");
		navlinkItem0.setName("Home");
		navlinkItem0.setNavlink(navlink0);
		navlinkItem2 = new NavlinkItem();
		navlinkItem2.setLanguageCode("se");
		navlinkItem2.setName("Vilkor");
		navlinkItem2.setImage("vilkor.png");
		navlinkItem2.setImageOver("vilkorover.png");
		navlinkItem2.setNavlink(navlink0);
		navlinkItem3 = new NavlinkItem();
		navlinkItem3.setName("Calme");
		navlinkItem3.setNavlink(navlink0);
		navlinkItem1 = new NavlinkItem();
		navlinkItem1.setLanguageCode("fr");
		navlinkItem1.setName("Calme");
		navlinkItem1.setImage("calme.png");
		navlinkItem1.setImageOver("calmeover.png");
		navlinkItem1.setNavlink(navlink0);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		navlink0 = navlinkDao.makePersistent(navlink0);
		navlinkItem0 = navlinkItemDao.makePersistent(navlinkItem0);
		navlinkItem1 = navlinkItemDao.makePersistent(navlinkItem1);
		navlinkItem2 = navlinkItemDao.makePersistent(navlinkItem2);
		navlinkItem3 = navlinkItemDao.makePersistent(navlinkItem3);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(navlinkItem0.getId());
		assertNotSame(navlinkItem0.hashCode(), 0L);
		assertFalse(navlinkItem0.toString().equals(""));
		NavlinkItem navlinkItem = navlinkItemDao.findWithId(navlinkItem0.getId(), false);
		assertEquals(navlinkItem0.hashCode(), navlinkItem.hashCode());
		assertEquals(navlinkItem0.getName(), navlinkItem.getName());
		assertNotNull(navlinkItem.getId());
	}

	@Test
	public void testFindWithNavlink() {
		List<NavlinkItem> navlinkItems = navlinkItemDao.findWithNavlink(navlink0);
		assertEquals(4, navlinkItems.size());
		assertEquals(navlinkItem3.getName(), navlinkItems.get(0).getName());
		assertEquals(navlinkItem0.getName(), navlinkItems.get(1).getName());
		assertEquals(navlinkItem1.getName(), navlinkItems.get(2).getName());
		assertEquals(navlinkItem2.getName(), navlinkItems.get(3).getName());
	}

	@Test
	public void testFindWithNavlinkAndLanguage() {
		NavlinkItem navlinkItem = navlinkItemDao.findWithNavlinkAndLanguage(navlink0, navlinkItem1.getLanguageCode());
		assertEquals(navlinkItem1.getLanguageCode(), navlinkItem.getLanguageCode());
	}

	@Test
	public void testFindWithNavlinkAndNoLanguage() {
		NavlinkItem navlinkItem = navlinkItemDao.findWithNavlinkAndNoLanguage(navlink0);
		assertEquals(navlinkItem3.getLanguageCode(), navlinkItem.getLanguageCode());
		navlinkItem3.setLanguageCode("");
		navlinkItem = navlinkItemDao.findWithNavlinkAndNoLanguage(navlink0);
		assertEquals(navlinkItem3.getLanguageCode(), navlinkItem.getLanguageCode());
	}

	@Test
	public void testFindWithImage() {
		List<NavlinkItem> navlinkItems = navlinkItemDao.findWithImage(navlinkItem1.getImage());
		assertEquals(1, navlinkItems.size());
		assertEquals(navlinkItem1.getImage(), navlinkItems.get(0).getImage());
	}

	@Test
	public void testFindWithImageOver() {
		List<NavlinkItem> navlinkItems = navlinkItemDao.findWithImageOver(navlinkItem1.getImageOver());
		assertEquals(1, navlinkItems.size());
		assertEquals(navlinkItem1.getImageOver(), navlinkItems.get(0).getImageOver());
	}

	@Test
	public void testResetNavigationModelReferences() {
		TemplateModel templateModel0 = new TemplateModel();
		templateModel0.setName("demo");
		templateModel0.setModelType("simple");
		templateModel0 = templateModelDao.makePersistent(templateModel0);
		navlinkItem0.setTemplateModel(templateModel0);
		navlinkItem2.setTemplateModel(templateModel0);
		assertEquals(2, navlinkItemDao.resetNavigationModelReferences(templateModel0));
	}

}
