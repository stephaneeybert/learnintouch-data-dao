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

import com.thalasoft.learnintouch.data.dao.StatisticsPageDao;
import com.thalasoft.learnintouch.data.dao.domain.StatisticsPage;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

public class StatisticsPageDaoTest extends AbstractDaoTest {

	@Autowired
	private StatisticsPageDao statisticsPageDao;

	private StatisticsPage statisticsPage0;
	private StatisticsPage statisticsPage1;

	protected void setStatisticsPageDao(StatisticsPageDao statisticsPageDao) {
		this.statisticsPageDao = statisticsPageDao;
	}

	public StatisticsPageDaoTest() {
		statisticsPage0 = new StatisticsPage();
		statisticsPage0.setPage("home");
		statisticsPage0.setYear(2010);
		statisticsPage0.setMonth(1);
		statisticsPage0.setHits(1000);

		statisticsPage1 = new StatisticsPage();
		statisticsPage1.setPage("contact");
		statisticsPage1.setYear(2009);
		statisticsPage1.setMonth(12);
		statisticsPage1.setHits(2000);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		statisticsPage1 = statisticsPageDao.makePersistent(statisticsPage1);
		statisticsPage0 = statisticsPageDao.makePersistent(statisticsPage0);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(statisticsPage0.getId());
		StatisticsPage statisticsPage = statisticsPageDao.findWithId(statisticsPage0.getId(), false);
		assertNotSame(statisticsPage0.hashCode(), 0L);
		assertEquals(statisticsPage0.hashCode(), statisticsPage.hashCode());
		assertFalse(statisticsPage0.toString().equals(""));
		assertNotNull(statisticsPage.getId());
		assertEquals(statisticsPage0.getPage(), statisticsPage.getPage());
		assertTrue(statisticsPageDao.isFoundById(statisticsPage0.getId()));
		statisticsPageDao.makeTransient(statisticsPage0);
		assertFalse(statisticsPageDao.isFoundById(statisticsPage0.getId()));
	}

	@Test
	public void testFindAll() {
		Page<StatisticsPage> page = statisticsPageDao.findAll(0, 0);
		List<StatisticsPage> statisticsPages = page.getPageItems();
		assertEquals(2, statisticsPages.size());
		assertEquals(statisticsPage1.getPage(), statisticsPages.get(0).getPage());
		assertEquals(statisticsPage0.getPage(), statisticsPages.get(1).getPage());
	}

	@Test
	public void testAddHit() {
		assertEquals(1, statisticsPageDao.addHit(statisticsPage0));
	}

	@Test
	public void testDeleteOld() {
		assertEquals(1, statisticsPageDao.deleteOld(2010));
	}

	@Test
	public void testSelectByYearAndMonth() {
		Page<StatisticsPage> page = statisticsPageDao.findWithYearAndMonth(2010, 3, 0, 0);
		List<StatisticsPage> statisticsPages = page.getPageItems();
		assertEquals(0, statisticsPages.size());
		page = statisticsPageDao.findWithYearAndMonth(2010, 1, 0, 0);
		statisticsPages = page.getPageItems();
		assertEquals(1, statisticsPages.size());
		assertEquals(statisticsPage0.getPage(), statisticsPages.get(0).getPage());
	}

}