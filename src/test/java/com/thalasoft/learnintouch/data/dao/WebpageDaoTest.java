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

import com.thalasoft.learnintouch.data.dao.AdminDao;
import com.thalasoft.learnintouch.data.dao.WebpageDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.Webpage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class WebpageDaoTest extends AbstractDaoTest {

	@Autowired
	private WebpageDao webpageDao;
	
	@Autowired
	private AdminDao adminDao;
	
	private Webpage webpage0;
	private Webpage webpage1;
	private Webpage webpage2;
	private Webpage webpage3;
	private Webpage webpage4;
	private Admin admin;
	private String name0 = "english";
	private String name1 = "french";
	private String name2 = "russian";
	private String name3 = "swedish";
	private String name4 = "norwegian";
	private String image = "myimage.png";
	private String content = "my content with an image : " + image;
	
	protected void setWebpageDao(WebpageDao webpageDao) {
		this.webpageDao = webpageDao;
	}

	protected void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public WebpageDaoTest() {
		webpage0 = new Webpage();
		webpage0.setName(name0);
		webpage0.setListOrder(1);
		webpage1 = new Webpage();
		webpage1.setName(name1);
		webpage1.setListOrder(1);
		webpage1.setGarbage(true);
		webpage1.setParent(webpage0);
		webpage2 = new Webpage();
		webpage2.setName(name2);
		webpage2.setListOrder(1);
		webpage2.setGarbage(false);
		webpage2.setParent(webpage1);
		webpage3 = new Webpage();
		webpage3.setName(name3);
		webpage3.setListOrder(2);
		webpage3.setContent(content);
		webpage3.setParent(webpage0);
		webpage4 = new Webpage();
		webpage4.setName(name4);
		webpage4.setListOrder(1);
		webpage4.setContent(content);
		admin = new Admin();
		admin.setLogin("stephane");
		admin.setPassword("toto");
        admin.setPasswordSalt("");
		admin.setEmail("mitt@yahoo.se");
		admin.setFirstname("Stephane");
		admin.setLastname("Eybert");
	}

	@Before
	public void beforeAnyTest() throws Exception {
		webpage0 = webpageDao.makePersistent(webpage0);
		webpage1 = webpageDao.makePersistent(webpage1);
		webpage2 = webpageDao.makePersistent(webpage2);
		webpage3 = webpageDao.makePersistent(webpage3);
		webpage4 = webpageDao.makePersistent(webpage4);
		admin = adminDao.makePersistent(admin);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(webpage0.getId());
		assertNotSame(webpage0.hashCode(), 0L);
		assertFalse(webpage0.toString().equals(""));
		Webpage retrievedWebpage = webpageDao.findWithId(webpage0.getId(), false);
		assertEquals(webpage0.hashCode(), retrievedWebpage.hashCode());
		assertEquals(webpage0.getName(), retrievedWebpage.getName());
		assertNotNull(retrievedWebpage.getId());
	}

	@Test
	public void testFindWithParent() {
		Page<Webpage> page = webpageDao.findWithParent(webpage0, 0, 0);
		List<Webpage> webpages = page.getPageItems();
		assertEquals(2, webpages.size());
		assertEquals(webpage1.getName(), webpages.get(0).getName());
		assertEquals(webpage3.getName(), webpages.get(1).getName());
		page = webpageDao.findWithParent(null, 0, 0);
		webpages = page.getPageItems();
		assertEquals(2, webpages.size());
		assertEquals(webpage0.getName(), webpages.get(0).getName());
		page = webpageDao.findWithParent(webpage2, 0, 0);
		webpages = page.getPageItems();
		assertEquals(0, webpages.size());
	}

	@Test
	public void testFindWithParentOrderById() {
		List<Webpage> webpages = webpageDao.findWithParentOrderById(webpage0);
		assertEquals(2, webpages.size());
		assertEquals(webpage1.getName(), webpages.get(0).getName());
		assertEquals(webpage3.getName(), webpages.get(1).getName());
		webpages = webpageDao.findWithParentOrderById(null);
		assertEquals(2, webpages.size());
		assertEquals(webpage0.getId(), webpages.get(0).getId());
		assertEquals(webpage4.getId(), webpages.get(1).getId());
	}

	@Test
	public void testFindWithParentAndName() {
		Webpage webpage = webpageDao.findWithParentAndName(webpage0, webpage3.getName());
		assertEquals(webpage3.getName(), webpage.getName());
		webpage = webpageDao.findWithParentAndName(null, webpage4.getName());
		assertEquals(name4, webpage.getName());
		webpage = webpageDao.findWithParentAndName(webpage1, webpage2.getName());
		assertEquals(name2, webpage.getName());
		webpage = webpageDao.findWithParentAndName(webpage1, webpage0.getName());
		assertNull(webpage);
	}

	@Test
	public void testFindWithParentAndNameAndNotGarbage() {
		Webpage webpage = webpageDao.findWithParentAndNameAndNotGarbage(webpage0, webpage3.getName());
		assertEquals(webpage3.getName(), webpage.getName());
		webpage = webpageDao.findWithParentAndNameAndNotGarbage(webpage0, webpage1.getName());
		assertNull(webpage);
	}

	@Test
	public void testFindWithListOrder() {
		Webpage webpage = webpageDao.findWithListOrder(webpage0, webpage1.getListOrder());
		assertEquals(webpage1.getListOrder(), webpage.getListOrder());
		webpage = webpageDao.findWithListOrder(null, webpage4.getListOrder());
		assertNotNull(webpage);
		assertEquals(webpage4.getListOrder(), webpage.getListOrder());
	}

	@Test
	public void testFindNextByListOrder() {
		Webpage webpage = webpageDao.findNextWithListOrder(webpage0, webpage1.getListOrder());
		assertEquals(webpage3.getListOrder(), webpage.getListOrder());
		webpage = webpageDao.findNextWithListOrder(webpage0, 0);
		assertEquals(webpage1.getListOrder(), webpage.getListOrder());
		webpage = webpageDao.findNextWithListOrder(null, 0);
		assertEquals(webpage0.getListOrder(), webpage.getListOrder());
		webpage = webpageDao.findNextWithListOrder(null, webpage0.getListOrder());
		assertNull(webpage);
	}

	@Test
	public void testFindPreviousByListOrder() {
		Webpage webpage = webpageDao.findPreviousWithListOrder(webpage0, webpage3.getListOrder());
		assertEquals(webpage1.getListOrder(), webpage.getListOrder());
		webpage = webpageDao.findPreviousWithListOrder(webpage0, webpage3.getListOrder() + 1);
		assertEquals(webpage3.getListOrder(), webpage.getListOrder());
		webpage = webpageDao.findPreviousWithListOrder(null, webpage4.getListOrder() + 1);
		assertEquals(webpage4.getListOrder(), webpage.getListOrder());
		webpage = webpageDao.findPreviousWithListOrder(null, webpage4.getListOrder());
		assertNull(webpage);
	}

	@Test
	public void testFindAllInGarbage() {
		Page<Webpage> page = webpageDao.findAllInGarbage(0, 0);
		List<Webpage> webpages = page.getPageItems();
		assertEquals(1, webpages.size());
		assertEquals(name1, webpages.get(0).getName());
	}

	@Test
	public void testFindAllNotInGarbage() {
		Page<Webpage> page = webpageDao.findAllNotInGarbage(0, 0);
		List<Webpage> webpages = page.getPageItems();
		assertEquals(4, webpages.size());
		assertEquals(name0, webpages.get(0).getName());
	}

	@Test
	public void testFindContentWithImage() {
		webpage3.setContent(content);
		List<Webpage> webpages = webpageDao.findContentWithImage(image);
		assertEquals(2, webpages.size());
		assertEquals(name3, webpages.get(0).getName());
	}
	
	@Test
	public void testCountListOrderDuplicates() {
		assertEquals(0, webpageDao.countListOrderDuplicates(webpage0));
		webpage2.setParent(webpage0);
		assertEquals(2, webpageDao.countListOrderDuplicates(webpage0));
		webpage2.setParent(null);
		assertEquals(0, webpageDao.countListOrderDuplicates(webpage0));
		webpage2.setParent(webpage1);
		assertEquals(2, webpageDao.countListOrderDuplicates(null));
	}
	
	@Test
	public void testFindWithPatternLike() {
		Page<Webpage> page = webpageDao.findWithPatternLike("en", 0, 0);
		List<Webpage> webpages = page.getPageItems();
		assertEquals(3, webpages.size());
		assertEquals(name0, webpages.get(0).getName());
		assertEquals(name4, webpages.get(1).getName());
		assertEquals(name3, webpages.get(2).getName());
		page = webpageDao.findWithPatternLike("uss", 0, 0);
		webpages = page.getPageItems();
		assertEquals(1, webpages.size());
		page = webpageDao.findWithPatternLike("with", 0, 0);
		webpages = page.getPageItems();
		assertEquals(2, webpages.size());
	}

	@Test
	public void testFindAll() {
		Page<Webpage> page = webpageDao.findAll(0, 0);
		List<Webpage> webpages = page.getPageItems();
		assertEquals(5, webpages.size());
		assertEquals(name0, webpages.get(0).getName());
		assertEquals(name1, webpages.get(1).getName());
	}

	@Test
	public void testFindWithAdmin() {
		webpage0.setAdmin(admin);
		webpage1.setAdmin(admin);
		admin = adminDao.makePersistent(admin);
		Page<Webpage> page = webpageDao.findWithAdmin(admin, 0, 0);
		List<Webpage> webpages = page.getPageItems();
		assertEquals(2, webpages.size());
		assertEquals(name0, webpages.get(0).getName());
		assertEquals(name1, webpages.get(1).getName());
	}

}
