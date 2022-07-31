package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.TemplateElement;
import com.thalasoft.learnintouch.data.dao.domain.TemplateElementTag;

public interface TemplateElementTagDao extends GenericDao<TemplateElementTag, Serializable> {

	public List<TemplateElementTag> findWithElement(TemplateElement templateElement);
	
	public List<TemplateElementTag> findWithElementAndDomTagId(TemplateElement templateElement, String domTagId);
	
}
