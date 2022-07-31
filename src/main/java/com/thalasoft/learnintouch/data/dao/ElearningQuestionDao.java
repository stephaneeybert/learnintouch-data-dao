package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercisePage;
import com.thalasoft.learnintouch.data.dao.domain.ElearningQuestion;

public interface ElearningQuestionDao extends GenericDao<ElearningQuestion, Serializable> {

	public List<ElearningQuestion> findWithExercisePage(ElearningExercisePage elearningExercisePage);

	public List<ElearningQuestion> findWithExercisePageOrderById(ElearningExercisePage elearningExercisePage);
	
	public List<ElearningQuestion> findWithExercise(ElearningExercise elearningExercise);
	
	public ElearningQuestion findWithListOrder(ElearningExercisePage elearningExercisePage, int listOrder);
	
	public ElearningQuestion findNextWithListOrder(ElearningExercisePage elearningExercisePage, int listOrder);
	
	public ElearningQuestion findPreviousWithListOrder(ElearningExercisePage elearningExercisePage, int listOrder);
	
	public long countListOrderDuplicates(ElearningExercisePage elearningExercisePage);
	
	public List<ElearningQuestion> findWithImage(String image);
	
	public List<ElearningQuestion> findWithAudio(String audio);
	
}
