package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.ElearningClass;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningResult;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubscription;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningResultDao extends GenericDao<ElearningResult, Serializable> {

	public Page<ElearningResult> findWithReleaseDate(LocalDateTime sinceDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningResult> findOldResult(LocalDateTime sinceDateTime, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningResult> findNonSubscription(int pageNumber, int pageSize);
	
	public Page<ElearningResult> findWithUser(UserAccount userAccount, int pageNumber, int pageSize);
	
	public Page<ElearningResult> findWithPatternLike(String searchPattern, int pageNumber, int pageSize);
	
	public Page<ElearningResult> findWithSessionAndCourseAndClassAndExercise(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningClass elearningClass, ElearningExercise elearningExercise, int pageNumber, int pageSize);

	public Page<ElearningResult> findWithSessionAndCourseAndClass(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningClass elearningClass, int pageNumber, int pageSize);
	
	public Page<ElearningResult> findWithSessionAndCourseAndExercise(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningExercise elearningExercise, int pageNumber, int pageSize);
	
	public Page<ElearningResult> findWithSessionAndCourse(ElearningSession elearningSession, ElearningCourse elearningCourse, int pageNumber, int pageSize);
	
	public Page<ElearningResult> findWithSessionAndClass(ElearningSession elearningSession, ElearningClass elearningClass, int pageNumber, int pageSize);
	
	public Page<ElearningResult> findWithSession(ElearningSession elearningSession, int pageNumber, int pageSize);
	
	public Page<ElearningResult> findWithSubscription(ElearningSubscription elearningSubscription, int pageNumber, int pageSize);
	
	public ElearningResult findWithSubscriptionAndExercise(ElearningSubscription elearningSubscription, ElearningExercise elearningExercise);
	
	public List<ElearningResult> findWithExerciseAndEmailAndDate(ElearningExercise elearningExercise, String email, LocalDateTime exerciseDateTime);
	
	public List<ElearningResult> findWithEmail(String email);
	
	public List<ElearningResult> findWithExerciseAndEmail(ElearningExercise elearningExercise, String email);
	
	public List<ElearningResult> findWithExercise(ElearningExercise elearningExercise);
	
}
