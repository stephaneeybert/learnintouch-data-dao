package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.TemplateProperty;
import com.thalasoft.learnintouch.data.dao.domain.TemplatePropertySet;

public interface TemplatePropertyDao extends GenericDao<TemplateProperty, Serializable> {

	public List<TemplateProperty> findWithPropertySet(TemplatePropertySet templatePropertySet);
	
	public List<TemplateProperty> findWithPropertySetAndName(TemplatePropertySet templatePropertySet, String name);
	
	public List<TemplateProperty> findWithValue(String value);
	
}
