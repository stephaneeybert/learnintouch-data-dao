package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.PhotoAlbum;

public interface PhotoAlbumRepository extends GenericRepository<PhotoAlbum, Long> {

    @Query("SELECT pa FROM PhotoAlbum pa ORDER BY pa.listOrder")
    public List<PhotoAlbum> findThemAll();

    @Query("SELECT pa FROM PhotoAlbum pa ORDER BY pa.id")
    public List<PhotoAlbum> findAllOrderById();

    public PhotoAlbum findByName(String name);

    public PhotoAlbum findByFolderName(String folderName);

    @Query("SELECT pa FROM PhotoAlbum pa WHERE LOWER(pa.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(pa.event) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(pa.location) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY pa.listOrder")
    public Page<PhotoAlbum> search(@Param("searchTerm") String searchTerm, Pageable page);

    @Query("SELECT pa FROM PhotoAlbum pa WHERE pa.listOrder > :listOrder ORDER BY pa.listOrder ASC LIMIT 1")
    public PhotoAlbum findByNextListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT pa FROM PhotoAlbum pa WHERE pa.listOrder < :listOrder ORDER BY pa.listOrder DESC LIMIT 1")
    public PhotoAlbum findByPreviousListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT pa FROM PhotoAlbum pa WHERE pa.listOrder = :listOrder ORDER BY pa.listOrder DESC")
    public List<PhotoAlbum> findByListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT pa1.id) as count FROM PhotoAlbum pa1, PhotoAlbum pa2 WHERE pa1.id != pa2.id AND pa1.listOrder = pa2.listOrder")
    public Long countDuplicateListOrders();
    
}
