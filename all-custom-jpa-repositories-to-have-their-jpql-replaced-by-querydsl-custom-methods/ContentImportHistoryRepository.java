package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ContentImportHistory;

public interface ContentImportHistoryRepository extends GenericRepository<ContentImportHistory, Long> {

    @Query("SELECT c FROM ContentImportHistory c ORDER BY c.domainName, c.importDatetime DESC")
    public Page<ContentImportHistory> findThemAll(Pageable page);
    
    @Query("SELECT c FROM ContentImportHistory c WHERE LOWER(c.domainName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(c.course) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(c.lesson) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(c.exercise) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY c.domainName, c.importDatetime DESC")
    public Page<ContentImportHistory> search(@Param("searchTerm") String searchTerm, Pageable page);
    
	public Page<ContentImportHistory> findByDomainNameOrderByImportDatetimeDesc(String domainName, Pageable page);

	public Page<ContentImportHistory> findByCourseNotNullOrderByDomainNameAscImportDatetimeDesc(Pageable page);

	public Page<ContentImportHistory> findByLessonNotNullOrderByDomainNameAscImportDatetimeDesc(Pageable page);
	
	public Page<ContentImportHistory> findByExerciseNotNullOrderByDomainNameAscImportDatetimeDesc(Pageable page);
	
}
