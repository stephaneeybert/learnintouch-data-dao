package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercisePage;

public interface ElearningExercisePageDao extends GenericDao<ElearningExercisePage, Serializable> {

	public List<ElearningExercisePage> findWithExercise(ElearningExercise elearningExercise);
	
	public List<ElearningExercisePage> findWithExerciseOrderById(ElearningExercise elearningExercise);
	
	public ElearningExercisePage findWithListOrder(ElearningExercise elearningExercise, int listOrder);
	
	public ElearningExercisePage findNextWithListOrder(ElearningExercise elearningExercise, int listOrder);
	
	public ElearningExercisePage findPreviousWithListOrder(ElearningExercise elearningExercise, int listOrder);
	
	public long countListOrderDuplicates(ElearningExercise elearningExercise);
	
	public List<ElearningExercisePage> findWithImage(String image);
	
	public List<ElearningExercisePage> findWithImageInTextLike(String image);
	
	public List<ElearningExercisePage> findWithAudio(String audio);
	
}
