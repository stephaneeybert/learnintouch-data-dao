package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningSubject;

public interface ElearningSubjectRepository extends GenericRepository<ElearningSubject, Long> {

    @Query("SELECT es FROM ElearningSubject es ORDER BY es.name")
    public Page<ElearningSubject> findThemAll(Pageable page);

}
