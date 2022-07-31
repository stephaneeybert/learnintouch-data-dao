package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningCategory;

public interface ElearningCategoryRepository extends GenericRepository<ElearningCategory, Long> {

    @Query("SELECT ec FROM ElearningCategory ec ORDER BY ec.name")
    public List<ElearningCategory> findThemAll();

}
