package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.Photo;
import com.thalasoft.learnintouch.data.jpa.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.jpa.domain.PhotoFormat;

public interface PhotoRepository extends GenericRepository<Photo, Long> {

    @Query("SELECT p FROM Photo p ORDER BY p.photoAlbum.id, p.listOrder")
    public Page<Photo> findThemAll(Pageable page);

    @Query("SELECT p FROM Photo p WHERE p.photoAlbum = :photoAlbum OR (p.photoAlbum IS NULL AND :photoAlbum < '1') ORDER BY p.listOrder")
    public Page<Photo> findByAlbum(@Param("photoAlbum") PhotoAlbum photoAlbum, Pageable page);
    
    @Query("SELECT p FROM Photo p WHERE p.photoAlbum = :photoAlbum OR (p.photoAlbum IS NULL AND :photoAlbum < '1') ORDER BY p.id")
    public List<Photo> findByAlbumOrderById(@Param("photoAlbum") PhotoAlbum photoAlbum);
    
    @Query("SELECT p FROM Photo p, PhotoAlbum pa WHERE p.photoAlbum.id = pa.id AND pa.name = :name AND p.image = :image")
    public Page<Photo> findByAlbumAndImage(@Param("name") String name, @Param("image") String image, Pageable page);
    
    @Query("SELECT p FROM Photo p WHERE p.photoFormat = :photoFormat OR (p.photoFormat IS NULL AND :photoFormat < '1') ORDER BY p.photoAlbum.id, p.listOrder")
    public Page<Photo> findByFormat(@Param("photoFormat") PhotoFormat photoFormat, Pageable page);
    
    @Query("SELECT p FROM Photo p WHERE p.reference = :reference ORDER BY p.photoAlbum.id, p.listOrder")
    public Page<Photo> findByReference(@Param("reference") String reference, Pageable page);
    
    @Query("SELECT p FROM Photo p WHERE (p.photoAlbum = :photoAlbum OR (p.photoAlbum IS NULL AND :photoAlbum < '1')) AND p.listOrder > :listOrder ORDER BY p.listOrder ASC LIMIT 1")
    public Photo findByNextListOrder(@Param("photoAlbum") PhotoAlbum photoAlbum, @Param("listOrder") int listOrder);
    
    @Query("SELECT p FROM Photo p WHERE (p.photoAlbum = :photoAlbum OR (p.photoAlbum IS NULL AND :photoAlbum < '1')) AND p.listOrder < :listOrder ORDER BY p.listOrder DESC LIMIT 1")
    public Photo findByPreviousListOrder(@Param("photoAlbum") PhotoAlbum photoAlbum, @Param("listOrder") int listOrder);
    
    @Query("SELECT p FROM Photo p WHERE (p.photoAlbum = :photoAlbum OR (p.photoAlbum IS NULL AND :photoAlbum < '1')) AND p.listOrder = :listOrder ORDER BY p.listOrder DESC")
    public List<Photo> findByListOrder(@Param("photoAlbum") PhotoAlbum photoAlbum, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT p1.id) as count FROM Photo p1, Photo p2 WHERE p1.id != p2.id AND p1.photoAlbum.id = p2.photoAlbum.id AND p1.listOrder = p2.listOrder AND p1.photoAlbum = :photoAlbum")
    public Long countDuplicateListOrders(@Param("photoAlbum") PhotoAlbum photoAlbum);
    
    @Query("SELECT p FROM Photo p WHERE LOWER(p.reference) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.tags) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.textComment) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.price) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.image) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY p.photoAlbum, p.listOrder")
    public Page<Photo> search(@Param("searchTerm") String searchTerm, Pageable page);

}
