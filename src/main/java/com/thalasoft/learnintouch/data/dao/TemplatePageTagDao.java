package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.TemplatePage;
import com.thalasoft.learnintouch.data.dao.domain.TemplatePageTag;

public interface TemplatePageTagDao extends GenericDao<TemplatePageTag, Serializable> {

	public List<TemplatePageTag> findWithPage(TemplatePage templatePage);
	
	public List<TemplatePageTag> findWithPageAndDomTagId(TemplatePage templatePage, String domTagId);
	
}
