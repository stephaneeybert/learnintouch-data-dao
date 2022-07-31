package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thalasoft.learnintouch.data.jpa.domain.ElearningTeacher;

public interface ElearningTeacherRepositoryCustom {

    public Page<ElearningTeacher> search(String searchTerm, Pageable page);
    
}
