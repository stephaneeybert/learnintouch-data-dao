package com.thalasoft.learnintouch.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.learnintouch.data.jpa.domain.ContentImport;

public interface ContentImportRepository extends GenericRepository<ContentImport, Long> {

	@Query("SELECT c FROM ContentImport c WHERE LOWER(c.domainName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) ORDER BY c.domainName")
	public Page<ContentImport> search(@Param("searchTerm") String searchTerm, Pageable page);
	
    @Query("SELECT c FROM ContentImport c ORDER BY c.domainName")
	public Page<ContentImport> findAll(Pageable page);

    @Query("SELECT c FROM ContentImport c WHERE c.isImporting = '1' ORDER BY c.domainName")
	public Page<ContentImport> findByIsImportingTrue(Pageable page);
	
    @Query("SELECT c FROM ContentImport c WHERE c.isExporting = '1' ORDER BY c.domainName")
	public Page<ContentImport> findByIsExportingTrue(Pageable page);
	
	public Page<ContentImport> findByDomainName(String domainName, Pageable page);
	
	public ContentImport findByDomainNameAndIsImportingTrue(String domainName);
	
	public ContentImport findByDomainNameAndIsExportingTrue(String domainName);
	
}
