package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.LinkCategory;
import com.thalasoft.learnintouch.data.jpa.domain.Link;

public interface LinkRepository extends GenericRepository<Link, Long> {

    @Query("SELECT l FROM Link l ORDER BY l.linkCategory, l.listOrder")
    public Page<Link> findThemAll(Pageable page);

    @Query("SELECT l FROM Link l WHERE l.linkCategory = :linkCategory OR (l.linkCategory IS NULL AND :linkCategory < '1') ORDER BY l.listOrder")
    public Page<Link> findByCategory(@Param("linkCategory") LinkCategory linkCategory, Pageable page);
    
    @Query("SELECT l FROM Link l WHERE l.linkCategory = :linkCategory OR (l.linkCategory IS NULL AND :linkCategory < '1') ORDER BY l.id")
    public List<Link> findByCategoryOrderById(@Param("linkCategory") LinkCategory linkCategory);
    
    @Query("SELECT l FROM Link l WHERE (l.linkCategory = :linkCategory OR (l.linkCategory IS NULL AND :linkCategory < '1')) AND l.listOrder > :listOrder ORDER BY l.listOrder ASC LIMIT 1")
    public Link findByNextListOrder(@Param("linkCategory") LinkCategory linkCategory, @Param("listOrder") int listOrder);
    
    @Query("SELECT l FROM Link l WHERE (l.linkCategory = :linkCategory OR (l.linkCategory IS NULL AND :linkCategory < '1')) AND l.listOrder < :listOrder ORDER BY l.listOrder DESC LIMIT 1")
    public Link findByPreviousListOrder(@Param("linkCategory") LinkCategory linkCategory, @Param("listOrder") int listOrder);
    
    @Query("SELECT l FROM Link l WHERE (l.linkCategory = :linkCategory OR (l.linkCategory IS NULL AND :linkCategory < '1')) AND l.listOrder = :listOrder ORDER BY l.listOrder DESC")
    public List<Link> findByListOrder(@Param("linkCategory") LinkCategory linkCategory, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT l1.id) as count FROM Link l1, Link l2 WHERE l1.id != l2.id AND l1.linkCategory.id = l2.linkCategory.id AND l1.listOrder = l2.listOrder AND l1.linkCategory = :linkCategory")
    public Long countDuplicateListOrders(@Param("linkCategory") LinkCategory linkCategory);
    
    public List<Link> findByImage(String image);

    @Query("SELECT l FROM Link l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(l.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(l.url) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY l.linkCategory, l.listOrder")
    public Page<Link> search(@Param("searchTerm") String searchTerm, Pageable page);

}
