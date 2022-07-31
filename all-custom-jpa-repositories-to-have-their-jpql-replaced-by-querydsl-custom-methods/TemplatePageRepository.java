package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.TemplateModel;
import com.thalasoft.learnintouch.data.jpa.domain.TemplatePage;

public interface TemplatePageRepository extends GenericRepository<TemplatePage, Long> {

    @Query("SELECT tp FROM TemplatePage tp WHERE tp.templateModel = :templateModel ORDER BY tp.systemPage")
    public List<TemplatePage> findByModel(@Param("templateModel") TemplateModel templateModel);

    @Query("SELECT tp FROM TemplatePage tp WHERE tp.templateModel = :templateModel AND tp.systemPage = :systemPage")
    public TemplatePage findByModelAndSystemPage(@Param("templateModel") TemplateModel templateModel, @Param("systemPage") String systemPage);

}
