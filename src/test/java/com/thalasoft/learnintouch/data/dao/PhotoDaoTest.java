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
import com.thalasoft.learnintouch.data.dao.PhotoDao;
import com.thalasoft.learnintouch.data.dao.PhotoFormatDao;
import com.thalasoft.learnintouch.data.dao.domain.Photo;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class PhotoDaoTest extends AbstractDaoTest {

	@Autowired
	private PhotoDao photoDao;

	@Autowired
	private PhotoAlbumDao photoAlbumDao;

	@Autowired
	private PhotoFormatDao photoFormatDao;

	private PhotoAlbum photoAlbum0;
	private PhotoAlbum photoAlbum1;
	private PhotoFormat photoFormat0;
	private PhotoFormat photoFormat1;
	private Photo photo0;
	private Photo photo1;
	private Photo photo2;
	private Photo photo3;

	protected void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	protected void setPhotoAlbumDao(PhotoAlbumDao photoAlbumDao) {
		this.photoAlbumDao = photoAlbumDao;
	}

	protected void setPhotoFormatDao(PhotoFormatDao photoFormatDao) {
		this.photoFormatDao = photoFormatDao;
	}

	public PhotoDaoTest() {
		photoAlbum0 = new PhotoAlbum();
		photoAlbum0.setName("summer");
		photoAlbum0.setFolderName("summer");
		photoAlbum1 = new PhotoAlbum();
		photoAlbum1.setName("winter");
		photoAlbum1.setFolderName("winter");
		photoFormat0 = new PhotoFormat();
		photoFormat0.setName("large");
		photoFormat1 = new PhotoFormat();
		photoFormat1.setName("small");
		photo0 = new Photo();
		photo0.setName("Home");
		photo0.setImage("home.png");
		photo0.setListOrder(3);
		photo0.setReference("ref0");
		photo0.setPhotoFormat(photoFormat1);
		photo0.setPhotoAlbum(photoAlbum0);
		photo2 = new Photo();
		photo2.setName("Vilkor");
		photo2.setImage("vilkor.png");
		photo2.setListOrder(2);
		photo2.setPhotoFormat(photoFormat1);
		photo2.setPhotoAlbum(photoAlbum1);
		photo3 = new Photo();
		photo3.setName("Calme");
		photo3.setListOrder(1);
		photo1 = new Photo();
		photo1.setName("Top");
		photo1.setImage("top.png");
		photo1.setListOrder(2);
		photo1.setPhotoFormat(photoFormat0);
		photo1.setPhotoAlbum(photoAlbum0);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		photoAlbum1 = photoAlbumDao.makePersistent(photoAlbum1);
		photoAlbum0 = photoAlbumDao.makePersistent(photoAlbum0);
		photoFormat0 = photoFormatDao.makePersistent(photoFormat0);
		photoFormat1 = photoFormatDao.makePersistent(photoFormat1);
		photo0 = photoDao.makePersistent(photo0);
		photo3 = photoDao.makePersistent(photo3);
		photo2 = photoDao.makePersistent(photo2);
		photo1 = photoDao.makePersistent(photo1);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(photo0.getId());
		assertNotSame(photo0.hashCode(), 0L);
		assertFalse(photo0.toString().equals(""));
		Photo photo = photoDao.findWithId(photo0.getId(), false);
		assertEquals(photo0.hashCode(), photo.hashCode());
		assertEquals(photo0.getName(), photo.getName());
		assertEquals(photo0.getImage(), photo.getImage());
		assertEquals(photo0.getReference(), photo.getReference());
		assertEquals(photo0.getListOrder(), photo.getListOrder());
		assertNotNull(photo.getId());
	}
	
	@Test
	public void testFindAll() {		
		Page<Photo> page = photoDao.findAll(0, 0);
		List<Photo> photos = page.getPageItems();
		assertEquals(4, photos.size());
	}
	
	@Test
	public void testFindWithReference() {
		Page<Photo> page = photoDao.findWithReference(photo0.getReference(), 0, 0);
		List<Photo> photos = page.getPageItems();
		assertEquals(1, photos.size());
		assertEquals(photo0.getName(), photos.get(0).getName());
	}

	@Test
	public void testFindWithPhotoAlbum() {
		Page<Photo> page = photoDao.findWithPhotoAlbum(photoAlbum0, 0, 0);
		List<Photo> photos = page.getPageItems();
		assertEquals(2, photos.size());
		assertEquals(photo1.getName(), photos.get(0).getName());
		assertEquals(photo0.getName(), photos.get(1).getName());
		page = photoDao.findWithPhotoAlbum(null, 0, 0);
		photos = page.getPageItems();
		assertEquals(1, photos.size());
		assertEquals(photo3.getName(), photos.get(0).getName());
	}

	@Test
	public void testFindWithPhotoAlbumOrderById() {
		Page<Photo> page = photoDao.findWithPhotoAlbum(photoAlbum0, 0, 0);
		List<Photo> photos = page.getPageItems();
		assertEquals(2, photos.size());
		assertEquals(photo1.getName(), photos.get(0).getName());
		assertEquals(photo0.getName(), photos.get(1).getName());
		page = photoDao.findWithPhotoAlbum(null, 0, 0);
		photos = page.getPageItems();
		assertEquals(1, photos.size());
		assertEquals(photo3.getName(), photos.get(0).getName());
	}


	@Test
	public void testFindWithPhotoFormat() {
		Page<Photo> page = photoDao.findWithPhotoFormat(photoFormat0, 0, 0);
		List<Photo> photos = page.getPageItems();
		assertEquals(1, photos.size());
		assertEquals(photo1.getName(), photos.get(0).getName());
		page = photoDao.findWithPhotoFormat(photoFormat1, 0, 0);
		photos = page.getPageItems();
		assertEquals(2, photos.size());
		assertEquals(photo2.getName(), photos.get(0).getName());
		assertEquals(photo0.getName(), photos.get(1).getName());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<Photo> page = photoDao.findWithPatternLike("ilkor", 0, 0);
		List<Photo> photos = page.getPageItems();
		assertEquals(1, photos.size());
		assertEquals(photo2.getName(), photos.get(0).getName());
		page = photoDao.findWithPatternLike("me", 0, 0);
		photos = page.getPageItems();
		assertEquals(2, photos.size());
		assertEquals(photo3.getName(), photos.get(0).getName());
		assertEquals(photo0.getName(), photos.get(1).getName());
		page = photoDao.findWithPatternLike("top.p", 0, 0);
		photos = page.getPageItems();
		assertEquals(1, photos.size());
		assertEquals(photo1.getName(), photos.get(0).getName());
	}

	@Test
	public void testFindWithPhotoAlbumAndImage() {
		Page<Photo> page = photoDao.findWithPhotoAlbumAndImage(photoAlbum0.getName(), "top.png", 0, 0);
		List<Photo> photos = page.getPageItems();
		assertEquals(1, photos.size());
		assertEquals(photo1.getName(), photos.get(0).getName());
	}

	@Test
	public void testFindWithListOrder() {
		Photo photo = photoDao.findWithListOrder(photoAlbum0, 2);
		assertEquals(photo1.getName(), photo.getName());
		assertEquals(photo1.getListOrder(), photo.getListOrder());
		photo = photoDao.findWithListOrder(null, 1);
		assertNotNull(photo);
		assertEquals(photo3.getName(), photo.getName());
		assertEquals(photo3.getListOrder(), photo.getListOrder());
		photo = photoDao.findWithListOrder(null, 2);
		assertNull(photo);
	}

	@Test
	public void testFindNextByListOrder() {
		Photo photo = photoDao.findNextWithListOrder(null, 0);
		assertEquals(photo3.getListOrder(), photo.getListOrder());
		photo = photoDao.findNextWithListOrder(null, 1);
		assertNull(photo);
		photo = photoDao.findNextWithListOrder(photoAlbum0, 1);
		assertEquals(photo1.getListOrder(), photo.getListOrder());
		photo = photoDao.findNextWithListOrder(photoAlbum0, 2);
		assertEquals(photo0.getListOrder(), photo.getListOrder());
		photo = photoDao.findNextWithListOrder(photoAlbum1, 1);
		assertEquals(photo2.getListOrder(), photo.getListOrder());
		photo = photoDao.findNextWithListOrder(photoAlbum1, 2);
		assertNull(photo);
	}

	@Test
	public void testFindPreviousByListOrder() {
		Photo photo = photoDao.findPreviousWithListOrder(null, 2);
		assertEquals(photo3.getListOrder(), photo.getListOrder());
		photo = photoDao.findPreviousWithListOrder(null, 1);
		assertNull(photo);
		photo = photoDao.findPreviousWithListOrder(photoAlbum0, 3);
		assertEquals(photo1.getListOrder(), photo.getListOrder());
		photo = photoDao.findPreviousWithListOrder(photoAlbum0, 4);
		assertEquals(photo0.getListOrder(), photo.getListOrder());
		photo = photoDao.findPreviousWithListOrder(photoAlbum1, 3);
		assertEquals(photo2.getListOrder(), photo.getListOrder());
		photo = photoDao.findPreviousWithListOrder(photoAlbum1, 2);
		assertNull(photo);
	}

	@Test
	public void testCountListOrderDuplicates() {
		long count = photoDao.countListOrderDuplicates(photoAlbum0);
		assertEquals(0, count);
		photo0.setListOrder(2);
		assertEquals(2, photoDao.countListOrderDuplicates(photoAlbum0));
		photo0.setPhotoAlbum(null);
		assertEquals(0, photoDao.countListOrderDuplicates(photoAlbum0));
		photo1.setPhotoAlbum(null);
		assertEquals(2, photoDao.countListOrderDuplicates(null));
	}

}
