package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.DocumentCategory;
import com.thalasoft.learnintouch.data.jpa.domain.Document;

public interface DocumentRepository extends GenericRepository<Document, Long> {

    @Query("SELECT d FROM Document d ORDER BY d.documentCategory, d.listOrder")
    public Page<Document> findThemAll(Pageable page);

    @Query("SELECT d FROM Document d WHERE LOWER(d.reference) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(d.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(d.filename) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY d.documentCategory, d.listOrder")
    public Page<Document> search(@Param("searchTerm") String searchTerm, Pageable page);

    @Query("SELECT d FROM Document d WHERE d.documentCategory = :documentCategory OR (d.documentCategory IS NULL AND :documentCategory < '1') ORDER BY d.listOrder")
    public Page<Document> findByCategory(@Param("documentCategory") DocumentCategory documentCategory, Pageable page);

    @Query("SELECT d FROM Document d WHERE d.documentCategory = :documentCategory OR (d.documentCategory IS NULL AND :documentCategory < '1') ORDER BY d.id")
    public List<Document> findByCategoryOrderById(@Param("documentCategory") DocumentCategory documentCategory);

    public Page<Document> findByFilename(String filename, Pageable page);
    
    @Query("SELECT d FROM Document d WHERE d.hide != '1'")
    public Page<Document> findByIsPublishedTrue(Pageable page);
    
    @Query("SELECT d FROM Document d WHERE (d.documentCategory = :documentCategory OR (d.documentCategory IS NULL AND :documentCategory < '1')) AND d.listOrder > :listOrder ORDER BY d.listOrder ASC LIMIT 1")
    public Document findByNextListOrder(@Param("documentCategory") DocumentCategory documentCategory, @Param("listOrder") int listOrder);

    @Query("SELECT d FROM Document d WHERE (d.documentCategory = :documentCategory OR (d.documentCategory IS NULL AND :documentCategory < '1')) AND d.listOrder < :listOrder ORDER BY d.listOrder DESC LIMIT 1")
    public Document findByPreviousListOrder(@Param("documentCategory") DocumentCategory documentCategory, @Param("listOrder") int listOrder);

    @Query("SELECT d FROM Document d WHERE (d.documentCategory = :documentCategory OR (d.documentCategory IS NULL AND :documentCategory < '1')) AND d.listOrder = :listOrder ORDER BY d.listOrder DESC")
    public List<Document> findByListOrder(@Param("documentCategory") DocumentCategory documentCategory, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT d1.id) as count FROM Document d1, Document d2 WHERE d1.id != d2.id AND d1.documentCategory.id = d2.documentCategory.id AND d1.listOrder = d2.listOrder AND d1.documentCategory = :documentCategory")
    public Long countDuplicateListOrders(@Param("documentCategory") DocumentCategory documentCategory);

}
