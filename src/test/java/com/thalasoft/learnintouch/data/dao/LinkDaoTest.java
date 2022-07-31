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

import com.thalasoft.learnintouch.data.dao.LinkCategoryDao;
import com.thalasoft.learnintouch.data.dao.LinkDao;
import com.thalasoft.learnintouch.data.dao.domain.Link;
import com.thalasoft.learnintouch.data.dao.domain.LinkCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class LinkDaoTest extends AbstractDaoTest {

	@Autowired
	private LinkDao linkDao;

	@Autowired
	private LinkCategoryDao linkCategoryDao;

	private Link link0;
	private Link link1;
	private Link link2;
	private Link link3;
	private LinkCategory linkCategory0;

	protected void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}

	public LinkDaoTest() {
		linkCategory0 = new LinkCategory();
		linkCategory0.setName("someiages");
		linkCategory0.setListOrder(1);
		
		link0 = new Link();
		link0.setName("Thalasoft");
		link0.setImage("image0.png");
		link0.setListOrder(1);
		linkCategory0.addLink(link0);
		
		link1 = new Link();
		link1.setName("Libe");
		link1.setImage("image1.jpg");
		link1.setListOrder(2);
		linkCategory0.addLink(link1);
		
		link2 = new Link();
		link2.setName("Google");
		link2.setImage("image2.gif");
		link2.setListOrder(3);
		linkCategory0.addLink(link2);

		link3 = new Link();
		link3.setName("LearninTouch");
		link3.setListOrder(4);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		link1 = linkDao.makePersistent(link1);
		link0 = linkDao.makePersistent(link0);
		link2 = linkDao.makePersistent(link2);
		link3 = linkDao.makePersistent(link3);
		
		linkCategory0 = linkCategoryDao.makePersistent(linkCategory0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(link0.getId());
		assertNotSame(link0.hashCode(), 0L);
		assertFalse(link0.toString().equals(""));
		Link retrievedLink = linkDao.findWithId(link0.getId(), true);
		assertEquals(link0.hashCode(), retrievedLink.hashCode());
		assertEquals(link0.getImage(), retrievedLink.getImage());
		assertNotNull(retrievedLink.getId());
	}

	@Test
	public void testFindAll() {
		Page<Link> page = linkDao.findAll(0, 0);
		List<Link> links = page.getPageItems();
		assertEquals(4, links.size());
	}

	@Test
	public void testFindWithCategory() {
		Page<Link> page = linkDao.findWithCategory(linkCategory0, 0, 0);
		List<Link> links = page.getPageItems();
		assertEquals(3, links.size());
		assertEquals(link0.getId(), links.get(0).getId());
		assertEquals(link1.getId(), links.get(1).getId());
		linkCategory0.removeLink(link1);
		page = linkDao.findWithCategory(linkCategory0, 0, 0);
		links = page.getPageItems();
		assertEquals(2, links.size());
		page = linkDao.findWithCategory(null, 0, 0);
		links = page.getPageItems();
		assertEquals(2, links.size());
		assertEquals(link1.getId(), links.get(0).getId());
		assertEquals(link1.getImage(), links.get(0).getImage());
	}

	@Test
	public void testFindWithCategoryOrderById() {
		List<Link> links = linkDao.findWithCategoryOrderById(linkCategory0);
		assertEquals(3, links.size());
		assertEquals(link1.getId(), links.get(0).getId());
		assertEquals(link0.getId(), links.get(1).getId());
		assertEquals(link2.getId(), links.get(2).getId());
		linkCategory0.removeLink(link1);
		linkCategory0.removeLink(link2);
		links = linkDao.findWithCategoryOrderById(null);
		assertEquals(3, links.size());
		assertEquals(link1.getId(), links.get(0).getId());
		assertEquals(link2.getId(), links.get(1).getId());
	}

	@Test
	public void testFindWithListOrder() {
		Link link = linkDao.findWithListOrder(linkCategory0, 2);
		assertEquals(link1.getId(), link.getId());
		link = linkDao.findWithListOrder(null, 2);
		assertNull(link);
	}

	@Test
	public void testFindNextByListOrder() {
		Link link = linkDao.findNextWithListOrder(linkCategory0, 1);
		assertEquals(link1.getId(), link.getId());
		link = linkDao.findNextWithListOrder(linkCategory0, 0);
		assertEquals(link0.getId(), link.getId());
		link = linkDao.findNextWithListOrder(linkCategory0, 2);
		assertEquals(link2.getId(), link.getId());
		linkCategory0.removeLink(link1);
		link = linkDao.findNextWithListOrder(null, 1);
		assertEquals(link1.getId(), link.getId());
		linkCategory0.removeLink(link2);
		link = linkDao.findNextWithListOrder(null, 2);
		assertEquals(link2.getId(), link.getId());
	}

	@Test
	public void testFindPreviousByListOrder() {
		Link link = linkDao.findPreviousWithListOrder(linkCategory0, 2);
		assertEquals(link0.getId(), link.getId());
		link = linkDao.findPreviousWithListOrder(linkCategory0, 3);
		assertEquals(link1.getId(), link.getId());
		link = linkDao.findPreviousWithListOrder(linkCategory0, 4);
		assertEquals(link2.getId(), link.getId());
		link = linkDao.findPreviousWithListOrder(linkCategory0, 1);
		assertNull(link);
		linkCategory0.removeLink(link0);
		link = linkDao.findPreviousWithListOrder(null, 2);
		assertEquals(link0.getId(), link.getId());
		linkCategory0.removeLink(link1);
		link = linkDao.findPreviousWithListOrder(null, 3);
		assertEquals(link1.getId(), link.getId());
	}

	@Test
	public void testFindWithImage() {
		List<Link> links = linkDao.findWithImage(link1.getImage());
		assertEquals(1, links.size());
		assertEquals(link1.getImage(), links.get(0).getImage());
	}
	
	@Test
	public void testCountListOrderDuplicates() {
		long count = linkDao.countListOrderDuplicates(linkCategory0);
		assertEquals(0, count);
		link2.setListOrder(2);
		assertEquals(2, linkDao.countListOrderDuplicates(linkCategory0));
		linkCategory0.removeLink(link1);
		linkCategory0.removeLink(link2);
		assertEquals(2, linkDao.countListOrderDuplicates(null));
	}

}
