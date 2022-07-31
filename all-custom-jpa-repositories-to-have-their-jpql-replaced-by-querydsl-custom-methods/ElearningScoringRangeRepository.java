package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningScoring;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningScoringRange;

public interface ElearningScoringRangeRepository extends GenericRepository<ElearningScoringRange, Long> {

    @Query("DELETE FROM ElearningScoringRange esr WHERE esr.elearningScoring = :elearningScoring")
    public void deleteByScoring(@Param("elearningScoring") ElearningScoring elearningScoring);

    @Query("SELECT esr FROM ElearningScoringRange esr WHERE esr.elearningScoring = :elearningScoring ORDER BY esr.upperRange")
    public Page<ElearningScoringRange> findByScoring(@Param("elearningScoring") ElearningScoring elearningScoring, Pageable page);

}
