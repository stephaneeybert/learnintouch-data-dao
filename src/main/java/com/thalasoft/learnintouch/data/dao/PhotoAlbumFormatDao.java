package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbumFormat;
import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;

public interface PhotoAlbumFormatDao extends GenericDao<PhotoAlbumFormat, Serializable> {

	public PhotoAlbumFormat findWithPhotoFormatAndPhotoAlbum(PhotoFormat photoFormat, PhotoAlbum photoAlbum);

	public List<PhotoAlbumFormat> findWithPhotoFormat(PhotoFormat photoFormat);

	public List<PhotoAlbumFormat> findWithPhotoAlbum(PhotoAlbum photoAlbum);

}
