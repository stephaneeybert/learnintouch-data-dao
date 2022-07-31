package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.thalasoft.learnintouch.data.dao.DocumentCategoryDao;
import com.thalasoft.learnintouch.data.dao.DocumentDao;
import com.thalasoft.learnintouch.data.dao.domain.Document;
import com.thalasoft.learnintouch.data.dao.domain.DocumentCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class DocumentCategoryDaoTest extends AbstractDaoTest {

	private static Logger logger = LoggerFactory.getLogger(DocumentCategoryDaoTest.class);

	@Autowired
	private DocumentCategoryDao documentCategoryDao;

	@Autowired
	private DocumentDao documentDao;

	private DocumentCategory documentCategory0;
	private DocumentCategory documentCategory1;

	private String name0 = "MyStuffCat";
	private String name1 = "Pdf";
	
	protected void setDocumentCategoryDao(DocumentCategoryDao documentCategoryDao) {
		this.documentCategoryDao = documentCategoryDao;
	}

	protected void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	public DocumentCategoryDaoTest() {
		documentCategory0 = new DocumentCategory();
		documentCategory0.setName(name0);
		documentCategory0.setListOrder(1);
		documentCategory1 = new DocumentCategory();
		documentCategory1.setName(name1);
		documentCategory1.setListOrder(2);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		documentCategory1 = documentCategoryDao.makePersistent(documentCategory1);
		documentCategory0 = documentCategoryDao.makePersistent(documentCategory0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(documentCategory0.getId());
		assertNotSame(documentCategory0.hashCode(), 0L);
		assertFalse(documentCategory0.toString().equals(""));
		DocumentCategory retrievedDocumentCategory = documentCategoryDao.findWithId(documentCategory0.getId(), true);
		assertEquals(documentCategory0.hashCode(), retrievedDocumentCategory.hashCode());
		assertEquals(documentCategory0.getName(), retrievedDocumentCategory.getName());
		assertNotNull(retrievedDocumentCategory.getId());
	}
	
	@Test
	public void testFindAll() {
		Page<DocumentCategory> page = documentCategoryDao.findAll(0, 0);
		List<DocumentCategory> documentCategories = page.getPageItems();
		assertEquals(2, documentCategories.size());
		assertEquals(documentCategory0.getListOrder(), documentCategories.get(0).getListOrder());
		assertEquals(documentCategory1.getListOrder(), documentCategories.get(1).getListOrder());
	}
	
	@Test
	public void testFindAllOrderById() {
		List<DocumentCategory> documentCategories = documentCategoryDao.findAllOrderById();
		assertEquals(2, documentCategories.size());
		assertEquals(documentCategory1.getListOrder(), documentCategories.get(0).getListOrder());
		assertEquals(documentCategory0.getListOrder(), documentCategories.get(1).getListOrder());
	}
	
	@Test
	public void testFindWithListOrder() {
		DocumentCategory documentCategory = documentCategoryDao.findWithListOrder(2);
		assertEquals(documentCategory1.getListOrder(), documentCategory.getListOrder());
		assertEquals(documentCategory1.getId(), documentCategory.getId());
		documentCategory = documentCategoryDao.findWithListOrder(1);
		assertEquals(documentCategory0.getListOrder(), documentCategory.getListOrder());
		assertEquals(documentCategory0.getId(), documentCategory.getId());
	}

	@Test
	public void testFindNextByListOrder() {
		DocumentCategory documentCategory = documentCategoryDao.findNextWithListOrder(0);
		assertEquals(documentCategory0.getListOrder(), documentCategory.getListOrder());
		documentCategory = documentCategoryDao.findNextWithListOrder(1);
		assertEquals(documentCategory1.getListOrder(), documentCategory.getListOrder());
		documentCategory = documentCategoryDao.findNextWithListOrder(2);
		assertNull(documentCategory);
	}

	@Test
	public void testFindPreviousByListOrder() {
		DocumentCategory documentCategory = documentCategoryDao.findPreviousWithListOrder(2);
		assertEquals(documentCategory0.getListOrder(), documentCategory.getListOrder());
		documentCategory = documentCategoryDao.findPreviousWithListOrder(3);
		assertEquals(documentCategory1.getListOrder(), documentCategory.getListOrder());
		documentCategory = documentCategoryDao.findPreviousWithListOrder(1);
		assertNull(documentCategory);
	}

	@Test
	public void testCountListOrderDuplicates() {
		long count = documentCategoryDao.countListOrderDuplicates();
		assertEquals(0, count);
		documentCategory0.setListOrder(2);
		assertEquals(2, documentCategoryDao.countListOrderDuplicates());
	}

	@Test
	public void testDelete() {
		assertEquals(2, documentCategoryDao.countAll());
		documentCategoryDao.makeTransient(documentCategory0);
		assertEquals(1, documentCategoryDao.countAll());
		Document document = new Document();
		document.setFilename("myfile");
		document.setDocumentCategory(documentCategory1);
		document = documentDao.makePersistent(document);
		try {
			documentCategoryDao.makeTransient(documentCategory1);
			documentCategoryDao.flush();
			fail("The document category was deleted when it should not have been.");
		} catch	(DataAccessException e) {
		} finally {
			documentCategoryDao.clear();			
		}
		assertEquals(1, documentCategoryDao.countAll());
		document = documentDao.makePersistent(document);
		document.setDocumentCategory(null);
		documentCategoryDao.makeTransient(documentCategory1);
		assertEquals(0, documentCategoryDao.countAll());
	}
	
}
