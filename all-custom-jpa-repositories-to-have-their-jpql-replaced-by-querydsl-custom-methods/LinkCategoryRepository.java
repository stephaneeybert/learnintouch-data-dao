package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.LinkCategory;

public interface LinkCategoryRepository extends GenericRepository<LinkCategory, Long> {

    @Query("SELECT lc FROM LinkCategory lc ORDER BY lc.listOrder")
    public List<LinkCategory> findThemAll();

    @Query("SELECT lc FROM LinkCategory lc ORDER BY lc.id")
    public List<LinkCategory> findAllOrderById();

    @Query("SELECT lc FROM LinkCategory lc WHERE lc.listOrder > :listOrder ORDER BY lc.listOrder ASC LIMIT 1")
    public LinkCategory findByNextListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT lc FROM LinkCategory lc WHERE lc.listOrder < :listOrder ORDER BY lc.listOrder DESC LIMIT 1")
    public LinkCategory findByPreviousListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT lc FROM LinkCategory lc WHERE lc.listOrder = :listOrder ORDER BY lc.listOrder DESC")
    public List<LinkCategory> findByListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT lc1.id) as count FROM LinkCategory lc1, LinkCategory lc2 WHERE lc1.id != lc2.id AND lc1.listOrder = lc2.listOrder")
    public Long countDuplicateListOrders();
    
}
