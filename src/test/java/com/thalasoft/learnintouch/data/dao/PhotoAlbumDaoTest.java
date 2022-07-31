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

import com.thalasoft.learnintouch.data.dao.PhotoAlbumDao;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class PhotoAlbumDaoTest extends AbstractDaoTest {

	@Autowired
	private PhotoAlbumDao photoAlbumDao;

	private PhotoAlbum photoAlbum0;
	private PhotoAlbum photoAlbum1;
	private PhotoAlbum photoAlbum2;
	private PhotoAlbum photoAlbum3;

	protected void setPhotoAlbumDao(PhotoAlbumDao photoAlbumDao) {
		this.photoAlbumDao = photoAlbumDao;
	}

	public PhotoAlbumDaoTest() {
		photoAlbum0 = new PhotoAlbum();
		photoAlbum0.setName("summer");
		photoAlbum0.setFolderName("summer");
		photoAlbum0.setListOrder(2);
		photoAlbum1 = new PhotoAlbum();
		photoAlbum1.setName("automn");
		photoAlbum1.setFolderName("automn");
		photoAlbum1.setListOrder(1);
		photoAlbum2 = new PhotoAlbum();
		photoAlbum2.setName("winter");
		photoAlbum2.setFolderName("winter");
		photoAlbum2.setListOrder(3);
		photoAlbum3 = new PhotoAlbum();
		photoAlbum3.setName("spring");
		photoAlbum3.setFolderName("spring");
		photoAlbum3.setLocation("Austin");
		photoAlbum3.setHide(true);
		photoAlbum3.setListOrder(4);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		photoAlbum1 = photoAlbumDao.makePersistent(photoAlbum1);
		photoAlbum0 = photoAlbumDao.makePersistent(photoAlbum0);
		photoAlbum3 = photoAlbumDao.makePersistent(photoAlbum3);
		photoAlbum2 = photoAlbumDao.makePersistent(photoAlbum2);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(photoAlbum0.getId());
		assertNotSame(photoAlbum0.hashCode(), 0L);
		assertFalse(photoAlbum0.toString().equals(""));
		PhotoAlbum photoAlbum = photoAlbumDao.findWithId(photoAlbum0.getId(), false);
		assertEquals(photoAlbum0.hashCode(), photoAlbum.hashCode());
		assertEquals(photoAlbum0.getName(), photoAlbum.getName());
		assertEquals(photoAlbum0.getEvent(), photoAlbum.getEvent());
		assertEquals(photoAlbum0.getLocation(), photoAlbum.getLocation());
		assertEquals(photoAlbum0.getPrice(), photoAlbum.getPrice());
		assertEquals(photoAlbum0.getPublicationDate(), photoAlbum.getPublicationDate());
		assertEquals(photoAlbum0.getListOrder(), photoAlbum.getListOrder());
		assertNotNull(photoAlbum.getId());
	}
	
	@Test
	public void testFindAll() {		
		Page<PhotoAlbum> page = photoAlbumDao.findAll(0, 0);
		List<PhotoAlbum> photoAlbums = page.getPageItems();
		assertEquals(4, photoAlbums.size());
		assertEquals(photoAlbum1.getListOrder(), photoAlbums.get(0).getListOrder());
		assertEquals(photoAlbum0.getListOrder(), photoAlbums.get(1).getListOrder());
		assertEquals(photoAlbum2.getListOrder(), photoAlbums.get(2).getListOrder());
		assertEquals(photoAlbum3.getListOrder(), photoAlbums.get(3).getListOrder());
	}
	
	@Test
	public void testFindAllOrderById() {		
		List<PhotoAlbum> photoAlbums = photoAlbumDao.findAllOrderById();
		assertEquals(4, photoAlbums.size());
		assertEquals(photoAlbum1.getListOrder(), photoAlbums.get(0).getListOrder());
		assertEquals(photoAlbum0.getListOrder(), photoAlbums.get(1).getListOrder());
		assertEquals(photoAlbum3.getListOrder(), photoAlbums.get(2).getListOrder());
		assertEquals(photoAlbum2.getListOrder(), photoAlbums.get(3).getListOrder());
	}
	
	@Test
	public void testFindNotHidden() {
		Page<PhotoAlbum> page = photoAlbumDao.findNotHidden(0, 0);
		List<PhotoAlbum> photoAlbums = page.getPageItems();
		assertEquals(3, photoAlbums.size());
		assertEquals(photoAlbum1.getListOrder(), photoAlbums.get(0).getListOrder());
		assertEquals(photoAlbum0.getListOrder(), photoAlbums.get(1).getListOrder());
		assertEquals(photoAlbum2.getListOrder(), photoAlbums.get(2).getListOrder());
	}

	@Test
	public void testFindWithName() {
		PhotoAlbum photoAlbum = photoAlbumDao.findWithName(photoAlbum0.getName());
		assertEquals(photoAlbum0.getName(), photoAlbum.getName());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<PhotoAlbum> page = photoAlbumDao.findWithPatternLike("umm", 0, 0);
		List<PhotoAlbum> photoAlbums = page.getPageItems();
		assertEquals(1, photoAlbums.size());
		assertEquals(photoAlbum0.getName(), photoAlbums.get(0).getName());
		page = photoAlbumDao.findWithPatternLike("au", 0, 0);
		photoAlbums = page.getPageItems();
		assertEquals(2, photoAlbums.size());
		assertEquals(photoAlbum1.getName(), photoAlbums.get(0).getName());
		assertEquals(photoAlbum3.getName(), photoAlbums.get(1).getName());
	}

	@Test
	public void testFindWithListOrder() {
		PhotoAlbum photoAlbum = photoAlbumDao.findWithListOrder(2);
		assertEquals(photoAlbum0.getName(), photoAlbum.getName());
		assertEquals(photoAlbum0.getListOrder(), photoAlbum.getListOrder());
		photoAlbum = photoAlbumDao.findWithListOrder(5);
		assertNull(photoAlbum);
		photoAlbum = photoAlbumDao.findWithListOrder(1);
		assertEquals(photoAlbum1.getName(), photoAlbum.getName());
	}

	@Test
	public void testFindNextByListOrder() {
		PhotoAlbum photoAlbum = photoAlbumDao.findNextWithListOrder(0);
		assertEquals(photoAlbum1.getListOrder(), photoAlbum.getListOrder());
		photoAlbum = photoAlbumDao.findNextWithListOrder(1);
		assertEquals(photoAlbum0.getListOrder(), photoAlbum.getListOrder());
		photoAlbum = photoAlbumDao.findNextWithListOrder(3);
		assertEquals(photoAlbum3.getListOrder(), photoAlbum.getListOrder());
		photoAlbum = photoAlbumDao.findNextWithListOrder(4);
		assertNull(photoAlbum);
		photoAlbum = photoAlbumDao.findNextWithListOrder(2);
		assertEquals(photoAlbum2.getListOrder(), photoAlbum.getListOrder());
	}

	@Test
	public void testFindPreviousByListOrder() {
		PhotoAlbum photoAlbum = photoAlbumDao.findPreviousWithListOrder(2);
		assertEquals(photoAlbum1.getListOrder(), photoAlbum.getListOrder());
		photoAlbum = photoAlbumDao.findPreviousWithListOrder(1);
		assertNull(photoAlbum);
		photoAlbum = photoAlbumDao.findPreviousWithListOrder(3);
		assertEquals(photoAlbum0.getListOrder(), photoAlbum.getListOrder());
		photoAlbum = photoAlbumDao.findPreviousWithListOrder(4);
		assertEquals(photoAlbum2.getListOrder(), photoAlbum.getListOrder());
	}

	@Test
	public void testCountListOrderDuplicates() {
		long count = photoAlbumDao.countListOrderDuplicates();
		assertEquals(0, count);
		photoAlbum1.setListOrder(2);
		assertEquals(2, photoAlbumDao.countListOrderDuplicates());
	}

}
