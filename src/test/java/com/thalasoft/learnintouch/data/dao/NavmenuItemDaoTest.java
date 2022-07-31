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

import com.thalasoft.learnintouch.data.dao.NavmenuDao;
import com.thalasoft.learnintouch.data.dao.NavmenuItemDao;
import com.thalasoft.learnintouch.data.dao.NavmenuLanguageDao;
import com.thalasoft.learnintouch.data.dao.TemplateModelDao;
import com.thalasoft.learnintouch.data.dao.domain.Navmenu;
import com.thalasoft.learnintouch.data.dao.domain.NavmenuItem;
import com.thalasoft.learnintouch.data.dao.domain.NavmenuLanguage;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

public class NavmenuItemDaoTest extends AbstractDaoTest {

	@Autowired
	private NavmenuDao navmenuDao;

	@Autowired
	private NavmenuLanguageDao navmenuLanguageDao;

	@Autowired
	private NavmenuItemDao navmenuItemDao;

	@Autowired
	private TemplateModelDao templateModelDao;

	protected void setTemplateModelDao(TemplateModelDao templateModelDao) {
		this.templateModelDao = templateModelDao;
	}

	private Navmenu navmenu0;
	private NavmenuLanguage navmenuLanguage0;
	private NavmenuLanguage navmenuLanguage1;
	private NavmenuItem navmenuItem0;
	private NavmenuItem navmenuItem1;
	private NavmenuItem navmenuItem2;
	private NavmenuItem navmenuItem3;
	private NavmenuItem navmenuItem4;

	protected void setNavmenuDao(NavmenuDao navmenuDao) {
		this.navmenuDao = navmenuDao;
	}

	protected void setNavmenuLanguageDao(NavmenuLanguageDao navmenuLanguageDao) {
		this.navmenuLanguageDao = navmenuLanguageDao;
	}

	protected void setNavmenuItemDao(NavmenuItemDao navmenuItemDao) {
		this.navmenuItemDao = navmenuItemDao;
	}

	public NavmenuItemDaoTest() {
		navmenu0 = new Navmenu();
		navmenuItem0 = new NavmenuItem();
		navmenuLanguage0 = new NavmenuLanguage();
		navmenuLanguage0.setLanguageCode("en");
		navmenuLanguage0.setNavmenuItem(navmenuItem0);
		navmenuLanguage0.setNavmenu(navmenu0);
		navmenuLanguage1 = new NavmenuLanguage();
		navmenuLanguage1.setLanguageCode("fr");
		navmenuLanguage1.setNavmenuItem(navmenuItem0);
		navmenuLanguage1.setNavmenu(navmenu0);
		navmenuItem0.setListOrder(1);
		navmenuItem0.setName("Home");
		navmenuItem1 = new NavmenuItem();
		navmenuItem1.setListOrder(1);
		navmenuItem1.setName("Calme");
		navmenuItem1.setImage("calme.png");
		navmenuItem1.setImageOver("calmeover.png");
		navmenuItem1.setParent(navmenuItem0);
		navmenuItem2 = new NavmenuItem();
		navmenuItem2.setListOrder(3);
		navmenuItem2.setName("Conditions");
		navmenuItem2.setParent(navmenuItem0);
		navmenuItem3 = new NavmenuItem();
		navmenuItem3.setListOrder(2);
		navmenuItem3.setName("Jeu");
		navmenuItem3.setParent(navmenuItem1);
		navmenuItem4 = new NavmenuItem();
		navmenuItem4.setListOrder(1);
		navmenuItem4.setName("Voyage");
		navmenuItem4.setParent(navmenuItem1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		navmenu0 = navmenuDao.makePersistent(navmenu0);
		navmenuItem0 = navmenuItemDao.makePersistent(navmenuItem0);
		navmenuItem1 = navmenuItemDao.makePersistent(navmenuItem1);
		navmenuItem2 = navmenuItemDao.makePersistent(navmenuItem2);
		navmenuItem3 = navmenuItemDao.makePersistent(navmenuItem3);
		navmenuItem4 = navmenuItemDao.makePersistent(navmenuItem4);
		navmenuLanguage0 = navmenuLanguageDao.makePersistent(navmenuLanguage0);
		navmenuLanguage1 = navmenuLanguageDao.makePersistent(navmenuLanguage1);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(navmenuItem0.getId());
		assertNotSame(navmenuItem0.hashCode(), 0L);
		assertFalse(navmenuItem0.toString().equals(""));
		NavmenuItem navmenuItem = navmenuItemDao.findWithId(navmenuItem0.getId(), false);
		assertEquals(navmenuItem0.hashCode(), navmenuItem.hashCode());
		assertEquals(navmenuItem0.getName(), navmenuItem.getName());
		assertNotNull(navmenuItem.getId());
	}

	@Test
	public void testFindWithName() {
		NavmenuItem navmenuItem = navmenuItemDao.findWithName(navmenuItem1.getName());
		assertEquals(navmenuItem1.getName(), navmenuItem.getName());
	}

	@Test
	public void testFindWithImage() {
		List<NavmenuItem> navmenuItems = navmenuItemDao.findWithImage(navmenuItem1.getImage());
		assertEquals(1, navmenuItems.size());
		assertEquals(navmenuItem1.getName(), navmenuItems.get(0).getName());
	}

	@Test
	public void testFindWithImageOver() {
		List<NavmenuItem> navmenuItems = navmenuItemDao.findWithImageOver(navmenuItem1.getImageOver());
		assertEquals(1, navmenuItems.size());
		assertEquals(navmenuItem1.getName(), navmenuItems.get(0).getName());
	}

	@Test
	public void testFindWithParent() {
		List<NavmenuItem> navmenuItems = navmenuItemDao.findWithParent(navmenuItem0);
		assertEquals(2, navmenuItems.size());
		assertEquals(navmenuItem1.getName(), navmenuItems.get(0).getName());
		assertEquals(navmenuItem2.getName(), navmenuItems.get(1).getName());
	}

	@Test
	public void testFindWithParentOrderById() {
		List<NavmenuItem> navmenuItems = navmenuItemDao.findWithParentOrderById(navmenuItem1);
		assertEquals(2, navmenuItems.size());
		assertEquals(navmenuItem3.getName(), navmenuItems.get(0).getName());
		assertEquals(navmenuItem4.getName(), navmenuItems.get(1).getName());
	}

	@Test
	public void testFindWithListOrder() {
		NavmenuItem navmenuItem = navmenuItemDao.findWithListOrder(navmenuItem0, navmenuItem2.getListOrder());
		assertEquals(navmenuItem2.getListOrder(), navmenuItem.getListOrder());
		navmenuItem = navmenuItemDao.findWithListOrder(navmenuItem0, 4);
		assertNull(navmenuItem);
		navmenuItem = navmenuItemDao.findWithListOrder(null, 1);
		assertNull(navmenuItem);
	}

	@Test
	public void testFindNextByListOrder() {
		NavmenuItem navmenuItem = navmenuItemDao.findNextWithListOrder(navmenuItem0, navmenuItem1.getListOrder() - 1);
		assertEquals(navmenuItem1.getListOrder(), navmenuItem.getListOrder());
		navmenuItem = navmenuItemDao.findNextWithListOrder(navmenuItem0, 0);
		assertEquals(navmenuItem1.getListOrder(), navmenuItem.getListOrder());
		navmenuItem = navmenuItemDao.findNextWithListOrder(navmenuItem0, navmenuItem2.getListOrder());
		assertNull(navmenuItem);
	}

	@Test
	public void testFindPreviousByListOrder() {
		NavmenuItem navmenuItem = navmenuItemDao.findPreviousWithListOrder(navmenuItem0, navmenuItem2.getListOrder());
		assertEquals(navmenuItem1.getListOrder(), navmenuItem.getListOrder());
		navmenuItem = navmenuItemDao.findPreviousWithListOrder(navmenuItem0, navmenuItem2.getListOrder() + 1);
		assertEquals(navmenuItem2.getListOrder(), navmenuItem.getListOrder());
		navmenuItem = navmenuItemDao.findPreviousWithListOrder(navmenuItem0, 4);
		assertEquals(navmenuItem2.getListOrder(), navmenuItem.getListOrder());
		navmenuItem = navmenuItemDao.findPreviousWithListOrder(navmenuItem0, navmenuItem1.getListOrder());
		assertNull(navmenuItem);
	}

	@Test
	public void testCountListOrderDuplicates() {
		long count = navmenuItemDao.countListOrderDuplicates(navmenuItem0);
		assertEquals(0, count);
		navmenuItem2.setListOrder(1);
		count = navmenuItemDao.countListOrderDuplicates(navmenuItem0);
		assertEquals(2, count);
		navmenuItem1.setParent(null);
		count = navmenuItemDao.countListOrderDuplicates(null);
		assertEquals(2, count);
	}

	@Test
	public void testResetNavigationModelReferences() {
		TemplateModel templateModel0 = new TemplateModel();
		templateModel0.setName("demo");
		templateModel0.setModelType("simple");
		templateModel0 = templateModelDao.makePersistent(templateModel0);
		navmenuItem0.setTemplateModel(templateModel0);
		navmenuItem2.setTemplateModel(templateModel0);
		assertEquals(2, navmenuItemDao.resetNavigationModelReferences(templateModel0));
	}

}
