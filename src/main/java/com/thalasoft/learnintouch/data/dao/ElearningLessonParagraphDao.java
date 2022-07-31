package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLesson;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonHeading;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLessonParagraph;

public interface ElearningLessonParagraphDao extends GenericDao<ElearningLessonParagraph, Serializable> {

	public List<ElearningLessonParagraph> findWithLesson(ElearningLesson elearninglesson);
	
	public List<ElearningLessonParagraph> findWithExercise(ElearningExercise elearningExercise);
	
	public List<ElearningLessonParagraph> findWithOtherLessonExercise(ElearningLesson elearningLesson, ElearningExercise elearningExercise);
	
	public List<ElearningLessonParagraph> findWithLessonAndExercise(ElearningLesson elearningLesson, ElearningExercise elearningExercise);
	
	public List<ElearningLessonParagraph> findWithLessonHeading(ElearningLessonHeading elearningLessonHeading);

	public List<ElearningLessonParagraph> findWithLessonAndLessonHeading(ElearningLesson elearningLesson, ElearningLessonHeading elearningLessonHeading);
	
	public List<ElearningLessonParagraph> findWithLessonAndLessonHeadingOrderById(ElearningLesson elearningLesson, ElearningLessonHeading elearningLessonHeading);
	
	public List<ElearningLessonParagraph> findWithLessonAndNoLessonHeading(ElearningLesson elearningLesson);
	
	public List<ElearningLessonParagraph> findWithInvalidHeading(ElearningLesson elearningLesson);
	
	public ElearningLessonParagraph findWithListOrder(ElearningLesson elearningLesson, int listOrder);

	public ElearningLessonParagraph findNextWithListOrder(ElearningLesson elearningLesson, int listOrder);

	public ElearningLessonParagraph findPreviousWithListOrder(ElearningLesson elearningLesson, int listOrder);

	public long countListOrderDuplicates(ElearningLesson elearningLesson);
	
}
