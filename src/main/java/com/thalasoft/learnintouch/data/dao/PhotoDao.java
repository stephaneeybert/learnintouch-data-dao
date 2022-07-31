package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.Photo;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface PhotoDao extends GenericDao<Photo, Serializable> {

	public Page<Photo> findAll(int pageNumber, int pageSize);
		
	public Page<Photo> findWithReference(String reference, int pageNumber, int pageSize);
	
	public Page<Photo> findWithPhotoAlbum(PhotoAlbum photoAlbum, int pageNumber, int pageSize);
	
	public List<Photo> findWithPhotoAlbumOrderById(PhotoAlbum photoAlbum);
	
	public Page<Photo> findWithPhotoFormat(PhotoFormat photoFormat, int pageNumber, int pageSize);

	public Photo findWithListOrder(PhotoAlbum photoAlbum, int listOrder);
	
	public Photo findNextWithListOrder(PhotoAlbum photoAlbum, int listOrder);
	
	public Photo findPreviousWithListOrder(PhotoAlbum photoAlbum, int listOrder);

	public long countListOrderDuplicates(PhotoAlbum photoAlbum);
	
	public Page<Photo> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<Photo> findWithPhotoAlbumAndImage(String album, String image, int pageNumber, int pageSize);
	
}
