package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.Property;

public interface PropertyDao extends GenericDao<Property, Serializable> {

	public Property findWithName(String name);

}
