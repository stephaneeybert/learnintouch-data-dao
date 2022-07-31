package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningScoring;

public interface ElearningScoringRepository extends GenericRepository<ElearningScoring, Long> {

    @Query("SELECT es FROM ElearningScoring es ORDER BY es.name")
    public Page<ElearningScoring> findThemAll(Pageable page);

}
