package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestion;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSolution;

public interface ElearningSolutionDao extends GenericDao<ElearningSolution, Serializable> {

	public List<ElearningSolution> findWithQuestion(ElearningQuestion elearningQuestion);

	public List<ElearningSolution> findWithAnswer(ElearningAnswer elearningAnswer);
	
	public List<ElearningSolution> findWithQuestionAndAnswer(ElearningQuestion elearningQuestion, ElearningAnswer elearningAnswer);
	
}
