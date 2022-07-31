package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCategory;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLevel;
import com.thalasoft.learnintouch.data.dao.domain.ElearningScoring;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubject;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningExerciseDao extends GenericDao<ElearningExercise, Serializable> {

	public ElearningExercise findWithName(String name);
	
	public Page<ElearningExercise> findAll(int pageNumber, int pageSize);

	public Page<ElearningExercise> findAllNotGarbage(int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findAllGarbage(int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithPublicAccessAndReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithPublicAccess(int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithNotPublicAccessAndReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithNotPublicAccess(int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCourseAndReleasedSince(ElearningCourse elearningCourse, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithSubjectAndReleasedSince(ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithLevelAndReleasedSince(ElearningLevel elearningLevel, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCategoryAndReleasedSince(ElearningCategory elearningCategory, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCategoryAndLevelAndSubjectAndReleasedSince(ElearningCategory elearningCategory, ElearningLevel elearningLevel, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCategoryAndLevelAndSubject(ElearningCategory elearningCategory, ElearningLevel elearningLevel, ElearningSubject elearningSubject, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCategoryAndLevelAndReleasedSince(ElearningCategory elearningCategory, ElearningLevel elearningLevel, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCategoryAndSubjectAndReleasedSince(ElearningCategory elearningCategory, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithLevelAndSubjectAndReleasedSince(ElearningLevel elearningLevel, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<ElearningExercise> findWithLevelAndSubject(ElearningLevel elearningLevel, ElearningSubject elearningSubject, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCategoryAndSubject(ElearningCategory elearningCategory, ElearningSubject elearningSubject, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCategoryAndLevel(ElearningCategory elearningCategory, ElearningLevel elearningLevel, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithLevel(ElearningLevel elearningLevel, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithSubject(ElearningSubject elearningSubject, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCategory(ElearningCategory elearningCategory, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithScoring(ElearningScoring elearningScoring, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithCourse(ElearningCourse elearningCourse, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithPatternInExerciseAndCourseLike(String searchPattern, int pageNumber, int pageSize);

	public Page<ElearningExercise> findWithPatternLike(String searchPattern, int pageNumber, int pageSize);
	
	public Page<ElearningExercise> findWithImageInIntroductionLike(String image, int pageNumber, int pageSize);
	
	public List<ElearningExercise> findWithImage(String image);
	
	public List<ElearningExercise> findWithAudio(String audio);
	
}
