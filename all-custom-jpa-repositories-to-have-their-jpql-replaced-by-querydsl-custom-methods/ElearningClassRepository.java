package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.dao.domain.ElearningTeacher;
import com.thalasoft.learnintouch.data.jpa.domain.ElearningClass;

public interface ElearningClassRepository extends GenericRepository<ElearningClass, Long> {

    @Query("SELECT ec FROM ElearningClass ec ORDER BY ec.name")
    public List<ElearningClass> findThemAll();

    @Query("SELECT ec FROM ElearningClass ec, ElearningSubscription es WHERE es.elearningClass.id = ec.id AND es.elearningTeacher = :elearningTeacher ORDER BY ec.name")
    public List<ElearningClass> findBySubscriptionTeacher(@Param("elearningTeacher") ElearningTeacher elearningTeacher);

    @Query("SELECT ec FROM ElearningClass ec WHERE LOWER(ec.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(ec.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY ec.name")
    public Page<ElearningClass> search(@Param("searchTerm") String searchTerm, Pageable page);

}
