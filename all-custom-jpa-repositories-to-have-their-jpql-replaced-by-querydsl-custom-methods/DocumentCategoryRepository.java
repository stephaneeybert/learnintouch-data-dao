package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.DocumentCategory;

public interface DocumentCategoryRepository extends GenericRepository<DocumentCategory, Long> {

    @Query("SELECT dc FROM DocumentCategory dc ORDER BY dc.listOrder")
    public List<DocumentCategory> findThemAll();

    @Query("SELECT dc FROM DocumentCategory dc ORDER BY dc.id")
    public List<DocumentCategory> findAllOrderById();

    @Query("SELECT dc FROM DocumentCategory dc WHERE dc.listOrder > :listOrder ORDER BY dc.listOrder ASC LIMIT 1")
    public DocumentCategory findByNextListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT dc FROM DocumentCategory dc WHERE dc.listOrder < :listOrder ORDER BY dc.listOrder DESC LIMIT 1")
    public DocumentCategory findByPreviousListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT dc FROM DocumentCategory dc WHERE dc.listOrder = :listOrder ORDER BY dc.listOrder DESC")
    public List<DocumentCategory> findByListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT dc1.id) as count FROM DocumentCategory dc1, DocumentCategory dc2 WHERE dc1.id != dc2.id AND dc1.listOrder = dc2.listOrder")
    public Long countDuplicateListOrders();
    
}
