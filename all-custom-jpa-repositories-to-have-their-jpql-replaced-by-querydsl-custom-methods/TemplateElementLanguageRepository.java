package com.thalasoft.learnintouch.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.TemplateElement;
import com.thalasoft.learnintouch.data.jpa.domain.TemplateElementLanguage;

public interface TemplateElementLanguageRepository extends GenericRepository<TemplateElementLanguage, Long> {

    @Query("SELECT tel FROM TemplateElementLanguage tel WHERE tel.templateElement = :templateElement ORDER BY tel.languageCode")
    public List<TemplateElementLanguage> findByElement(@Param("templateElement") TemplateElement templateElement);

    @Query("SELECT tel FROM TemplateElementLanguage tel WHERE tel.templateElement = :templateElement AND tel.languageCode = :languageCode")
    public TemplateElementLanguage findByElementAndLanguage(@Param("templateElement") TemplateElement templateElement, @Param("languageCode") String languageCode);

    @Query("SELECT tel FROM TemplateElementLanguage tel WHERE tel.templateElement = :templateElement AND tel.languageCode = ''")
    public TemplateElementLanguage findByElementAndNoLanguage(@Param("templateElement") TemplateElement templateElement);

}
