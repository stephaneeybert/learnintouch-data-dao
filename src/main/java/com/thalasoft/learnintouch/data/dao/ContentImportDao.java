package com.thalasoft.learnintouch.data.dao;

import java.io.Serializable;

import com.thalasoft.learnintouch.data.dao.domain.ContentImport;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public interface ContentImportDao extends GenericDao<ContentImport, Serializable> {

	public Page<ContentImport> findAll(int pageNumber, int pageSize);

	public Page<ContentImport> findWithPatternLike(String pattern, int pageNumber, int pageSize);

	public Page<ContentImport> findIsImporting(int pageNumber, int pageSize);

	public Page<ContentImport> findIsExporting(int pageNumber, int pageSize);

	public Page<ContentImport> findWithDomainName(String domainName, int pageNumber, int pageSize);

	public ContentImport findIsImportingByDomainName(String domainName);

	public ContentImport findIsExportingByDomainName(String domainName);

}
