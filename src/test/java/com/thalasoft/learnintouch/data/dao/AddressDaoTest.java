package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.AddressDao;
import com.thalasoft.learnintouch.data.dao.domain.Address;

public class AddressDaoTest extends AbstractDaoTest {

	@Autowired
	private AddressDao addressDao;

	private Address address1;
	private Address address2;
	
	protected void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}
	
	public AddressDaoTest() {
		address1 = new Address();
		address1.setAddress1("6 place Emile");
		address1.setAddress2("Couteron");
		address1.setZipCode("54000");
		address1.setCity("Nancy");
		address1.setPostalBox("PoBox 001");
		address1.setState("PACA");
		address1.setCountry("France");

		address2 = new Address();
		address2.setAddress1("40 rue Mignet");
		address2.setZipCode("13100");
		address2.setCity("Aix-en-Provence");
		address2.setState("PACA");
		address2.setCountry("France");
	}
	
	@Before
	public void beforeAnyTest() throws Exception {
		address1 = addressDao.makePersistent(address1);
		address2 = addressDao.makePersistent(address2);
	}
	
	@After
	public void afterAnyTest() throws Exception {
	}

	@Test
	public void testSaveAndRetrieve() {
		assertNotNull(address1.getId());
		long count = addressDao.countAll();
		assertEquals(2, count);
		address2.setAddress1("6 place Emile Bossy");
		Address loadedAddress = addressDao.findWithId(address2.getId(), true);
		assertNotNull(loadedAddress.getId());
		assertEquals(address2.getAddress1(), loadedAddress.getAddress1());
        assertNotSame(address2.hashCode(), 0L);
        assertEquals(address2.hashCode(), loadedAddress.hashCode());  
        assertFalse(address2.toString().equals(""));
	}

}