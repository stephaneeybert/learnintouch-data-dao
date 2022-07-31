package com.thalasoft.learnintouch.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thalasoft.learnintouch.data.dao.AdminDao;
import com.thalasoft.learnintouch.data.dao.AdminOptionDao;
import com.thalasoft.learnintouch.data.dao.domain.Admin;
import com.thalasoft.learnintouch.data.dao.domain.AdminOption;

public class AdminOptionDaoTest extends AbstractDaoTest {

	@Autowired
	private AdminOptionDao adminOptionDao;
	
	@Autowired
	private AdminDao adminDao;
	
	private AdminOption adminOption;
	private Admin admin;
	
	protected void setAdminOptionDao(AdminOptionDao adminOptionDao) {
		this.adminOptionDao = adminOptionDao;
	}
	
	protected void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	public AdminOptionDaoTest() {
		admin = new Admin();
		admin.setLogin("stephane");
		admin.setPassword("toto");
        admin.setPasswordSalt("");
		admin.setEmail("mitt@yahoo.se");
		admin.setFirstname("Stephane");
		admin.setLastname("Eybert");

		adminOption = new AdminOption();
		adminOption.setAdmin(admin);
		adminOption.setName("OPTION_ELEARNING");
	}
	
	@Before
	public void beforeAnyTest() throws Exception {
		admin = adminDao.makePersistent(admin);
		adminOption = adminOptionDao.makePersistent(adminOption);
	}
	
	@Test
	public void testSaveAndRetrieve() {		
		assertNotNull(adminOption.getId());
		adminOption.setName("OPTION_DOCUMENT");
		AdminOption retrievedAdminOption = adminOptionDao.findWithId(adminOption.getId(), true);
		assertNotNull(retrievedAdminOption.getId());
		assertEquals(adminOption.getName(), retrievedAdminOption.getName());
		assertEquals(adminOption.getAdmin().getEmail(), retrievedAdminOption.getAdmin().getEmail());
        assertNotSame(adminOption.hashCode(), 0L);
        assertEquals(adminOption.hashCode(), retrievedAdminOption.hashCode());  
        assertFalse(adminOption.toString().equals(""));
        retrievedAdminOption = adminOptionDao.findWithNameAndAdmin("OPTION_DOCUMENT", admin);
        assertEquals("OPTION_DOCUMENT", retrievedAdminOption.getName());
	}

	@Test
	public void testFindWithOptionName() {
		List<AdminOption> adminOptions = adminOptionDao.findWithName("OPTION_ELEARNING");
		assertEquals(1, adminOptions.size());
		assertEquals("OPTION_ELEARNING", adminOptions.get(0).getName());
	}
	
	@Test
	public void testFindWithAdmin() {
		List<AdminOption> adminOptions = adminOptionDao.findWithAdmin(admin);
		assertEquals(1, adminOptions.size());
		assertEquals("OPTION_ELEARNING", adminOptions.get(0).getName());
	}

}
