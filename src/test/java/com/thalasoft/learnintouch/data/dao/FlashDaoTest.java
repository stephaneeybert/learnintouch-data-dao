package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.FlashDao;
import com.thalasoft.learnintouch.data.dao.domain.Flash;

public class FlashDaoTest extends AbstractDaoTest {

	@Autowired
	private FlashDao flashDao;

	private Flash flash0;
	private Flash flash1;
	private String file0 = "mydemo0.swf";
	private String file1 = "atest1.swf";
	private String wddx1 = "awddxfile1.xml";
	
	protected void setFlashDao(FlashDao flashDao) {
		this.flashDao = flashDao;
	}

	public FlashDaoTest() {
		flash0 = new Flash();
		flash0.setFilename(file0);
		flash1 = new Flash();
		flash1.setFilename(file1);
		flash1.setWddx(wddx1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		flash1 = flashDao.makePersistent(flash1);
		flash0 = flashDao.makePersistent(flash0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(flash0.getId());
		assertNotSame(flash0.hashCode(), 0L);
		assertFalse(flash0.toString().equals(""));
		Flash retrievedFlash = flashDao.findWithId(flash0.getId(), false);
		assertEquals(flash0.hashCode(), retrievedFlash.hashCode());
		assertEquals(flash0.getFilename(), retrievedFlash.getFilename());
		assertNotNull(retrievedFlash.getId());
	}

	@Test
	public void testFindAll() {
		List<Flash> webpages = flashDao.findAll();
		assertEquals(2, webpages.size());
		assertEquals(file1, webpages.get(0).getFilename());
		assertEquals(file0, webpages.get(1).getFilename());
	}

	@Test
	public void testFindWithFile() {
		List<Flash> webpages = flashDao.findWithFilename(file1);
		assertEquals(1, webpages.size());
		assertEquals(file1, webpages.get(0).getFilename());
	}

	@Test
	public void testFindWithWddx() {
		List<Flash> webpages = flashDao.findWithWddx(wddx1);
		assertEquals(1, webpages.size());
		assertEquals(wddx1, webpages.get(0).getWddx());
	}
	
}
