package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningResultRange;

public interface ElearningResultRangeDao extends GenericDao<ElearningResultRange, Serializable> {

	public List<ElearningResultRange> findAll();
	
}
