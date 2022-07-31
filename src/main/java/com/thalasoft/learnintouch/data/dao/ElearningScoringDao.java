package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ElearningScoring;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningScoringDao extends GenericDao<ElearningScoring, Serializable> {

	public Page<ElearningScoring> findAll(int pageNumber, int pageSize);

}
