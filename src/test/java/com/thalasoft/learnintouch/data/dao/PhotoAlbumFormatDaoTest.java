package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.PhotoAlbumDao;
import com.thalasoft.learnintouch.data.dao.PhotoAlbumFormatDao;
import com.thalasoft.learnintouch.data.dao.PhotoFormatDao;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbumFormat;
import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;

public class PhotoAlbumFormatDaoTest extends AbstractDaoTest {

	@Autowired
	private PhotoAlbumDao photoAlbumDao;
	
	@Autowired
	private PhotoFormatDao photoFormatDao;
	
	@Autowired
	private PhotoAlbumFormatDao photoAlbumFormatDao;
	
	private PhotoAlbum photoAlbum0;
	private PhotoAlbum photoAlbum1;
	private PhotoFormat photoFormat0;
	private PhotoFormat photoFormat1;
	private PhotoAlbumFormat photoAlbumFormat0;
	private PhotoAlbumFormat photoAlbumFormat1;
	private PhotoAlbumFormat photoAlbumFormat2;

	protected void setPhotoAlbumDao(PhotoAlbumDao photoAlbumDao) {
		this.photoAlbumDao = photoAlbumDao;
	}

	protected void setPhotoFormatDao(PhotoFormatDao photoFormatDao) {
		this.photoFormatDao = photoFormatDao;
	}

	protected void setPhotoAlbumFormatDao(PhotoAlbumFormatDao photoAlbumFormatDao) {
		this.photoAlbumFormatDao = photoAlbumFormatDao;
	}

	public PhotoAlbumFormatDaoTest() {
		photoAlbum0 = new PhotoAlbum();
		photoAlbum0.setName("Vacances Ete");
		photoAlbum0.setFolderName("VacancesEte");
		photoAlbum0.setListOrder(1);
		photoAlbum1 = new PhotoAlbum();
		photoAlbum1.setName("Ski Hiver");
		photoAlbum1.setFolderName("SkiHiver");
		photoAlbum1.setListOrder(2);
		photoFormat0 = new PhotoFormat();
		photoFormat0.setName("Large");
		photoFormat1 = new PhotoFormat();
		photoFormat1.setName("Extra Large");
		photoAlbumFormat0 = new PhotoAlbumFormat();
		photoAlbumFormat0.setPhotoAlbum(photoAlbum0);
		photoAlbumFormat0.setPhotoFormat(photoFormat0);
		photoAlbumFormat0.setPrice(10);
		photoAlbumFormat1 = new PhotoAlbumFormat();
		photoAlbumFormat1.setPhotoAlbum(photoAlbum0);
		photoAlbumFormat1.setPhotoFormat(photoFormat1);
		photoAlbumFormat0.setPrice(30);
		photoAlbumFormat2 = new PhotoAlbumFormat();
		photoAlbumFormat2.setPhotoAlbum(photoAlbum1);
		photoAlbumFormat2.setPhotoFormat(photoFormat1);
		photoAlbumFormat0.setPrice(20);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		photoAlbum0 = photoAlbumDao.makePersistent(photoAlbum0);
		photoAlbum1 = photoAlbumDao.makePersistent(photoAlbum1);
		photoFormat0 = photoFormatDao.makePersistent(photoFormat0);
		photoFormat1 = photoFormatDao.makePersistent(photoFormat1);
		photoAlbumFormat1 = photoAlbumFormatDao.makePersistent(photoAlbumFormat1);
		photoAlbumFormat0 = photoAlbumFormatDao.makePersistent(photoAlbumFormat0);
		photoAlbumFormat2 = photoAlbumFormatDao.makePersistent(photoAlbumFormat2);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(photoAlbumFormat0.getId());
		assertNotSame(photoAlbumFormat0.hashCode(), 0L);
		assertFalse(photoAlbumFormat0.toString().equals(""));
		PhotoAlbumFormat photoAlbumFormat = photoAlbumFormatDao.findWithId(photoAlbumFormat0.getId(), false);
		assertEquals(photoAlbumFormat0.hashCode(), photoAlbumFormat.hashCode());
		assertEquals(photoAlbumFormat0.getPrice(), photoAlbumFormat.getPrice());
		assertNotNull(photoAlbumFormat.getId());
	}

	@Test
	public void testFindWithPhotoFormatAndPhotoAlbum() {		
		PhotoAlbumFormat photoAlbumFormat = photoAlbumFormatDao.findWithPhotoFormatAndPhotoAlbum(photoFormat0, photoAlbum0);
		assertEquals(photoAlbumFormat0.getId(), photoAlbumFormat.getId());
	}
	
	@Test
	public void testFindWithPhotoFormat() {		
		List<PhotoAlbumFormat> photoAlbumFormats = photoAlbumFormatDao.findWithPhotoFormat(photoFormat0);
		assertEquals(1, photoAlbumFormats.size());
		assertEquals(photoAlbumFormat0.getId(), photoAlbumFormats.get(0).getId());
		photoAlbumFormats = photoAlbumFormatDao.findWithPhotoFormat(photoFormat1);
		assertEquals(2, photoAlbumFormats.size());
	}

	@Test
	public void testFindWithPhotoAlbum() {		
		List<PhotoAlbumFormat> photoAlbumFormats = photoAlbumFormatDao.findWithPhotoAlbum(photoAlbum0);
		assertEquals(2, photoAlbumFormats.size());
		photoAlbumFormats = photoAlbumFormatDao.findWithPhotoAlbum(photoAlbum1);
		assertEquals(1, photoAlbumFormats.size());
	}

}
