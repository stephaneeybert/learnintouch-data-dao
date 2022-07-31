package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.StatisticsRefererDao;
import com.thalasoft.learnintouch.data.dao.domain.StatisticsReferer;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class StatisticsRefererDaoTest extends AbstractDaoTest {

	@Autowired
	private StatisticsRefererDao statisticsRefererDao;

	private StatisticsReferer statisticsReferer0;
	private StatisticsReferer statisticsReferer1;

	protected void setStatisticsRefererDao(StatisticsRefererDao statisticsRefererDao) {
		this.statisticsRefererDao = statisticsRefererDao;
	}

	public StatisticsRefererDaoTest() {
		statisticsReferer0 = new StatisticsReferer();
		statisticsReferer0.setName("Google");
		statisticsReferer0.setDescription("The Google search engine");
		statisticsReferer0.setUrl("www.google.com");

		statisticsReferer1 = new StatisticsReferer();
		statisticsReferer1.setName("Libe");
		statisticsReferer1.setUrl("www.libe.fr");
	}

	@Before
	public void beforeAnyTest() throws Exception {
		statisticsReferer1 = statisticsRefererDao.makePersistent(statisticsReferer1);
		statisticsReferer0 = statisticsRefererDao.makePersistent(statisticsReferer0);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(statisticsReferer0.getId());
		StatisticsReferer statisticsReferer = statisticsRefererDao.findWithId(statisticsReferer0.getId(), false);
		assertNotSame(statisticsReferer0.hashCode(), 0L);
		assertEquals(statisticsReferer0.hashCode(), statisticsReferer.hashCode());
		assertFalse(statisticsReferer0.toString().equals(""));
		assertNotNull(statisticsReferer.getId());
		assertEquals(statisticsReferer0.getName(), statisticsReferer.getName());
		assertTrue(statisticsRefererDao.isFoundById(statisticsReferer0.getId()));
		statisticsRefererDao.makeTransient(statisticsReferer0);
		assertFalse(statisticsRefererDao.isFoundById(statisticsReferer0.getId()));
	}

	@Test
	public void testFindAll() {
		Page<StatisticsReferer> page = statisticsRefererDao.findAll(0, 0);
		List<StatisticsReferer> statisticsReferers = page.getPageItems();
		assertEquals(2, statisticsReferers.size());
		assertEquals(statisticsReferer0.getName(), statisticsReferers.get(0).getName());
		assertEquals(statisticsReferer1.getName(), statisticsReferers.get(1).getName());
	}

	@Test
	public void testFindWithName() {
		StatisticsReferer statisticsReferer = statisticsRefererDao.findWithName("Google");
		assertEquals(statisticsReferer0.getName(), statisticsReferer.getName());
	}

	@Test
	public void testFindWithUrl() {
		StatisticsReferer statisticsReferer = statisticsRefererDao.findWithUrl("www.libe.fr");
		assertEquals(statisticsReferer1.getUrl(), statisticsReferer.getUrl());
	}

}