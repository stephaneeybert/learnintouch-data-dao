package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.StatisticsReferer;

public interface StatisticsRefererRepository extends GenericRepository<StatisticsReferer, Long> {

    @Query("SELECT sr FROM StatisticsReferer sr ORDER BY sr.name")
    public Page<StatisticsReferer> findThemAll(Pageable page);

    @Query("SELECT sr FROM StatisticsReferer sr WHERE sr.name = :name")
    public StatisticsReferer findByName(@Param("name") String name);

    @Query("SELECT sr FROM StatisticsReferer sr WHERE sr.url = :url")
    public StatisticsReferer findByUrl(@Param("url") String url);

}
