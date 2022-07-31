package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.ContentImportDao;
import com.thalasoft.learnintouch.data.dao.domain.ContentImport;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ContentImportDaoTest extends AbstractDaoTest {

	@Autowired
	private ContentImportDao contentImportDao;

	private ContentImport contentImport0;
	private ContentImport contentImport1;
	private String domainName0 = "thalasoft.com";
	private String domainName1 = "europasprak.com";
	
	protected void setContentImportDao(ContentImportDao contentImportDao) {
		this.contentImportDao = contentImportDao;
	}

	public ContentImportDaoTest() {
		contentImport0 = new ContentImport();
		contentImport0.setDomainName(domainName0);
		contentImport0.setIsImporting(true);
		contentImport0.setIsExporting(true);
		contentImport1 = new ContentImport();
		contentImport1.setDomainName(domainName1);
		contentImport1.setIsImporting(true);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		contentImport1 = contentImportDao.makePersistent(contentImport1);
		contentImport0 = contentImportDao.makePersistent(contentImport0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(contentImport0.getId());
		assertNotSame(contentImport0.hashCode(), 0L);
		assertFalse(contentImport0.toString().equals(""));
		ContentImport retrievedContentImport = contentImportDao.findWithId(contentImport0.getId(), true);
		assertEquals(contentImport0.hashCode(), retrievedContentImport.hashCode());
		assertEquals(contentImport0.getDomainName(), retrievedContentImport.getDomainName());
		assertNotNull(retrievedContentImport.getId());
	}
	
	@Test
	public void testFindAll() {
		Page<ContentImport> page = contentImportDao.findAll(0, 0);
		List<ContentImport> contentImports = page.getPageItems();
		assertEquals(2, contentImports.size());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<ContentImport> page = contentImportDao.findWithPatternLike("hala", 0, 0);
		List<ContentImport> contentImports = page.getPageItems();
		assertEquals(1, contentImports.size());
		assertEquals(domainName0, contentImports.get(0).getDomainName());
		assertEquals(true, contentImports.get(0).getIsImporting());
		assertEquals(true, contentImports.get(0).getIsExporting());
		page = contentImportDao.findWithPatternLike("blabla", 0, 0);
		contentImports = page.getPageItems();
		assertEquals(0, contentImports.size());
	}
	
	@Test
	public void testFindWithDomainName() {
		Page<ContentImport> page = contentImportDao.findWithDomainName(domainName1, 0, 0);
		List<ContentImport> contentImports = page.getPageItems();
		assertEquals(1, contentImports.size());
		assertEquals(domainName1, contentImports.get(0).getDomainName());
		assertEquals(true, contentImports.get(0).getIsImporting());
		assertEquals(false, contentImports.get(0).getIsExporting());
	}
	
	@Test
	public void testFindIsImporting() {
		Page<ContentImport> page = contentImportDao.findIsImporting(0, 0);
		List<ContentImport> contentImports = page.getPageItems();
		assertEquals(2, contentImports.size());
	}
	
	@Test
	public void testFindIsExporting() {
		Page<ContentImport> page = contentImportDao.findIsExporting(0, 0);
		List<ContentImport> contentImports = page.getPageItems();
		assertEquals(1, contentImports.size());
	}
	
	@Test
	public void testFindIsImportingByDomainName() {
		ContentImport contentImport = contentImportDao.findIsImportingByDomainName(domainName0);
		assertEquals(domainName0, contentImport.getDomainName());
	}
	
	@Test
	public void testFindIsExportingByDomainName() {
		ContentImport contentImport = contentImportDao.findIsExportingByDomainName(domainName1);
		assertNull(contentImport);
		contentImport = contentImportDao.findIsExportingByDomainName(domainName0);
		assertNotNull(contentImport);
	}
	
}
