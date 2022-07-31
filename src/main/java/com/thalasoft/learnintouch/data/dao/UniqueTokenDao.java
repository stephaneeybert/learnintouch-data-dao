package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.UniqueToken;

public interface UniqueTokenDao extends GenericDao<UniqueToken, Serializable> {

	UniqueToken findWithNameAndValue(String name, String value);
	
}
