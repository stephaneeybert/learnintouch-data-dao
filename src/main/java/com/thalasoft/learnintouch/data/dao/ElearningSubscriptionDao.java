package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.ElearningClass;
import com.thalasoft.learnintouch.data.dao.domain.ElearningCourse;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSession;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubscription;
import com.thalasoft.learnintouch.data.dao.domain.ElearningTeacher;
import com.thalasoft.learnintouch.data.dao.domain.UserAccount;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningSubscriptionDao extends GenericDao<ElearningSubscription, Serializable> {

	public Page<ElearningSubscription> findAll(int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithTeacher(ElearningTeacher elearningTeacher, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithSession(ElearningSession elearningSession, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithCourse(ElearningCourse elearningCourse, int pageNumber, int pageSize);

	public Page<ElearningSubscription> findWithClass(ElearningClass elearningClass, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithSessionAndCourse(ElearningSession elearningSession, ElearningCourse elearningCourse, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithSessionAndClass(ElearningSession elearningSession, ElearningClass elearningClass, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithSessionAndCourseAndClass(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningClass elearningClass, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithUser(UserAccount userAccount, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithUserAndTeacher(UserAccount userAccount, ElearningTeacher elearningTeacher, int pageNumber, int pageSize);

	public ElearningSubscription findWithUserAndSubscription(UserAccount userAccount, ElearningSubscription elearningSubscription);
	
	public Page<ElearningSubscription> findWithSessionAndTeacher(ElearningSession elearningSession, ElearningTeacher elearningTeacher, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithSessionAndCourseAndTeacher(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningTeacher elearningTeacher, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithSessionAndClassAndTeacher(ElearningSession elearningSession, ElearningClass elearningClass, ElearningTeacher elearningTeacher, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findWithSessionAndCourseAndClassAndTeacher(ElearningSession elearningSession, ElearningCourse elearningCourse, ElearningClass elearningClass, ElearningTeacher elearningTeacher, int pageNumber, int pageSize);

	public Page<ElearningSubscription> findWithPatternLike(String searchPattern, int pageNumber, int pageSize);

	public Page<ElearningSubscription> findWithPatternLikeAndDistinctUser(String searchPattern, int pageNumber, int pageSize);

	public Page<ElearningSubscription> findUserSubscriptions(UserAccount userAccount, int pageNumber, int pageSize);
	
	public Page<ElearningSubscription> findOpenedUserSubscriptionsWithCourse(UserAccount userAccount, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<ElearningSubscription> findOpenedUserSubscriptions(UserAccount userAccount, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public long countOpenedSubscriptions(LocalDateTime systemDateTime);
	
}
