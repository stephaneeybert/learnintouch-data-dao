package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningScoring;
import com.thalasoft.learnintouch.data.dao.domain.ElearningScoringRange;

public interface ElearningScoringRangeDao extends GenericDao<ElearningScoringRange, Serializable> {

	public long deleteWithScoring(ElearningScoring elearningScoring);
	
	public List<ElearningScoringRange> findWithScoring(ElearningScoring elearningScoring);
	
}
