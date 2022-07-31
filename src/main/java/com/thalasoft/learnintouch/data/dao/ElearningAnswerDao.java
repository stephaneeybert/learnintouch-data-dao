package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningAnswer;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestion;

public interface ElearningAnswerDao extends GenericDao<ElearningAnswer, Serializable> {

	public List<ElearningAnswer> findWithQuestion(ElearningQuestion elearningQuestion);

	public List<ElearningAnswer> findWithQuestionOrderById(ElearningQuestion elearningQuestion);

	public ElearningAnswer findWithQuestionAndAnswer(ElearningQuestion elearningQuestion, String answer);

	public ElearningAnswer findWithListOrder(ElearningQuestion elearningQuestion, int listOrder);
	
	public ElearningAnswer findNextWithListOrder(ElearningQuestion elearningQuestion, int listOrder);
	
	public ElearningAnswer findPreviousWithListOrder(ElearningQuestion elearningQuestion, int listOrder);
	
	public long countListOrderDuplicates(ElearningQuestion elearningQuestion);
	
	public List<ElearningAnswer> findWithImage(String image);
	
	public List<ElearningAnswer> findWithAudio(String audio);
	
}
