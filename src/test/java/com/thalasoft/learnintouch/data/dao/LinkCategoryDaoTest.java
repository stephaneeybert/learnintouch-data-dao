package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.thalasoft.learnintouch.data.dao.LinkCategoryDao;
import com.thalasoft.learnintouch.data.dao.LinkDao;
import com.thalasoft.learnintouch.data.dao.domain.Link;
import com.thalasoft.learnintouch.data.dao.domain.LinkCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class LinkCategoryDaoTest extends AbstractDaoTest {

	private static Logger logger = LoggerFactory.getLogger(LinkCategoryDaoTest.class);

	@Autowired
	private LinkCategoryDao linkCategoryDao;

	@Autowired
	private LinkDao linkDao;

	private LinkCategory linkCategory0;
	private LinkCategory linkCategory1;
	private Link link0;
	private Link link1;
	private Link link2;

	protected void setLinkCategoryDao(LinkCategoryDao linkCategoryDao) {
		this.linkCategoryDao = linkCategoryDao;
	}

	public LinkCategoryDaoTest() {
		linkCategory0 = new LinkCategory();
		linkCategory0.setName("Images");
        linkCategory0.setListOrder(1);
		linkCategory1 = new LinkCategory();
		linkCategory1.setName("Pdf");
        linkCategory1.setListOrder(2);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		linkCategory1 = linkCategoryDao.makePersistent(linkCategory1);
		linkCategory0 = linkCategoryDao.makePersistent(linkCategory0);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(linkCategory0.getId());
		assertNotSame(linkCategory0.hashCode(), 0L);
		assertFalse(linkCategory0.toString().equals(""));
		LinkCategory retrievedLinkCategory = linkCategoryDao.findWithId(linkCategory0.getId(), true);
		assertEquals(linkCategory0.hashCode(), retrievedLinkCategory.hashCode());
		assertEquals(linkCategory0.getName(), retrievedLinkCategory.getName());
		assertNotNull(retrievedLinkCategory.getId());
	}

	@Test
	public void testCollection() {
		link0 = new Link();
		link0.setName("Thalasoft");
		link0.setImage("image0.png");
		link0.setListOrder(3);
		linkCategory0.addLink(link0);
		link2 = new Link();
		link2.setName("Google");
		link2.setImage("image2.gif");
		link2.setListOrder(1);
		linkCategory0.addLink(link2);
		link1 = new Link();
		link1.setName("Libe");
		link1.setImage("image1.jpg");
		link1.setListOrder(2);
		linkCategory0.addLink(link1);
		linkCategoryDao.flush();
		linkCategoryDao.clear();
		LinkCategory linkCategory = linkCategoryDao.findWithId(linkCategory0.getId(), false);
		assertEquals(3, linkCategory.getLinks().size());
		Iterator<Link> links = linkCategory.getLinks().iterator();
		Link link = links.next();
		assertEquals(link2.getName(), link.getName());
		link = links.next();
		assertEquals(link1.getName(), link.getName());
		link = links.next();
		assertEquals(link0.getName(), link.getName());
		linkCategory.removeLink(link);
		linkCategoryDao.flush();
		linkCategoryDao.clear();
		linkCategory = linkCategoryDao.findWithId(linkCategory0.getId(), false);
		assertEquals(2, linkCategory.getLinks().size());
		links = linkCategory.getLinks().iterator();
		link = links.next();
		assertEquals(link2.getName(), link.getName());
		link = links.next();
		assertEquals(link1.getName(), link.getName());
		linkCategory.removeLink(link);
		linkCategoryDao.flush();
		linkCategoryDao.clear();
		linkCategory = linkCategoryDao.findWithId(linkCategory0.getId(), false);
		assertEquals(1, linkCategory.getLinks().size());
		links = linkCategory.getLinks().iterator();
		link = links.next();
		assertEquals(link2.getName(), link.getName());
		linkCategory.removeLink(link);
		linkCategoryDao.flush();
		linkCategoryDao.clear();
		linkCategory = linkCategoryDao.findWithId(linkCategory0.getId(), false);
		assertEquals(0, linkCategory.getLinks().size());
	}

	@Test
	public void testFindAll() {
		Page<LinkCategory> page = linkCategoryDao.findAll(0, 0);
		List<LinkCategory> linkCategories = page.getPageItems();
		assertEquals(2, linkCategories.size());
		assertEquals(linkCategory0.getName(), linkCategories.get(0).getName());
		assertEquals(linkCategory1.getName(), linkCategories.get(1).getName());
	}

	@Test
	public void testDelete() {
		assertEquals(2, linkCategoryDao.countAll());
		linkCategoryDao.makeTransient(linkCategory0);
		assertEquals(1, linkCategoryDao.countAll());
		Link link = new Link();
		link.setName("Google");
		link.setUrl("www.google.com");
		linkCategory1.addLink(link);
		link = linkDao.makePersistent(link);
		try {
			linkCategoryDao.makeTransient(linkCategory1);
			linkCategoryDao.flush();
		} catch (DataAccessException e) {
			fail("The link category was not deleted when it should have been.");
		} finally {
			linkCategoryDao.clear();
		}
		assertEquals(0, linkCategoryDao.countAll());
	}
	
	@Test
	public void testRetrieveFirstPage() {
		Page<LinkCategory> page = linkCategoryDao.findAll(1, 1);
		List<LinkCategory> linkCategories = page.getPageItems();
		assertEquals(1, linkCategories.size());
		assert (page.isFirstPage());
		assertEquals(1, page.getPageSize());
		assertEquals(2, page.getNumberOfPages());
		assertEquals(2, page.getNextPageNumber());
	}

}
