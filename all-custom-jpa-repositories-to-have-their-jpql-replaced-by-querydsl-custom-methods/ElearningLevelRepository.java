package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningLevel;

public interface ElearningLevelRepository extends GenericRepository<ElearningLevel, Long> {

    @Query("SELECT el FROM ElearningLevel el ORDER BY el.name")
    public Page<ElearningLevel> findThemAll(Pageable page);

}
