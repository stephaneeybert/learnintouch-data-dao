package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PhotoAlbumFormatDao;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbumFormat;
import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;

@Repository
@Transactional
public class PhotoAlbumFormatHibernateDao extends GenericHibernateDao<PhotoAlbumFormat, Serializable> implements PhotoAlbumFormatDao {

	@Override
	public PhotoAlbumFormat findWithPhotoFormatAndPhotoAlbum(PhotoFormat photoFormat, PhotoAlbum photoAlbum) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("photoFormat", photoFormat));
		criteria.add(Restrictions.eq("photoAlbum", photoAlbum));
		return (PhotoAlbumFormat) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhotoAlbumFormat> findWithPhotoFormat(PhotoFormat photoFormat) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("photoFormat", photoFormat));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhotoAlbumFormat> findWithPhotoAlbum(PhotoAlbum photoAlbum) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("photoAlbum", photoAlbum));
		return criteria.list();
	}

}
