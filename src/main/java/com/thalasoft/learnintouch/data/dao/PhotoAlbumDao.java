package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface PhotoAlbumDao extends GenericDao<PhotoAlbum, Serializable> {

	public Page<PhotoAlbum> findAll(int pageNumber, int pageSize);

	public List<PhotoAlbum> findAllOrderById();
	
	public PhotoAlbum findWithName(String name);

	public Page<PhotoAlbum> findNotHidden(int pageNumber, int pageSize);
	
	public Page<PhotoAlbum> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public PhotoAlbum findWithListOrder(int listOrder);
	
	public PhotoAlbum findNextWithListOrder(int listOrder);
	
	public PhotoAlbum findPreviousWithListOrder(int listOrder);
	
	public long countListOrderDuplicates();
	
}
