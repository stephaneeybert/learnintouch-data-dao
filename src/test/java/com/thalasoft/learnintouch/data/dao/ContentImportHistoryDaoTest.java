package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.ContentImportHistoryDao;
import com.thalasoft.learnintouch.data.dao.domain.ContentImportHistory;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class ContentImportHistoryDaoTest extends AbstractDaoTest {

	@Autowired
	private ContentImportHistoryDao contentImportHistoryDao;

	private ContentImportHistory contentImportHistory0;
	private ContentImportHistory contentImportHistory0bis;
	private ContentImportHistory contentImportHistory1;
	private ContentImportHistory contentImportHistory2;
	private String domainName0 = "thalasoft.com";
	private String domainName1 = "europasprak.com";
	private String course = "A course in french";
	private String lesson = "An lesson in russian";
	private String exercise = "An exercise in russian";
	
	protected void setContentImportHistoryDao(ContentImportHistoryDao contentImportHistoryDao) {
		this.contentImportHistoryDao = contentImportHistoryDao;
	}

	public ContentImportHistoryDaoTest() {
		contentImportHistory0 = new ContentImportHistory();
		contentImportHistory0.setDomainName(domainName0);
		contentImportHistory0.setCourse(course);
		LocalDateTime importDatetime = new LocalDateTime();
		contentImportHistory0.setImportDatetime(importDatetime);
		contentImportHistory0bis = new ContentImportHistory();
		contentImportHistory0bis.setDomainName(domainName0);
		contentImportHistory0bis.setImportDatetime(importDatetime);
		contentImportHistory0bis.setCourse(null);
		contentImportHistory1 = new ContentImportHistory();
		contentImportHistory1.setDomainName(domainName1);
		contentImportHistory1.setImportDatetime(importDatetime);
		contentImportHistory1.setLesson(lesson);
		contentImportHistory2 = new ContentImportHistory();
		contentImportHistory2.setDomainName(domainName0);
		contentImportHistory2.setImportDatetime(importDatetime);
		contentImportHistory2.setExercise(exercise);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		contentImportHistory1 = contentImportHistoryDao.makePersistent(contentImportHistory1);
		contentImportHistory0 = contentImportHistoryDao.makePersistent(contentImportHistory0);
		contentImportHistory0bis = contentImportHistoryDao.makePersistent(contentImportHistory0bis);
		contentImportHistory2 = contentImportHistoryDao.makePersistent(contentImportHistory2);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(contentImportHistory0.getId());
		assertNotSame(contentImportHistory0.hashCode(), 0L);
		assertFalse(contentImportHistory0.toString().equals(""));
		ContentImportHistory retrievedContentImport = contentImportHistoryDao.findWithId(contentImportHistory0.getId(), true);
		assertEquals(contentImportHistory0.hashCode(), retrievedContentImport.hashCode());
		assertEquals(contentImportHistory0.getDomainName(), retrievedContentImport.getDomainName());
		assertEquals(contentImportHistory0.getImportDatetime(), retrievedContentImport.getImportDatetime());
		assertNotNull(retrievedContentImport.getId());
	}
	
	@Test
	public void testFindAll() {
		Page<ContentImportHistory> page = contentImportHistoryDao.findAll(0, 0);
		List<ContentImportHistory> contentImportHistories = page.getPageItems();
		assertEquals(4, contentImportHistories.size());
	}

	@Test
	public void testFindWithPatternLike() {
		Page<ContentImportHistory> page = contentImportHistoryDao.findWithPatternLike("hala", 0, 0);
		List<ContentImportHistory> contentImportHistories = page.getPageItems();
		assertEquals(3, contentImportHistories.size());
		assertEquals(domainName0, contentImportHistories.get(0).getDomainName());
		page = contentImportHistoryDao.findWithPatternLike("blabla", 0, 0);
		contentImportHistories = page.getPageItems();
		assertEquals(0, contentImportHistories.size());
	}
	
	@Test
	public void testFindWithDomainName() {
		Page<ContentImportHistory> page = contentImportHistoryDao.findWithDomainName(domainName1, 0, 0);
		List<ContentImportHistory> contentImportHistories = page.getPageItems();
		assertEquals(1, contentImportHistories.size());
		assertEquals(domainName1, contentImportHistories.get(0).getDomainName());
	}
	
	@Test
	public void testFindWithCourseContent() {
		Page<ContentImportHistory> page = contentImportHistoryDao.findWithCourseContent(0, 0);
		List<ContentImportHistory> contentImportHistories = page.getPageItems();
		assertEquals(1, contentImportHistories.size());
		assertEquals(course, contentImportHistories.get(0).getCourse());
	}
	
	@Test
	public void testFindWithLessonContent() {
		Page<ContentImportHistory> page = contentImportHistoryDao.findWithLessonContent(0, 0);
		List<ContentImportHistory> contentImportHistories = page.getPageItems();
		assertEquals(1, contentImportHistories.size());
		assertEquals(lesson, contentImportHistories.get(0).getLesson());
	}
	
	@Test
	public void testFindWithExerciseContent() {
		Page<ContentImportHistory> page = contentImportHistoryDao.findWithExerciseContent(0, 0);
		List<ContentImportHistory> contentImportHistories = page.getPageItems();
		assertEquals(1, contentImportHistories.size());
		assertEquals(exercise, contentImportHistories.get(0).getExercise());
	}
		
}
