package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.TemplateElement;
import com.thalasoft.learnintouch.data.dao.domain.TemplateElementLanguage;

public interface TemplateElementLanguageDao extends GenericDao<TemplateElementLanguage, Serializable> {

	public List<TemplateElementLanguage> findWithElement(TemplateElement templateElement);
	
	public TemplateElementLanguage findWithElementAndLanguageCode(TemplateElement templateElement, String languageCode);
	
	public TemplateElementLanguage findWithElementAndNoLanguageCode(TemplateElement templateElement);
	
}
