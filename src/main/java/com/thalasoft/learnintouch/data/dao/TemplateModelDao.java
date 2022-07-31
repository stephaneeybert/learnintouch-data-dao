package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface TemplateModelDao extends GenericDao<TemplateModel, Serializable> {

	public Page<TemplateModel> findAll(int pageNumber, int pageSize);

	public TemplateModel findWithName(String name);
	
	public List<TemplateModel> findWithParent(TemplateModel parent);

	public List<TemplateModel> findWithNoParent();
	
}
