package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.StatisticsVisitDao;
import com.thalasoft.learnintouch.data.dao.domain.StatisticsVisit;

public class StatisticsVisitDaoTest extends AbstractDaoTest {

	@Autowired
	private StatisticsVisitDao statisticsVisitDao;

	private StatisticsVisit statisticsVisit0;
	private StatisticsVisit statisticsVisit1;
	private StatisticsVisit statisticsVisit2;
	private StatisticsVisit statisticsVisit3;
	private StatisticsVisit statisticsVisit4;
	private String browser0 = "Firefox";
	private String browser1 = "Chrome";
	private String browser2 = "Safari";
	private String visitorHostAddress0 = "85.231.434.45";
	private String visitorHostAddress1 = "123.22.454.232";
	private String visitorHostAddress2 = "112.54.34.111";
	private String visitorReferer0 = "www.google.com";
	private String visitorReferer1 = "www.francofil.com";
	private String visitorReferer2 = "www.lasuede.com";
	
	protected void setStatisticsVisitDao(StatisticsVisitDao statisticsVisitDao) {
		this.statisticsVisitDao = statisticsVisitDao;
	}

	public StatisticsVisitDaoTest() {
		statisticsVisit0 = new StatisticsVisit();
		statisticsVisit0.setVisitDatetime(new LocalDateTime());
		statisticsVisit0.setVisitorBrowser(browser0);
		statisticsVisit0.setVisitorHostAddress(visitorHostAddress0);
		statisticsVisit0.setVisitorReferer(visitorReferer0);

		statisticsVisit1 = new StatisticsVisit();
		statisticsVisit1.setVisitDatetime(new LocalDateTime().minusYears(2));
		statisticsVisit1.setVisitorBrowser(browser1);
		statisticsVisit1.setVisitorHostAddress(visitorHostAddress1);
		statisticsVisit1.setVisitorReferer(visitorReferer1);
		
		statisticsVisit2 = new StatisticsVisit();
		statisticsVisit2.setVisitDatetime(new LocalDateTime().minusYears(1));
		statisticsVisit2.setVisitorBrowser(browser2);
		statisticsVisit2.setVisitorHostAddress(visitorHostAddress2);
		statisticsVisit2.setVisitorReferer(visitorReferer2);

		statisticsVisit3 = new StatisticsVisit();
		statisticsVisit3.setVisitDatetime(new LocalDateTime().minusYears(1));
		statisticsVisit3.setVisitorBrowser(browser2);
		statisticsVisit3.setVisitorHostAddress(visitorHostAddress2);
		statisticsVisit3.setVisitorReferer(visitorReferer2);

		statisticsVisit4 = new StatisticsVisit();
		statisticsVisit4.setVisitDatetime(new LocalDateTime().minusYears(1));
		statisticsVisit4.setVisitorBrowser(browser1);
		statisticsVisit4.setVisitorHostAddress(visitorHostAddress1);
		statisticsVisit4.setVisitorReferer(visitorReferer1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		statisticsVisit2 = statisticsVisitDao.makePersistent(statisticsVisit2);
		statisticsVisit1 = statisticsVisitDao.makePersistent(statisticsVisit1);
		statisticsVisit0 = statisticsVisitDao.makePersistent(statisticsVisit0);
		statisticsVisit4 = statisticsVisitDao.makePersistent(statisticsVisit4);
		statisticsVisit3 = statisticsVisitDao.makePersistent(statisticsVisit3);
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(statisticsVisit0.getId());
		StatisticsVisit statisticsVisit = statisticsVisitDao.findWithId(statisticsVisit0.getId(), false);
		assertNotSame(statisticsVisit0.hashCode(), 0L);
		assertEquals(statisticsVisit0.hashCode(), statisticsVisit.hashCode());
		assertFalse(statisticsVisit0.toString().equals(""));
		assertNotNull(statisticsVisit.getId());
		assertEquals(statisticsVisit0.getVisitorBrowser(), statisticsVisit.getVisitorBrowser());
		assertTrue(statisticsVisitDao.isFoundById(statisticsVisit0.getId()));
		statisticsVisitDao.makeTransient(statisticsVisit0);
		assertFalse(statisticsVisitDao.isFoundById(statisticsVisit0.getId()));
	}

	@Test
	public void testDeleteOld() {
		assertEquals(1, statisticsVisitDao.deleteOld(new LocalDateTime().minusYears(1).getYear()));
	}

	@Test
	public void testCountOldVisits() {
		assertEquals(1, statisticsVisitDao.countOldVisits(new LocalDateTime().minusYears(1).getYear()));
	}

	@Test
	public void testFindCountsByReferer() {
		List<Object[]> objects = statisticsVisitDao.findCountsByReferer(new LocalDateTime().minusYears(1).getYear(), new LocalDateTime().getMonthOfYear());
		assertEquals(2, objects.size());
		Object[] properties = (Object[]) objects.get(0);
		assertEquals(1L, properties[0]);
		assertEquals(statisticsVisit4.getVisitorReferer(), properties[1]);
		properties = (Object[]) objects.get(1);
		assertEquals(2L, properties[0]);
		assertEquals(statisticsVisit2.getVisitorReferer(), properties[1]);
	}

	@Test
	public void testFindCountsByBrowser() {
		List<Object[]> objects = statisticsVisitDao.findCountsByBrowser();
		assertEquals(3, objects.size());
		Object[] properties = (Object[]) objects.get(0);
		assertEquals(2L, properties[0]);
		assertEquals(statisticsVisit1.getVisitorBrowser(), properties[1]);
		properties = (Object[]) objects.get(1);
		assertEquals(1L, properties[0]);
		assertEquals(statisticsVisit0.getVisitorBrowser(), properties[1]);
		properties = (Object[]) objects.get(2);
		assertEquals(2L, properties[0]);
		assertEquals(statisticsVisit2.getVisitorBrowser(), properties[1]);
	}

	@Test
	public void testFindHostLastVisit() {
	}
	
	@Test
	public void testCountMonthVisitors() {
		assertEquals(2, statisticsVisitDao.countMonthVisitors(new LocalDateTime().minusYears(1).getYear(), new LocalDateTime().getMonthOfYear()));
	}

	@Test
	public void testCountMonthVisits() {
		assertEquals(3, statisticsVisitDao.countMonthVisits(new LocalDateTime().minusYears(1).getYear(), new LocalDateTime().getMonthOfYear()));
	}

	@Test
	public void testCountDayVisitors() {
		statisticsVisitDao.flush();
		assertEquals(2, statisticsVisitDao.countDayVisitors(new LocalDateTime().minusYears(1).getYear(), new LocalDateTime().getMonthOfYear(), new LocalDateTime().getDayOfMonth()));
	}

	@Test
	public void testCountDayVisits() {
		statisticsVisitDao.flush();
		assertEquals(3, statisticsVisitDao.countDayVisits(new LocalDateTime().minusYears(1).getYear(), new LocalDateTime().getMonthOfYear(), new LocalDateTime().getDayOfMonth()));
	}

	@Test
	public void testCountWeekDayVisits() {
		statisticsVisitDao.flush();
		assertEquals(3, statisticsVisitDao.countWeekDayVisits(new LocalDateTime().minusYears(1).getYear(), new LocalDateTime().minusYears(1).getDayOfWeek()));
	}

	@Test
	public void testCountHourVisits() {
		statisticsVisitDao.flush();
		assertEquals(3, statisticsVisitDao.countHourVisits(new LocalDateTime().minusYears(1).getYear(), new LocalDateTime().minusYears(1).getHourOfDay()));
	}

	@Test
	public void testCountVisitorsSince() {
		assertEquals(3, statisticsVisitDao.countVisitorsSince(new LocalDateTime().minusYears(1)));
	}

	@Test
	public void testCountVisitsSince() {
		assertEquals(4, statisticsVisitDao.countVisitsSince(new LocalDateTime().minusYears(1)));
	}

}
