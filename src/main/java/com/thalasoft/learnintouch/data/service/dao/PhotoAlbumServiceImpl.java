package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PhotoAlbumDao;
import com.thalasoft.learnintouch.data.dao.PhotoDao;
import com.thalasoft.learnintouch.data.dao.domain.Photo;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.PhotoAlbumService;

@Transactional
public class PhotoAlbumServiceImpl implements PhotoAlbumService {

	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private PhotoAlbumDao photoAlbumDao;

	protected void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	protected void setPhotoAlbumDao(PhotoAlbumDao photoAlbumDao) {
		this.photoAlbumDao = photoAlbumDao;
	}

	@Override
	public PhotoAlbum save(PhotoAlbum photoAlbum) {
		return photoAlbumDao.makePersistent(photoAlbum);
	}

	@Override
	public boolean isNotUsedByAnyPhoto(PhotoAlbum photoAlbum) {
		Page<Photo> page = photoDao.findWithPhotoAlbum(photoAlbum, 0, 0);
		List<Photo> photos = page.getPageItems();
		return photos.size() == 0;
	}
	
}
