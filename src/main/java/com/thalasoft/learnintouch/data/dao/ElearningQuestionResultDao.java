package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestion;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestionResult;
import com.thalasoft.learnintouch.data.dao.domain.ElearningResult;

public interface ElearningQuestionResultDao extends GenericDao<ElearningQuestionResult, Serializable> {

	public ElearningQuestionResult findWithResultAndQuestionAndAnswer(ElearningResult elearningResult, ElearningQuestion elearningQuestion, ElearningAnswer elearningAnswer);

	public List<ElearningQuestionResult> findWithResult(ElearningResult elearningResult);
	
	public List<ElearningQuestionResult> findWithResultAndQuestion(ElearningResult elearningResult, ElearningQuestion elearningQuestion);
	
	public List<ElearningQuestionResult> findWithQuestion(ElearningQuestion elearningQuestion);

	public List<ElearningQuestionResult> findWithQuestionAndAnswer(ElearningQuestion elearningQuestion, ElearningAnswer elearningAnswer);
	
	public List<ElearningQuestionResult> findWithAnswer(ElearningAnswer elearningAnswer);
	
}
