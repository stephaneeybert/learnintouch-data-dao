package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.thalasoft.learnintouch.data.dao.PropertyDao;
import com.thalasoft.learnintouch.data.dao.domain.Property;

@TransactionConfiguration(defaultRollback = true)
public class PropertyDaoTest extends AbstractDaoTest {

	@Autowired
	private PropertyDao propertyDao;

	private Property property0;
	private Property property1;

	protected void setPropertyDao(PropertyDao propertyDao) {
		this.propertyDao = propertyDao;
	}

	public PropertyDaoTest() {
		property0 = new Property();
		property0.setName("LENGTH");
		property0.setValue("10");
		property1 = new Property();
		property1.setName("WIDTH");
		property1.setValue("20");
	}
	
	@Before
	public void beforeAnyTest() throws Exception {
		property1 = propertyDao.makePersistent(property1);
		property0 = propertyDao.makePersistent(property0);
	}
	
	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(property0.getId());
		assertNotSame(property0.hashCode(), 0L);
		assertFalse(property0.toString().equals(""));
		Property property = propertyDao.findWithId(property0.getId(), true);
		assertEquals(property0.hashCode(), property.hashCode());
		assertEquals(property0.getName(), property.getName());
		assertEquals(property0.getValue(), property.getValue());
		assertNotNull(property.getId());
	}

	@Test
	public void testFindWithName() {
		Property property = propertyDao.findWithName(property0.getName());
		assertEquals(property0.getName(), property.getName());
	}
	
}