package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;
import com.thalasoft.learnintouch.data.dao.domain.TemplatePage;

public interface TemplatePageDao extends GenericDao<TemplatePage, Serializable> {

	public List<TemplatePage> findWithModel(TemplateModel templateModel);
	
	public TemplatePage findWithModelAndSystemPage(TemplateModel templateModel, String systemPage);
	
}
