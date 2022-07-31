package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.TemplateElement;
import com.thalasoft.learnintouch.data.jpa.domain.TemplateElementTag;

public interface TemplateElementTagRepository extends GenericRepository<TemplateElementTag, Long> {

    @Query("SELECT tet FROM TemplateElementTag tet WHERE tet.templateElement = :templateElement")
    public List<TemplateElementTag> findByElement(@Param("templateElement") TemplateElement templateElement);

    @Query("SELECT tet FROM TemplateElementTag tet WHERE tet.templateElement = :templateElement AND domTagId = :domTagId")
    public List<TemplateElementTag> findByElementAndDomTagID(@Param("templateElement") TemplateElement templateElement, @Param("domTagId") String domTagId);

}
