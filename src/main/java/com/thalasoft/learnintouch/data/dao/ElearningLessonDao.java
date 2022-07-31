package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.ElearningCategory;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLesson;
import com.thalasoft.learnintouch.data.dao.domain.ElearningLevel;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubject;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningLessonDao extends GenericDao<ElearningLesson, Serializable> {

	public Page<ElearningLesson> findAll(int pageNumber, int pageSize);
	
	public ElearningLesson findWithName(String name);
	
	public Page<ElearningLesson> findAllNotGarbage(int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findAllGarbage(int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithPublicAccessAndReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithPublicAccess(int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithNotPublicAccessAndReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithNotPublicAccess(int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithPatternLike(String searchPattern, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCategoryAndLevelAndSubjectAndReleasedSince(ElearningCategory elearningCategory, ElearningLevel elearningLevel, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithPatternInLessonAndCourseLike(String searchPattern, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCourseAndReleasedSince(ElearningCourse elearningCourse, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCourse(ElearningCourse elearningCourse, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCategoryAndLevelAndSubject(ElearningCategory elearningCategory, ElearningLevel elearningLevel, ElearningSubject elearningSubject, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithLevelAndSubjectAndReleasedSince(ElearningLevel elearningLevel, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithReleasedSince(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCategoryAndReleasedSince(ElearningCategory elearningCategory, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCategory(ElearningCategory elearningCategory, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithLevelAndReleasedSince(ElearningLevel elearningLevel, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithLevel(ElearningLevel elearningLevel, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithSubjectAndReleasedSince(ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithSubject(ElearningSubject elearningSubject, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCategoryAndLevelAndReleasedSince(ElearningCategory elearningCategory, ElearningLevel elearningLevel, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCategoryAndLevel(ElearningCategory elearningCategory, ElearningLevel elearningLevel, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCategoryAndSubjectAndReleasedSince(ElearningCategory elearningCategory, ElearningSubject elearningSubject, LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithCategoryAndSubject(ElearningCategory elearningCategory, ElearningSubject elearningSubject, int pageNumber, int pageSize);
	
	public Page<ElearningLesson> findWithLevelAndSubject(ElearningLevel elearningLevel, ElearningSubject elearningSubject, int pageNumber, int pageSize);

	public Page<ElearningLesson> findWithImageInTextLike(String image, int pageNumber, int pageSize);
	
	public List<ElearningLesson> findWithImage(String image);
	
	public List<ElearningLesson> findWithAudio(String audio);
	
}
