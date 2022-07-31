package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.TemplateModel;

public interface TemplateModelRepository extends GenericRepository<TemplateModel, Long> {

    @Query("SELECT tm FROM TemplateModel tm ORDER BY tm.name")
    public Page<TemplateModel> findThemAll(Pageable page);

    @Query("SELECT tm FROM TemplateModel tm WHERE tm.name = :name")
    public TemplateModel findByName(@Param("name") String name);

    @Query("SELECT tm FROM TemplateModel tm WHERE tm.parent = :parent")
    public List<TemplateModel> findByParent(@Param("parent") TemplateModel parent);

    @Query("SELECT tm FROM TemplateModel tm WHERE tm.parent IS NULL")
    public List<TemplateModel> findByNoParent();

}
