package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningMatter;

public interface ElearningMatterRepository extends GenericRepository<ElearningMatter, Long> {

    @Query("SELECT em FROM ElearningMatter em ORDER BY em.name")
    public Page<ElearningMatter> findThemAll(Pageable page);

}
