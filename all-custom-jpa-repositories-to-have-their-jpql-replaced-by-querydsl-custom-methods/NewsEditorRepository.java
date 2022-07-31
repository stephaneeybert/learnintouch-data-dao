package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.Admin;
import com.thalasoft.learnintouch.data.jpa.domain.NewsEditor;

public interface NewsEditorRepository extends GenericRepository<NewsEditor, Long> {

    @Query("SELECT ne FROM NewsEditor ne WHERE ne.admin = :admin")
    public NewsEditor findByAdmin(@Param("admin") Admin admin);

    @Query("SELECT ne FROM NewsEditor ne, Admin a WHERE ne.admin.id = a.id ORDER BY a.lastname, a.firstname")
    public Page<NewsEditor> findThemAll(Pageable page);

}
