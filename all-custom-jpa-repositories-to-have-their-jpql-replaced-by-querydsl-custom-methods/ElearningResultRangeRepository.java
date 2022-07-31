package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningResultRange;

public interface ElearningResultRangeRepository extends GenericRepository<ElearningResultRange, Long> {

    @Query("SELECT err FROM ElearningResultRange err ORDER BY err.upperRange")
    public List<ElearningResultRange> findThemAll();

}
