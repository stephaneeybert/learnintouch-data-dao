package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.PhotoFormatDao;
import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class PhotoFormatDaoTest extends AbstractDaoTest {

	@Autowired
	private PhotoFormatDao photoFormatDao;

	private PhotoFormat photoFormat0;
	private PhotoFormat photoFormat1;

	protected void setPhotoFormatDao(PhotoFormatDao photoFormatDao) {
		this.photoFormatDao = photoFormatDao;
	}

	public PhotoFormatDaoTest() {
		photoFormat0 = new PhotoFormat();
		photoFormat0.setName("xsmall");
		photoFormat0.setDescription("The extra small format");
		photoFormat0.setPrice(110);
		photoFormat1 = new PhotoFormat();
		photoFormat1.setName("small");
	}

	@Before
	public void beforeAnyTest() throws Exception {
		photoFormat0 = photoFormatDao.makePersistent(photoFormat0);
		photoFormat1 = photoFormatDao.makePersistent(photoFormat1);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(photoFormat0.getId());
		assertNotSame(photoFormat0.hashCode(), 0L);
		assertFalse(photoFormat0.toString().equals(""));
		PhotoFormat photoFormat = photoFormatDao.findWithId(photoFormat0.getId(), false);
		assertEquals(photoFormat0.hashCode(), photoFormat.hashCode());
		assertEquals(photoFormat0.getName(), photoFormat.getName());
		assertEquals(photoFormat0.getDescription(), photoFormat.getDescription());
		assertEquals(photoFormat0.getPrice(), photoFormat.getPrice());
		assertNotNull(photoFormat.getId());
	}
	
	@Test
	public void testFindAll() {		
		Page<PhotoFormat> page = photoFormatDao.findAll(0, 0);
		List<PhotoFormat> photoFormats = page.getPageItems();
		assertEquals(2, photoFormats.size());
		assertEquals(photoFormat1.getId(), photoFormats.get(0).getId());
		assertEquals(photoFormat0.getId(), photoFormats.get(1).getId());
	}
	
	@Test
	public void testFindWithName() {
		PhotoFormat photoFormat = photoFormatDao.findWithName(photoFormat0.getName());
		assertEquals(photoFormat0.getName(), photoFormat.getName());
	}

}
