package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningLessonModel;

public interface ElearningLessonModelRepository extends GenericRepository<ElearningLessonModel, Long> {

    @Query("SELECT elm FROM ElearningLessonModel elm ORDER BY elm.name")
    public Page<ElearningLessonModel> findThemAll(Pageable page);

}
