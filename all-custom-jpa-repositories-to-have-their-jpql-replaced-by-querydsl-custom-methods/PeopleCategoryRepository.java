package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.PeopleCategory;

public interface PeopleCategoryRepository extends GenericRepository<PeopleCategory, Long> {

    @Query("SELECT pc FROM PeopleCategory pc ORDER BY pc.listOrder")
    public List<PeopleCategory> findThemAll();

    @Query("SELECT pc FROM PeopleCategory pc ORDER BY pc.id")
    public List<PeopleCategory> findAllOrderById();

    @Query("SELECT pc FROM PeopleCategory pc WHERE pc.listOrder > :listOrder ORDER BY pc.listOrder ASC LIMIT 1")
    public PeopleCategory findByNextListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT pc FROM PeopleCategory pc WHERE pc.listOrder < :listOrder ORDER BY pc.listOrder DESC LIMIT 1")
    public PeopleCategory findByPreviousListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT pc FROM PeopleCategory pc WHERE pc.listOrder = :listOrder ORDER BY pc.listOrder DESC")
    public List<PeopleCategory> findByListOrder(@Param("listOrder") int listOrder);

    @Query("SELECT COUNT(DISTINCT pc1.id) as count FROM PeopleCategory pc1, PeopleCategory pc2 WHERE pc1.id != pc2.id AND pc1.listOrder = pc2.listOrder")
    public Long countDuplicateListOrders();
    
}
