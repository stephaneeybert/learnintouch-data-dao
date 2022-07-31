package com.thalasoft.learnintouch.data.service.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PhotoDao;
import com.thalasoft.learnintouch.data.dao.PhotoFormatDao;
import com.thalasoft.learnintouch.data.dao.domain.Photo;
import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.service.PhotoFormatService;

@Transactional
public class PhotoFormatServiceImpl implements PhotoFormatService {

	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private PhotoFormatDao photoFormatDao;

	protected void setPhotoDao(PhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	protected void setPhotoFormatDao(PhotoFormatDao photoFormatDao) {
		this.photoFormatDao = photoFormatDao;
	}

	@Override
	public PhotoFormat save(PhotoFormat photoFormat) {
		return photoFormatDao.makePersistent(photoFormat);
	}

	@Override
	public boolean isNotUsedByAnyPhoto(PhotoFormat photoFormat) {
		Page<Photo> page = photoDao.findWithPhotoFormat(photoFormat, 0, 0);
		List<Photo> photos = page.getPageItems();
		return photos.size() == 0;
	}
	
}
