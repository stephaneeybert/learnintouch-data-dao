package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.SmsList;

public interface SmsListRepository extends GenericRepository<SmsList, Long> {

    @Query("SELECT sl FROM SmsList sl ORDER BY sl.name")
    public Page<SmsList> findThemAll(Pageable page);

    @Query("SELECT sl FROM SmsList sl WHERE sl.autoSubscribe = '1' ORDER BY sl.name")
    public Page<SmsList> findByAutoSubscribe(Pageable page);

    @Query("SELECT sl FROM SmsList sl WHERE LOWER(sl.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY sl.name")
    public Page<SmsList> search(@Param("searchTerm") String searchTerm, Pageable page);

}
