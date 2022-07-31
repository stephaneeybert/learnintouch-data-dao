package com.thalasoft.learnintouch.data.service;

import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;

public interface PhotoAlbumService {

	public PhotoAlbum save(PhotoAlbum photoAlbum);

	public boolean isNotUsedByAnyPhoto(PhotoAlbum photoAlbum);

}
