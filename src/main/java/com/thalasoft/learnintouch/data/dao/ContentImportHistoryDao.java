package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ContentImportHistory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ContentImportHistoryDao extends GenericDao<ContentImportHistory, Serializable> {

	public Page<ContentImportHistory> findWithDomainName(String domainName, int pageNumber, int pageSize);

	public Page<ContentImportHistory> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<ContentImportHistory> findWithCourseContent(int pageNumber, int pageSize);

	public Page<ContentImportHistory> findWithLessonContent(int pageNumber, int pageSize);

	public Page<ContentImportHistory> findWithExerciseContent(int pageNumber, int pageSize);

	public Page<ContentImportHistory> findAll(int pageNumber, int pageSize);

}
