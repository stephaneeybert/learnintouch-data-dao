package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import org.joda.time.LocalDateTime;

import com.thalasoft.learnintouch.data.dao.domain.ElearningAssignment;
import com.thalasoft.learnintouch.data.dao.domain.ElearningClass;
import com.thalasoft.learnintouch.data.dao.domain.ElearningExercise;
import com.thalasoft.learnintouch.data.dao.domain.ElearningResult;
import com.thalasoft.learnintouch.data.dao.domain.ElearningSubscription;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ElearningAssignmentDao extends GenericDao<ElearningAssignment, Serializable> {

	public Page<ElearningAssignment> findWithExercise(ElearningExercise elearningExercise, int pageNumber, int pageSize);
	
	public Page<ElearningAssignment> findWithResult(ElearningResult elearningResult, int pageNumber, int pageSize);
	
	public Page<ElearningAssignment> findWithSubscription(ElearningSubscription elearningSubscription, int pageNumber, int pageSize);

	public ElearningAssignment findWithSubscriptionAndExercise(ElearningSubscription elearningSubscription, ElearningExercise elearningExercise, int pageNumber, int pageSize);

	public Page<ElearningAssignment> findWithSubscriptionAndOpened(ElearningSubscription elearningSubscription, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<ElearningAssignment> findWithSubscriptionAndClosed(ElearningSubscription elearningSubscription, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<ElearningAssignment> findWithSubscriptionAndDeferred(ElearningSubscription elearningSubscription, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<ElearningAssignment> findWithClassAndOpened(ElearningClass elearningClass, LocalDateTime systemDateTime, int pageNumber, int pageSize);

	public Page<ElearningAssignment> findWithClassAndClosed(ElearningClass elearningClass, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
	public Page<ElearningAssignment> findWithClassAndDeferred(ElearningClass elearningClass, LocalDateTime systemDateTime, int pageNumber, int pageSize);
	
}
