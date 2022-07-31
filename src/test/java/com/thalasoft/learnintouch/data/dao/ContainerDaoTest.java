package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.ContainerDao;
import com.thalasoft.learnintouch.data.dao.domain.Container;

public class ContainerDaoTest extends AbstractDaoTest {

	@Autowired
	private ContainerDao containerDao;

	private Container container0;
	private Container container1;
	private String content0 = "The content 0";
	private String content1 = "The content 1";
	
	protected void setContainerDao(ContainerDao containerDao) {
		this.containerDao = containerDao;
	}

	public ContainerDaoTest() {
		container0 = new Container();
		container0.setContent(content0);
		container1 = new Container();
		container1.setContent(content1);
	}

	@Before
	public void beforeAnyTest() throws Exception {
		container0 = containerDao.makePersistent(container0);
		container1 = containerDao.makePersistent(container1);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(container0.getId());
		assertNotSame(container0.hashCode(), 0L);
		assertFalse(container0.toString().equals(""));
		Container retrievedContainer = containerDao.findWithId(container0.getId(), true);
		assertEquals(container0.hashCode(), retrievedContainer.hashCode());
		assertEquals(container0.getContent(), retrievedContainer.getContent());
		assertNotNull(retrievedContainer.getId());
	}
	
}
