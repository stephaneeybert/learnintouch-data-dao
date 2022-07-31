package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.TemplatePage;
import com.thalasoft.learnintouch.data.jpa.domain.TemplatePageTag;

public interface TemplatePageTagRepository extends GenericRepository<TemplatePageTag, Long> {

    @Query("SELECT tpt FROM TemplatePageTag tpt WHERE tpt.templatePage = :templatePage")
    public List<TemplatePageTag> findByPage(@Param("templatePage") TemplatePage templatePage);

    @Query("SELECT tpt FROM TemplatePageTag tpt WHERE tpt.templatePage = :templatePage AND tpt.domTagId = :domTagId")
    public List<TemplatePageTag> findByPageAndDomTagID(@Param("templatePage") TemplatePage templatePage, @Param("domTagId") String domTagId);

}
