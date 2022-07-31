package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.PeopleCategory;
import com.thalasoft.learnintouch.data.jpa.domain.People;

public interface PeopleRepository extends GenericRepository<People, Long> {

    @Query("SELECT p FROM People p ORDER BY p.peopleCategory, p.listOrder")
    public Page<People> findThemAll(Pageable page);

    @Query("SELECT p FROM People p WHERE p.peopleCategory = :peopleCategory OR (p.peopleCategory IS NULL AND :peopleCategory < '1') ORDER BY p.listOrder")
    public Page<People> findByCategory(@Param("peopleCategory") PeopleCategory peopleCategory, Pageable page);
    
    @Query("SELECT p FROM People p WHERE p.peopleCategory = :peopleCategory OR (p.peopleCategory IS NULL AND :peopleCategory < '1') ORDER BY p.id")
    public List<People> findByCategoryOrderById(@Param("peopleCategory") PeopleCategory peopleCategory);
    
    @Query("SELECT p FROM People p WHERE (p.peopleCategory = :peopleCategory OR (p.peopleCategory IS NULL AND :peopleCategory < '1')) AND p.listOrder > :listOrder ORDER BY p.listOrder ASC LIMIT 1")
    public People findByNextListOrder(@Param("peopleCategory") PeopleCategory peopleCategory, @Param("listOrder") int listOrder);
    
    @Query("SELECT p FROM People p WHERE (p.peopleCategory = :peopleCategory OR (p.peopleCategory IS NULL AND :peopleCategory < '1')) AND p.listOrder < :listOrder ORDER BY p.listOrder DESC LIMIT 1")
    public People findByPreviousListOrder(@Param("peopleCategory") PeopleCategory peopleCategory, @Param("listOrder") int listOrder);
    
    @Query("SELECT p FROM People p WHERE (p.peopleCategory = :peopleCategory OR (p.peopleCategory IS NULL AND :peopleCategory < '1')) AND p.listOrder = :listOrder ORDER BY p.listOrder DESC")
    public List<People> findByListOrder(@Param("peopleCategory") PeopleCategory peopleCategory, @Param("listOrder") int listOrder);
    
    @Query("SELECT COUNT(DISTINCT p1.id) as count FROM People p1, People p2 WHERE p1.id != p2.id AND p1.peopleCategory.id = p2.peopleCategory.id AND p1.listOrder = p2.listOrder AND p1.peopleCategory = :peopleCategory")
    public Long countDuplicateListOrders(@Param("peopleCategory") PeopleCategory peopleCategory);
    
    public List<People> findByImage(String image);

    @Query("SELECT p FROM People p WHERE LOWER(p.firstname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.profile) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(p.image) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY p.peopleCategory, p.listOrder")
    public Page<People> search(@Param("searchTerm") String searchTerm, Pageable page);

}
