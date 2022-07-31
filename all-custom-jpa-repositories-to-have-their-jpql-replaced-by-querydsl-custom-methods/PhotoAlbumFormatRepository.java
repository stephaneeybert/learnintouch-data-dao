package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.jpa.domain.PhotoAlbumFormat;
import com.thalasoft.learnintouch.data.jpa.domain.PhotoFormat;

public interface PhotoAlbumFormatRepository extends GenericRepository<PhotoAlbumFormat, Long> {

    @Query("SELECT paf FROM PhotoAlbumFormat paf WHERE paf.photoFormat = :photoFormat AND paf.photoAlbum = :photoAlbum")
    public PhotoAlbumFormat findByFormatAndAlbum(@Param("photoFormat") PhotoFormat photoFormat, @Param("photoAlbum") PhotoAlbum photoAlbum);
    
    @Query("SELECT paf FROM PhotoAlbumFormat paf WHERE paf.photoFormat = :photoFormat")
    public PhotoAlbumFormat findByFormat(@Param("photoFormat") PhotoFormat photoFormat);
    
    @Query("SELECT paf FROM PhotoAlbumFormat paf WHERE paf.photoAlbum = :photoAlbum")
    public PhotoAlbumFormat findByAlbum(@Param("photoAlbum") PhotoAlbum photoAlbum);
    
}
