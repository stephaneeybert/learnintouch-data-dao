package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.DocumentCategoryDao;
import com.thalasoft.learnintouch.data.dao.DocumentDao;
import com.thalasoft.learnintouch.data.dao.domain.Document;
import com.thalasoft.learnintouch.data.dao.domain.DocumentCategory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class DocumentDaoTest extends AbstractDaoTest {

	private static Logger logger = LoggerFactory.getLogger(DocumentDaoTest.class);

	@Autowired
	private DocumentCategoryDao documentCategoryDao;

	@Autowired
	private DocumentDao documentDao;

	private Document document0;
	private Document document1;
	private Document document2;

	private DocumentCategory documentCategory0;

	protected void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	public DocumentDaoTest() {
		documentCategory0 = new DocumentCategory();
		documentCategory0.setName("theiages");
		documentCategory0.setListOrder(1);
		document0 = new Document();
		document0.setFilename("image0.png");
		document0.setListOrder(1);
		document0.setDocumentCategory(documentCategory0);
		document1 = new Document();
		document1.setFilename("pdf1.pdf");
		document1.setListOrder(2);
		document2 = new Document();
		document2.setFilename("pdf2.pdf");
		document2.setListOrder(3);
		document2.setDocumentCategory(documentCategory0);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		document1 = documentDao.makePersistent(document1);
		document0 = documentDao.makePersistent(document0);
		document2 = documentDao.makePersistent(document2);
		documentCategory0 = documentCategoryDao.makePersistent(documentCategory0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(document0.getId());
		assertNotSame(document0.hashCode(), 0L);
		assertFalse(document0.toString().equals(""));
		Document retrievedDocument = documentDao.findWithId(document0.getId(), true);
		assertEquals(document0.hashCode(), retrievedDocument.hashCode());
		assertEquals(document0.getFilename(), retrievedDocument.getFilename());
		assertNotNull(retrievedDocument.getId());
	}

	@Test
	public void testFindAll() {
		Page<Document> page = documentDao.findAll(0, 0);
		List<Document> documents = page.getPageItems();
		assertEquals(3, documents.size());
	}

	@Test
	public void testFindWithCategory() {
		Page<Document> page = documentDao.findWithCategory(documentCategory0, 0, 0);
		List<Document> documents = page.getPageItems();
		assertEquals(2, documents.size());
		assertEquals(document0.getListOrder(), documents.get(0).getListOrder());
		assertEquals(document2.getListOrder(), documents.get(1).getListOrder());
		page = documentDao.findWithCategory(null, 0, 0);
		documents = page.getPageItems();
		assertEquals(1, documents.size());
		assertEquals(document1.getListOrder(), documents.get(0).getListOrder());
		assertEquals("pdf1.pdf", documents.get(0).getFilename());
	}

	@Test
	public void testFindWithCategoryOrderById() {
		document1.setDocumentCategory(documentCategory0);
		List<Document> documents = documentDao.findWithCategoryOrderById(documentCategory0);
		assertEquals(3, documents.size());
		assertEquals(document1.getListOrder(), documents.get(0).getListOrder());
		assertEquals(document0.getListOrder(), documents.get(1).getListOrder());
		assertEquals(document2.getListOrder(), documents.get(2).getListOrder());
		Page<Document> page = documentDao.findWithCategory(null, 0, 0);
		documents = page.getPageItems();
		assertEquals(0, documents.size());
		document1.setDocumentCategory(null);
		documents = documentDao.findWithCategoryOrderById(null);
		assertEquals(1, documents.size());
		assertEquals(document1.getId(), documents.get(0).getId());
	}

	@Test
	public void testFindWithFilename() {
		List<Document> documents = documentDao.findWithFilename("pdf1.pdf");
		assertEquals(1, documents.size());
		assertEquals("pdf1.pdf", documents.get(0).getFilename());
	}
	
	@Test
	public void testFindPublished() {
		document0.setHide(true);
		Page<Document> page = documentDao.findPublished(0, 0);
		List<Document> documents = page.getPageItems();
		assertEquals(2, documents.size());
		assertEquals(document1.getListOrder(), documents.get(0).getListOrder());
		assertEquals(document2.getListOrder(), documents.get(1).getListOrder());
	}

	@Test
	public void testFindWithListOrder() {
		document1.setDocumentCategory(documentCategory0);
		Document document = documentDao.findWithListOrder(documentCategory0, 2);
		assertEquals(document1.getListOrder(), document.getListOrder());
		document = documentDao.findWithListOrder(null, 2);
		assertNull(document);
		document1.setDocumentCategory(null);
		document = documentDao.findWithListOrder(null, 2);
		assertNotNull(document);
		assertEquals(document1.getListOrder(), document.getListOrder());
	}

	@Test
	public void testFindNextByListOrder() {
		Document document = documentDao.findNextWithListOrder(null, 1);
		assertEquals(document1.getId(), document.getId());
		document = documentDao.findNextWithListOrder(null, 0);
		assertEquals(document1.getId(), document.getId());
		document = documentDao.findNextWithListOrder(null, 2);
		assertNull(document);
		document = documentDao.findNextWithListOrder(documentCategory0, 1);
		assertEquals(document2.getListOrder(), document.getListOrder());
		document = documentDao.findNextWithListOrder(documentCategory0, 0);
		assertEquals(document0.getListOrder(), document.getListOrder());
		document = documentDao.findNextWithListOrder(documentCategory0, 2);
		assertEquals(document2.getListOrder(), document.getListOrder());
	}

	@Test
	public void testFindPreviousByListOrder() {
		Document document = documentDao.findPreviousWithListOrder(null, 2);
		assertNull(document);
		document = documentDao.findPreviousWithListOrder(null, 3);
		assertEquals(document1.getListOrder(), document.getListOrder());
		document = documentDao.findPreviousWithListOrder(null, 4);
		assertEquals(document1.getListOrder(), document.getListOrder());
		document = documentDao.findPreviousWithListOrder(null, 1);
		assertNull(document);
		document = documentDao.findPreviousWithListOrder(documentCategory0, 2);
		assertEquals(document0.getListOrder(), document.getListOrder());
		document = documentDao.findPreviousWithListOrder(documentCategory0, 3);
		assertEquals(document0.getListOrder(), document.getListOrder());
		document = documentDao.findPreviousWithListOrder(documentCategory0, 4);
		assertEquals(document2.getListOrder(), document.getListOrder());
		document = documentDao.findPreviousWithListOrder(documentCategory0, 1);
		assertNull(document);
	}

	@Test
	public void testCountListOrderDuplicates() {
		document0.setDocumentCategory(null);
		assertEquals(0, documentDao.countListOrderDuplicates(null));
		document0.setListOrder(2);
		assertEquals(2, documentDao.countListOrderDuplicates(null));
		document0.setDocumentCategory(documentCategory0);
		document2.setListOrder(2);
		assertEquals(2, documentDao.countListOrderDuplicates(documentCategory0));
		document2.setDocumentCategory(null);
		assertEquals(0, documentDao.countListOrderDuplicates(documentCategory0));
	}

}
